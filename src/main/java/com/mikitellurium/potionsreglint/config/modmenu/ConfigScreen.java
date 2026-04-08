package com.mikitellurium.potionsreglint.config.modmenu;

import com.mikitellurium.potionsreglint.PotionsReGlint;
import com.mikitellurium.potionsreglint.config.Configuration;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.io.IOException;

public class ConfigScreen {
    protected static Screen openScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("config.potionsreglint.title"));

        ConfigCategory general = builder.getOrCreateCategory(Component.empty());

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.translatable("config.potionsreglint.enablePotionGlint"), Configuration.ENABLE_POTION_GLINT.getValue())
                .setDefaultValue(true)
                .setSaveConsumer((newValue) -> Configuration.ENABLE_POTION_GLINT.setValue(newValue))
                .build());

        builder.setSavingRunnable(() -> {
            try {
                Configuration.MOD_CONFIG.save();
                PotionsReGlint.LOGGER.info("Saved Potion Re-Glint config");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return builder.build();
    }
}
