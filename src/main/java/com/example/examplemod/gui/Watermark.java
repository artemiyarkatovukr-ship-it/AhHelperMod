package com.example.examplemod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class Watermark {

 private static final String TEXT = "Rw Helper-Client";
 private static final int PADDING = 4;
 private static final int MARGIN = 4;
 private static final int BG_COLOR = 0xC0000000; // чёрный полупрозрачный фон
 private static final int TEXT_COLOR = 0xFFFFFF;  // белый текст

 public static void render(GuiGraphics guiGraphics) {
  Minecraft client = Minecraft.getInstance();
  int textWidth = client.font.width(TEXT);
  int textHeight = client.font.lineHeight;

  int left = MARGIN;
  int top = MARGIN;
  int right = left + textWidth + PADDING * 2;
  int bottom = top + textHeight + PADDING * 2;

  guiGraphics.fill(left, top, right, bottom, BG_COLOR);
  guiGraphics.drawString(client.font, TEXT, left + PADDING, top + PADDING, TEXT_COLOR);
 }
}
