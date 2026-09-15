package com.example.examplemod;

import com.example.examplemod.gui.ClickGuiScreen;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ClientModInitializer {

 public static final String MOD_ID = "examplemod";
 public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

 public static KeyMapping openGuiKey;

 @Override
 public void onInitializeClient() {
  LOGGER.info("[{}] Hello from your mod! It loaded successfully.", MOD_ID);

  // Регистрируем клавишу Right Shift для открытия гуи
  openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
    "key.examplemod.opengui",
    InputConstants.Type.KEYSYM,
    GLFW.GLFW_KEY_RIGHT_SHIFT,
    "key.categories.examplemod"
  ));

  // Каждый тик проверяем, была ли нажата клавиша
  ClientTickEvents.END_CLIENT_TICK.register(client -> {
   while (openGuiKey.consumeClick()) {
    if (client.screen == null) {
     client.setScreen(new ClickGuiScreen());
    }
   }
  });
 }
}
