package com.example.examplemod.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ClickGuiScreen extends Screen {

 public ClickGuiScreen() {
  super(Component.literal("ExampleMod ClickGui"));
 }

 @Override
 public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
  // Рисуем "Welcome" в левом верхнем углу, пока этот экран открыт
  guiGraphics.drawString(this.font, "Welcome", 10, 10, 0xFFFFFF);

  super.render(guiGraphics, mouseX, mouseY, partialTick);
 }

 @Override
 public boolean isPauseScreen() {
  // false = игра не ставится на паузу, пока гуи открыто (важно для мультиплеера/для клик-гуи)
  return false;
 }
}
