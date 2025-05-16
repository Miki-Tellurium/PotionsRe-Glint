package com.mikitellurium.potionsreglint.config;

import com.mikitellurium.potionsreglint.PotionsReGlintMod;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Configuration {
    public static ModConfigSpec.BooleanValue ENABLE_POTION_GLINT;

    public static void registerConfig() {
        ModConfigSpec.Builder CONFIG_BUILDER = new ModConfigSpec.Builder();
        setupConfig(CONFIG_BUILDER);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CONFIG_BUILDER.build());
    }

    public static void setupConfig(ModConfigSpec.Builder CONFIG_BUILDER) {
        CONFIG_BUILDER.comment("Potion Re-Glint Configuration").push(PotionsReGlintMod.MOD_ID);

        ENABLE_POTION_GLINT = CONFIG_BUILDER
                .comment("Enable enchantment glint on potions")
                .define("enablePotionEnchantmentGlint", true);

        CONFIG_BUILDER.pop();
    }
}
