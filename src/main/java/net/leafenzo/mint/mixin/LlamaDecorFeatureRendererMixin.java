package net.leafenzo.mint.mixin;

// SOURCES:
// Dodo Gang - https://github.com/dodogang/marbles/blob/a49fc890db99275919d57cc3064b8b429b1a8eb6/src/main/java/net/dodogang/marbles/mixin/client/render/LlamaDecorFeatureRendererMixin.java

import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.util.ElsDyeModDyeColor;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.LlamaDecorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.HashMap;

//@Debug(export = true)
@Mixin(LlamaDecorFeatureRenderer.class)
//@Environment(value= EnvType.CLIENT)
public abstract class LlamaDecorFeatureRendererMixin extends FeatureRenderer<LlamaEntity, LlamaEntityModel<LlamaEntity>> {
    public LlamaDecorFeatureRendererMixin(FeatureRendererContext<LlamaEntity, LlamaEntityModel<LlamaEntity>> context, EntityModelLoader loader) {
        super(context);
        //this.model = new LlamaEntityModel(loader.getModelPart(EntityModelLayers.LLAMA_DECOR));
    }
    @Shadow @Final private LlamaEntityModel<LlamaEntity> model;

    @Unique private static final HashMap<DyeColor, Identifier> LLAMA_DECOR = new HashMap<DyeColor, Identifier>(); static {{
        LLAMA_DECOR.put(ElsDyeModDyeColor.MINT, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/mint.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.PEACH, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/peach.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.PERIWINKLE, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/periwinkle.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.ARTICHOKE, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/artichoke.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.FUCHSIA, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/fuchsia.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.VERMILION, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/vermilion.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.SHAMROCK, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/shamrock.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.INDIGO, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/indigo.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.BANANA, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/banana.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.CERULEAN, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/cerulean.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.ACORN, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/acorn.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.MAUVE, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/mauve.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.MAROON, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/maroon.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.GRAPE, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/grape.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.NAVY, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/navy.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.SAP, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/sap.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.AMBER, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/amber.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.SAGE, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/sage.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.VELVET, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/velvet.png"));
        LLAMA_DECOR.put(ElsDyeModDyeColor.MOLD, new Identifier(ElsDyeMod.MOD_ID, "textures/entity/llama/decor/mold.png"));
    }}
//    @Unique private static Identifier getLlamaDecorByColor(DyeColor color) {
//        return LLAMA_DECOR.get(color);
//    }
     /**
      * @reason Replaces the llama's carpet texture with the one matching the new dye color if applicable
      */
    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, LlamaEntity llamaEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        Identifier identifier;
        DyeColor dyeColor = llamaEntity.getCarpetColor();
        if (dyeColor != null && Arrays.stream(ElsDyeModDyeColor.VALUES).anyMatch(x -> x == dyeColor)) {
            identifier = LLAMA_DECOR.get(dyeColor);

            LlamaDecorFeatureRenderer $this = LlamaDecorFeatureRenderer.class.cast(this);
            $this.getContextModel().copyStateTo(this.model);
            this.model.setAngles(llamaEntity, f, g, j, k, l);
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(identifier));
            //this.model.render(matrixStack, vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(identifier)), i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
            ci.cancel();
        }
    }

    //    /**
//     * @reason Replaces the llama's carpet decor with the pollen-graced carpet decor texture if it is worn.
//     */
//    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
//    private void replaceCarpet(MatrixStack matrices, VertexConsumerProvider vertices, int light, LlamaEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
//        ItemStack stack = ((LlamaEntityAccess) entity).getSaddleItem();
//        if (stack.getItem() == MarblesBlocks.POLLEN_GRACED_CARPET.asItem()) {
//            LlamaDecorFeatureRenderer $this = LlamaDecorFeatureRenderer.class.cast(this);
//
//            $this.getContextModel().copyStateTo(this.model);
//            this.model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
//
//            this.model.render(matrices, vertices.getBuffer(RenderLayer.getEntityCutoutNoCull(Util.POLLEN_GRACED_CARPET_LLAMA_DECOR_TEXTURE)), light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
//
//            ci.cancel();
//        }
//    }
}
