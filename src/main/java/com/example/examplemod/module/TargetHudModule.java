package com.example.examplemod.module;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShapeRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class TargetHudModule {

 private static boolean enabled = false;

 public static boolean isEnabled() {
  return enabled;
 }

 public static void toggle() {
  enabled = !enabled;
 }

 public static void register() {
  WorldRenderEvents.AFTER_ENTITIES.register(TargetHudModule::onRender);
 }

 private static void onRender(WorldRenderContext context) {
  if (!enabled) {
   return;
  }

  Minecraft client = Minecraft.getInstance();
  if (client.level == null) {
   return;
  }

  // hitResult — это то, во что смотрит игрок сейчас (прицел)
  HitResult hit = client.hitResult;
  if (!(hit instanceof EntityHitResult entityHit)) {
   return;
  }

  Entity target = entityHit.getEntity();
  if (target == null) {
   return;
  }

  PoseStack poseStack = context.matrixStack();
  MultiBufferSource bufferSource = context.consumers();
  if (poseStack == null || bufferSource == null) {
   return;
  }

  Vec3 camPos = context.camera().getPosition();

  poseStack.pushPose();
  poseStack.translate(-camPos.x, -camPos.y, -camPos.z);

  AABB box = target.getBoundingBox().inflate(0.05);
  VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());
  ShapeRenderer.renderLineBox(poseStack.last(), vertexConsumer, box, 1.0f, 1.0f, 1.0f, 1.0f);

  poseStack.popPose();
 }
}
