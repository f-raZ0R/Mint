package net.leafenzo.mint.mixin;

import com.google.common.collect.Maps;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity implements Shearable {
   @Unique private static final Identifier MINT_SHEEP_LOOT_TABLE = new Identifier(ID_KEY ,"entities/sheep/mint");

   @Shadow @Final private static Map<DyeColor, ItemConvertible> DROPS;
   @Shadow @Final private static TrackedData <Byte> COLOR;

   static {
      DROPS.put(ModDyeColor.MINT, ModBlocks.MINT_WOOL);
   }

   private SheepEntityMixin() {
      super(EntityType.PIG, null);
      throw new AssertionError();
   }

   /**
    * @reason Allowing >16 unique dye colors (128)
    * @author ADudeCalledLeo
    */
   @Overwrite
   public DyeColor getColor() {
      byte b = dataTracker.get(COLOR);
      return DyeColor.byId(b & 0x7F);
   }

   /**
    * @reason Allowing >16 unique dye colors (128)
    * @author ADudeCalledLeo
    */
   @Overwrite
   public void setColor(DyeColor color) {
      byte b = dataTracker.get(COLOR);
      dataTracker.set(COLOR, (byte) ((b & 0x80) | color.getId() % 0x7F));
   }

   /**
    * @reason Allowing >16 unique dye colors (128)
    * @author ADudeCalledLeo
    */
   @Overwrite
   public boolean isSheared() {
      return (dataTracker.get(COLOR) & 0x80) != 0;
   }

   /**
    * @reason Allowing >16 unique dye colors (128)
    * @author ADudeCalledLeo
    */
   @Overwrite
   public void setSheared(boolean sheared) {
      byte b = dataTracker.get(COLOR);
      dataTracker.set(COLOR, (byte) ((b & 0x7F) | (sheared ? 0x80 : 0)));
   }

   @Inject(method = "getLootTableId", at = @At("HEAD"), cancellable = true)
   public void getCustomLootTableIds(CallbackInfoReturnable <Identifier> cir) {
      DyeColor color = getColor();
      if (color == ModDyeColor.MINT)
         cir.setReturnValue(MINT_SHEEP_LOOT_TABLE);
   }
}
