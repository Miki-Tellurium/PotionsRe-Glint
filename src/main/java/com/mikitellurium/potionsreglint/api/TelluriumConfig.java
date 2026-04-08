package com.mikitellurium.potionsreglint.api;

import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * You can use and modify this freely if you want, just respect the License
 * and credit me.
 *
 * MIT License
 *
 * Copyright (c) 2023 Miki_Tellurium
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * A class used to create and load simple config files.
 * @author Miki Tellurium
 * @version 1.3.0-Fabric
 */
@SuppressWarnings("rawtypes")
public class TelluriumConfig {

    private static final String fileExtension = ".properties";

    /**
     * The Builder is an object used to define new config entries,
     * as well as saving and loading config files.
     */
    public static class Builder {

        private final String file; // The path of the config file

        private final List<String> COMMENTS = new ArrayList<>();
        private final List<ConfigEntry> ENTRIES = new ArrayList<>();

        /**
         * Builder object constructor.
         * @param fileName the name of the config file
         */
        public Builder(String fileName) {
            // Using the mod id suggested
            this.file = FabricLoader.getInstance().getConfigDir().resolve(fileName + fileExtension).toString();
        }

        /**
         * Return the path of the config file
         *
         * @return the config file path
         */
        public String getConfigFilePath() {
            return file;
        }


        /**
         * Add a comment to the config file.
         * <p>
         * Comments will be written at the top of the file before
         * any entry.
         *
         * @param comment the comment to write to the config file
         * @return the builder object
         */
        public Builder comment(String comment) {
            COMMENTS.add(comment);
            return this;
        }

        /**
         * Makes an entry that holds a boolean value.
         *
         * @param key the name of the entry
         * @param defaultValue the default value of the entry
         * @return the config entry that was created
         */
        public ConfigEntry<Boolean> define(String key, boolean defaultValue) {
            ConfigEntry<Boolean> newEntry = new ConfigEntry<>(this, key, defaultValue);
            ENTRIES.add(newEntry);
            return newEntry;
        }

        /**
         * Makes an entry that holds a integer value.
         *
         * @param key the name of the entry
         * @param defaultValue the default value of the entry
         * @return the config entry that was created
         */
        public ConfigEntry<Integer> define(String key, int defaultValue) {
            ConfigEntry<Integer> newEntry = new ConfigEntry<>(this, key, defaultValue);
            ENTRIES.add(newEntry);
            return newEntry;
        }

        /**
         * Makes an entry that holds a integer value.
         * <p>
         * This value will always stay between the specified
         * range (inclusive).
         *
         * @param key the name of the entry
         * @param minValue the minimum value this entry can have
         * @param maxValue the maximum value this entry can have
         * @param defaultValue the default value of the entry
         * @return the config entry that was created
         */
        public IntRangeConfigEntry defineInRange(String key, int minValue, int maxValue, int defaultValue) {
            IntRangeConfigEntry newEntry = new IntRangeConfigEntry(this, key, minValue, maxValue, defaultValue);
            ENTRIES.add(newEntry);
            return newEntry;
        }

        /**
         * Makes an entry that holds a double value.
         *
         * @param key the name of the entry
         * @param defaultValue the default value of the entry
         * @return the config entry that was created
         */
        public ConfigEntry<Double> define(String key, double defaultValue) {
            ConfigEntry<Double> newEntry = new ConfigEntry<>(this, key, defaultValue);
            ENTRIES.add(newEntry);
            return newEntry;
        }

        /**
         * Makes an entry that holds a long value.
         *
         * @param key the name of the entry
         * @param defaultValue the default value of the entry
         * @return the config entry that was created
         */
        public ConfigEntry<Long> define(String key, long defaultValue) {
            ConfigEntry<Long> newEntry = new ConfigEntry<>(this, key, defaultValue);
            ENTRIES.add(newEntry);
            return newEntry;
        }

