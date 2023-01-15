package org.infernalstudios.jsonentitymodelsextras.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.savage_and_ravage.client.model.GrieferArmorModel;
import com.teamabnormals.savage_and_ravage.common.item.GrieferArmorItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import org.infernalstudios.jsonentitymodels.client.render.ExtendedGeoReplacedEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoReplacedEntityRenderer;

import java.util.List;

@Pseudo
@Mixin(ExtendedGeoReplacedEntityRenderer.class)
public class ExtendedGeoReplacedEntityRendererMixin extends GeoReplacedEntityRenderer {

    public ExtendedGeoReplacedEntityRendererMixin(EntityRendererProvider.Context renderManager, AnimatedGeoModel modelProvider, IAnimatable animatable) {
        super(renderManager, modelProvider, animatable);
    }

    @Inject(method = "handleArmorRenderingForBone",
            at = @At(value = "INVOKE",
                    target = "Lorg/infernalstudios/jsonentitymodels/client/render/ExtendedGeoReplacedEntityRenderer;renderArmorOfItem(Lnet/minecraft/world/item/ArmorItem;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/model/geom/ModelPart;Lcom/mojang/blaze3d/vertex/PoseStack;II)V",
                    shift = At.Shift.BEFORE),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false,
            cancellable = true)
    private void jsonentitymodelsextras_renderGrieferArmor(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, ResourceLocation currentTexture, CallbackInfo ci, ItemStack armorForBone, EquipmentSlot boneSlot, ArmorItem armorItem, HumanoidModel armorModel, ModelPart sourceLimb, List cubeList, ResourceLocation armorResource) {
        if (armorItem instanceof GrieferArmorItem && armorModel instanceof GrieferArmorModel<?> grieferArmorModel) {
            VertexConsumer buffer = ItemRenderer.getArmorFoilBuffer(this.getCurrentRTB(), RenderType.armorCutoutNoCull(armorResource), false, armorForBone.hasFoil());

                if (bone.getName().startsWith("armorhead")) {
                    stack.pushPose();
                    grieferArmorModel.helmet.copyFrom(grieferArmorModel.head);
                    grieferArmorModel.helmet.render(stack, buffer, packedLightIn, packedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
                    stack.popPose();
                } else if (bone.getName().startsWith("armorbody")) {
                    stack.pushPose();
                    grieferArmorModel.chestplate1.copyFrom(grieferArmorModel.body);
                    grieferArmorModel.chestplate1.render(stack, buffer, packedLightIn, packedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
                    stack.popPose();
                } else if (bone.getName().startsWith("armorleftarm")) {
                    stack.pushPose();
                    grieferArmorModel.shoulderPadLeft.copyFrom(grieferArmorModel.leftArm);
                    grieferArmorModel.shoulderPadLeft.render(stack, buffer, packedLightIn, packedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
                    stack.popPose();
                } else if (bone.getName().startsWith("armorrightarm")) {
                    stack.pushPose();
                    grieferArmorModel.shoulderPadRight.copyFrom(grieferArmorModel.rightArm);
                    grieferArmorModel.shoulderPadRight.render(stack, buffer, packedLightIn, packedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
                    stack.popPose();
                } else if (bone.getName().startsWith("armorpelvis")) {
                    stack.pushPose();
                    stack.scale(1.01F, 1.0F, 1.01F);
                    grieferArmorModel.chestplate2.copyFrom(grieferArmorModel.body);
                    grieferArmorModel.chestplate2.render(stack, buffer, packedLightIn, packedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
                    stack.popPose();
                } else if (bone.getName().startsWith("armorleftleg")) {
                    stack.pushPose();
                    stack.scale(1.01F, 1.0F, 1.01F);
                    grieferArmorModel.leggingsLeft.copyFrom(grieferArmorModel.leftLeg);
                    grieferArmorModel.leggingsLeft.render(stack, buffer, packedLightIn, packedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
                    stack.popPose();
                } else if (bone.getName().startsWith("armorrightleg")) {
                    stack.pushPose();
                    stack.scale(1.01F, 1.0F, 1.01F);
                    grieferArmorModel.leggingsRight.copyFrom(grieferArmorModel.rightLeg);
                    grieferArmorModel.leggingsRight.render(stack, buffer, packedLightIn, packedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
                    stack.popPose();
                } else if (bone.getName().startsWith("armorrightfoot")) {
                    stack.pushPose();
                    stack.scale(1.05F, 1.0F, 1.05F);
                    grieferArmorModel.bootsRight.copyFrom(grieferArmorModel.rightLeg);
                    grieferArmorModel.bootsRight.render(stack, buffer, packedLightIn, packedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
                    stack.popPose();
                } else if (bone.getName().startsWith("armorleftfoot")) {
                    stack.pushPose();
                    stack.scale(1.05F, 1.0F, 1.05F);
                    grieferArmorModel.bootsLeft.copyFrom(grieferArmorModel.leftLeg);
                    grieferArmorModel.bootsLeft.render(stack, buffer, packedLightIn, packedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
                    stack.popPose();
                }

            stack.popPose();
            ci.cancel();
        }
    }
}
