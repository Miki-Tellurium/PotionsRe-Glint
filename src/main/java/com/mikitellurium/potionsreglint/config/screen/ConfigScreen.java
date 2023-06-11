package com.mikitellurium.potionsreglint.config.screen;

import com.mikitellurium.potionsreglint.config.Configuration;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigScreen {

    public static Screen openScreen(Screen parent) {

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("config.potionsreglint.title"));

        ConfigCategory general = builder.getOrCreateCategory(Component.empty());

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startBooleanToggle(
                Component.translatable("config.potionsreglint.enablePotionGlint"), true)
                .setDefaultValue(true)
                .setSaveConsumer((newValue) -> Configuration.ENABLE_POTION_GLINT.set(newValue))
                .build());

        return builder.build();
    }

}
