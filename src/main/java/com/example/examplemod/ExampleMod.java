package com.example.examplemod;

import com.example.examplemod.gui.ClickGuiScreen;
import com.example.examplemod.gui.Watermark;
import com.example.examplemod.module.TargetHudModule;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ClientModInitializer {

 public static final String MOD_ID = "examplemod";
 public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

 public static KeyMapping openGuiKey;

 private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
   Identifier.fromNamespaceAndPath(MOD_ID, "main")
 );

 @Override
 public void onInitializeClient() {
  LOGGER.info("[{}] Hello from your mod! It loaded successfully.", MOD_ID);

  openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
    "key.examplemod.opengui",
    InputConstants.Type.KEYSYM,
    GLFW.GLFW_KEY_RIGHT_SHIFT,
    CATEGORY
  ));

  ClientTickEvents.END_CLIENT_TICK.register(client -> {
   while (openGuiKey.consumeClick()) {
    if (client.screen == null) {
     client.setScreen(new ClickGuiScreen());
    }
   }
  });

  HudRenderCallback.EVENT.register((guiGraphics, tickDelta) -> Watermark.render(guiGraphics));

  TargetHudModule.register();
 }
}
