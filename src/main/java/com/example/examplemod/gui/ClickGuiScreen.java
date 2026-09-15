package com.example.examplemod.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ClickGuiScreen extends Screen {

 private static final int PANEL_WIDTH = 300;
 private static final int PANEL_HEIGHT = 200;
 private static final int CORNER_RADIUS = 8;
 private static final int PANEL_COLOR = 0xE0101010; // почти чёрный, слегка прозрачный

 public ClickGuiScreen() {
  super(Component.literal("ExampleMod ClickGui"));
 }

 @Override
 public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
  int centerX = this.width / 2;
  int centerY = this.height / 2;
  int left = centerX - PANEL_WIDTH / 2;
  int top = centerY - PANEL_HEIGHT / 2;
  int right = centerX + PANEL_WIDTH / 2;
  int bottom = centerY + PANEL_HEIGHT / 2;

  drawRoundedRect(guiGraphics, left, top, right, bottom, CORNER_RADIUS, PANEL_COLOR);

  super.render(guiGraphics, mouseX, mouseY, partialTick);
 }

 // Рисуем прямоугольник со скруглёнными углами: основной блок + 4 "среза" по углам
 private void drawRoundedRect(GuiGraphics guiGraphics, int left, int top, int right, int bottom, int radius, int color) {
  // Центральная часть (без углов)
  guiGraphics.fill(left + radius, top, right - radius, bottom, color);
  guiGraphics.fill(left, top + radius, right, bottom - radius, color);

  // Скруглённые углы (простая аппроксимация через несколько тонких полосок)
  for (int i = 0; i < radius; i++) {
   int dx = (int) Math.sqrt((double) (radius * radius - (radius - i) * (radius - i)));
   // верхний левый
   guiGraphics.fill(left + radius - dx, top + i, left + radius, top + i + 1, color);
   // верхний правый
   guiGraphics.fill(right - radius, top + i, right - radius + dx, top + i + 1, color);
   // нижний левый
   guiGraphics.fill(left + radius - dx, bottom - i - 1, left + radius, bottom - i, color);
   // нижний правый
   guiGraphics.fill(right - radius, bottom - i - 1, right - radius + dx, bottom - i, color);
  }
 }

 @Override
 public boolean isPauseScreen() {
  return false;
 }
}
