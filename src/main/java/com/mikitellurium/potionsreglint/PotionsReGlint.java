package com.mikitellurium.potionsreglint;

import com.mikitellurium.potionsreglint.config.Configuration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PotionsReGlint implements ModInitializer {

	public static final String MOD_ID = "potions_re_glint";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		try {
			Configuration.registerConfig();
			LOGGER.info("Loaded Potion Re-Glint config");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		ClientLifecycleEvents.CLIENT_STOPPING.register((client -> {
			Configuration.MOD_CONFIG.save();
			LOGGER.info("Saved Potion Re-Glint config");
		}));
	}

}