        /**
         * Makes an entry that holds a string value.
         *
         * @param key the name of the entry
         * @param defaultValue the default value of the entry
         * @return the config entry that was created
         */
        public ConfigEntry<String> define(String key, String defaultValue) {
            ConfigEntry<String> newEntry = new ConfigEntry<>(this, key, defaultValue);
            ENTRIES.add(newEntry);
            return newEntry;
        }

        /**
         * Build a new config file.
         * <p>
         * If the file already exist also load all its entries values.
         * Call this during the initialization phase of the game.
         * @throws IOException if the file is not found
         */
        public void build() throws IOException {
            File file = new File(this.file);
            if (file.exists()) {
                load();
            }
            save();
        }

        /**
         * Saves the current loaded values to the config file.
         * <p>
         * This is automatically called from the {@link #build()} method
         * but can also be called individually to save values when they
         * are changed during the execution of the game.
         * @throws IOException if it wasn't possible to write to the config file
         */
        public void save() throws IOException{
            FileWriter writer = new FileWriter(file);
            final String newline = System.lineSeparator(); // Wrap text

            // Write comments
            if (COMMENTS.size() > 0) {
                for (String s : COMMENTS) {
                    writer.write("#" + s + newline);
                }
            }

            writer.write(newline);
            writer.write("[Settings]" + newline);
            writer.write(newline);

            // Write config entries
            if (ENTRIES.size() > 0) {
                for (ConfigEntry entry : ENTRIES) {
                    String entrySeparator = "=";

                    if (entry.getComment() != null) {
                        writer.write("# " + entry.getComment() + newline);
                    }

                    if (entry instanceof IntRangeConfigEntry) {
                        writer.write("# Range: min=" + ((IntRangeConfigEntry) entry).getMinValue() +
                                ", max=" + ((IntRangeConfigEntry) entry).getMaxValue() + newline);
                    }

                    writer.write("# default = " + entry.getDefaultValue() + newline);
                    writer.write(entry.getKey() + entrySeparator + entry.getValue() + newline);
                    writer.write(newline);
                }
            }

            writer.flush();
            writer.close();
        }

        /*
         * Loads values from the config file
         */
        private void load() throws IOException {
            File file = new File(this.file);
            Scanner reader = new Scanner(file);
            for (int line = 1; reader.hasNextLine(); line++) {
                parseConfigEntry(reader.nextLine(), line);
            }
        }

        /*
         * Reads an entry from the config file and load its value
         */
        @SuppressWarnings("unchecked")
        private void parseConfigEntry(String string, int line) {
            if (isValueLine(string)) {
                String[] entryParts = string.split("=", 2);

                if (entryParts.length == 2) {
                    ConfigEntry configEntry = this.getConfigEntry(entryParts[0]);

                    if (configEntry != null) {

                        try {
                            if (configEntry.getValue() instanceof Boolean) {
                                configEntry.setValue(Boolean.parseBoolean(entryParts[1]));
                            } else if (configEntry.getValue() instanceof Integer) {
                                configEntry.setValue(Integer.parseInt(entryParts[1]));
                            } else if (configEntry.getValue() instanceof Double) {
                                configEntry.setValue(Double.parseDouble(entryParts[1]));
                            } else if (configEntry.getValue() instanceof Long) {
                                configEntry.setValue(Long.parseLong(entryParts[1]));
                            } else if (configEntry.getValue() instanceof String) {
                                configEntry.setValue(String.valueOf(entryParts[1]));
                            }
                        } catch (NumberFormatException e){
                            configEntry.setValue(configEntry.getDefaultValue());
                            //throw new IllegalConfigValueException("Invalid value type in config file \"" + this.getConfigFilePath() + "\" on line " + line);
                        }

                    } else {
                        throw new IllegalConfigValueException(
                                "Syntax error in config file \"" + this.getConfigFilePath() + "\" on line " + line);
                    }

                } else {
                    throw new IllegalConfigValueException(
                            "Syntax error in config file \"" + this.getConfigFilePath() + "\" on line " + line);
                }

            }

        }

