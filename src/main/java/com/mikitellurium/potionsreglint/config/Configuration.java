package com.mikitellurium.potionsreglint.config;

import com.mikitellurium.potionsreglint.PotionsReGlintMod;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Configuration {
    private static final ModConfigSpec CLIENT_CONFIG = setupConfig();
    public static ModConfigSpec.BooleanValue ENABLE_POTION_GLINT;

    public static void register() {
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG);
    }

    private static ModConfigSpec setupConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push(PotionsReGlintMod.MOD_ID).comment("Turtle Charging Station Configuration");

        ENABLE_POTION_GLINT = builder
                .comment("Enable enchantment glint on potions")
                .define("enablePotionEnchantmentGlint", true);

        builder.pop();
        return builder.build();
    }

    public static ModConfigSpec getClient() {
        return CLIENT_CONFIG;
    }
}
