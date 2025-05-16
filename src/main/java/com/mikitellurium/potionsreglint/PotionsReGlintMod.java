package com.mikitellurium.potionsreglint;

import com.mikitellurium.potionsreglint.config.Configuration;
import com.mikitellurium.potionsreglint.config.screen.ConfigScreen;
import com.mojang.logging.LogUtils;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import org.slf4j.Logger;

@Mod(PotionsReGlintMod.MOD_ID)
public class PotionsReGlintMod {
    public static final String MOD_ID = "potionsreglint";
    private static final Logger LOGGER = LogUtils.getLogger();

    public PotionsReGlintMod() {
        Configuration.registerConfig();
        init();
    }

    private static void init() {
        if (ModList.get().isLoaded("cloth_config")) {
            ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory(ConfigScreen::openScreen));
            LOGGER.info("ClothConfig found. Loaded in-game config menu");
        } else {
            LOGGER.info("ClothConfig not found. Could not load in-game config menu");
        }
    }

}
