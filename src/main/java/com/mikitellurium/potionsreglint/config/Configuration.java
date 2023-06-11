package com.mikitellurium.potionsreglint.config;

import com.mikitellurium.potionsreglint.PotionsReGlintMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Configuration {

    public static ForgeConfigSpec.BooleanValue ENABLE_POTION_GLINT;

    public static void registerConfig() {
        ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();
        setupConfig(CONFIG_BUILDER);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CONFIG_BUILDER.build());
    }

    public static void setupConfig(ForgeConfigSpec.Builder CONFIG_BUILDER) {
        CONFIG_BUILDER.comment("Potion Re-Glint Configuration").push(PotionsReGlintMod.MOD_ID);

        ENABLE_POTION_GLINT = CONFIG_BUILDER
                .comment("Enable enchantment glint on potions")
                .define("enablePotionEnchantmentGlint", true);

        CONFIG_BUILDER.pop();
    }

}