        /*
         * Returns the config entry corresponding to the specified key
         * or null if the config entry wasn't found
         */
        private ConfigEntry<?> getConfigEntry(String key) {
            for (ConfigEntry entry : ENTRIES) {
                if (entry.getKey().equals(key)) {
                    return entry;
                }
            }

            return null;
        }

        /*
         * Check if the current line we are reading is an entry or a comment
         */
        private boolean isValueLine(String line) {
            if (line.isEmpty()) return false;
            else if (line.startsWith("#") || line.startsWith("[")) return false;
            return true;
        }

    }

    /**
     * An object used to save a config value in a
     * config file.
     */
    @SuppressWarnings("fieldCanBeLocal")
    public static class ConfigEntry<T> {

        private final Builder builder;
        private String comment;
        private final String key;
        private final T defaultValue;
        private T value;

        private ConfigEntry(Builder parent, String key, T defaultValue) {
            this.builder = parent;
            this.key = key;
            this.defaultValue = defaultValue;
        }

        /**
         * @return the builder that holds this entry
         */
        public Builder getBuilder() {
            return builder;
        }

        /**
         * @return the key of this entry
         */
        public String getKey() {
            return key;
        }

        /**
         * @return the default value of this entry
         */
        public T getDefaultValue() {
            return defaultValue;
        }

        /**
         * @return the current loaded value for this entry
         */
        public T getValue() {
            if (value == null || value.toString().isBlank()) {
                return defaultValue;
            }

            return value;
        }

        /**
         * Change the currently loaded value of this entry.
         * <p>
         * If this is called during the execution of the game, use
         * {@link Builder#save()} before the game close to save the
         * new value to the config file.
         * @param value the new value
         */
        public void setValue(T value) {
            this.value = value;
        }

        /**
         * Set the comment for this entry.
         * @param comment the comment to write before the entry
         * @return the config entry that was commented
         */
        public ConfigEntry<T> comment(String comment) {
            this.comment = comment;
            return this;
        }

        /*
         * Get the comment for this entry
         */
        private String getComment() {
            return comment;
        }

    }

    /**
     * An object used to save a config value that has to be
     * in a certain range.
     */
    public static class IntRangeConfigEntry extends ConfigEntry<Integer> {

        private final int minValue;
        private final int maxValue;

        private IntRangeConfigEntry(Builder parent, String key, int minValue, int maxValue, int defaultValue) {
            super(parent, key, defaultValue);
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        /**
         * @return the minimum value this entry can have
         */
        public int getMinValue() {
            return minValue;
        }

        /**
         * @return the maximum value this entry can have
         */
        public int getMaxValue() {
            return maxValue;
        }

        /**
         * Change the currently loaded value of this entry.
         * <p>
         * If the new value is out of the specified range for this entry
         * it's automatically set to the closest value inside the range.
         * <p>
         * If this is called during the execution of the game, use
         * {@link Builder#save()} before the game close to save the
         * new value to the config file.
         * @param value the new value
         */
        @Override
        public void setValue(Integer value) {
            if (value < minValue) {
                super.setValue(minValue);
            } else if (value > maxValue){
                super.setValue(maxValue);
            } else {
                super.setValue(value);
            }
        }

        /**
         * Set the comment for this entry.
         * @param comment the comment to write before the entry
         * @return the config entry that was commented
         */
        @Override
        public IntRangeConfigEntry comment(String comment) {
            super.comment(comment);
            return this;
        }

    }

    /**
     * An exception used to handle illegal values specified
     * in the config file.
     */
    public static class IllegalConfigValueException extends IllegalArgumentException {

        public IllegalConfigValueException() {
            super();
        }

        public IllegalConfigValueException(String s) {
            super(s);
        }

        public IllegalConfigValueException(String message, Throwable cause) {
            super(message, cause);
        }

        public IllegalConfigValueException(Throwable cause) {
            super(cause);
        }

    }

}
