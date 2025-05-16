package com.mikitellurium.potionsreglint;

import com.mikitellurium.potionsreglint.config.Configuration;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;

import java.io.IOException;

public class PotionsReGlintClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		try {
			Configuration.registerConfig();
			PotionsReGlint.LOGGER.info("Loaded Potion Re-Glint config");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> {
			try {
				Configuration.MOD_CONFIG.save();
				PotionsReGlint.LOGGER.info("Saved Potion Re-Glint config");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
}
