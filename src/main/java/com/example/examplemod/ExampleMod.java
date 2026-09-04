package com.example.examplemod;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {

 public static final String MOD_ID = "examplemod";
 public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

 @Override
 public void onInitialize() {
  LOGGER.info("[{}] Hello from your mod! It loaded successfully.", MOD_ID);
 }
}
