package net.leafenzo.mint.mixin.compat.botania;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.leafenzo.mint.util.ModUtil;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import vazkii.botania.common.block.BotaniaBlocks;

@Pseudo
@Mixin(BotaniaBlocks.class)
public class BotaniaBlocksMixin {

    @ModifyExpressionValue(
            method = "*",
            at = @At(value = "INVOKE", target = "net/minecraft/util/DyeColor.values ()[Lnet/minecraft/util/DyeColor;")
    )
    private static DyeColor[] truncateDyeColors(DyeColor[] original) {
        // Use only vanilla dye colors, assuming that it expects nothing else to be there
        return ModUtil.VANILLA_DYE_COLORS;
    }

}