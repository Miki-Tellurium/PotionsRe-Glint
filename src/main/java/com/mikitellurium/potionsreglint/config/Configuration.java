package com.mikitellurium.potionsreglint.config;

import com.mikitellurium.potionsreglint.PotionsReGlint;
import com.mikitellurium.potionsreglint.api.TelluriumConfig;

import java.io.IOException;

public class Configuration {

    public static final TelluriumConfig.Builder MOD_CONFIG = new TelluriumConfig.Builder(PotionsReGlint.MOD_ID);
    public static TelluriumConfig.ConfigEntry<Boolean> ENABLE_POTION_GLINT;

    public static void registerConfig() throws IOException {
        MOD_CONFIG.comment("Potion Re-Glint Configuration");

        ENABLE_POTION_GLINT = MOD_CONFIG
                .comment("Enable enchantment glint on potions")
                .define("enablePotionEnchantmentGlint", true);

        MOD_CONFIG.build();
    }

}
