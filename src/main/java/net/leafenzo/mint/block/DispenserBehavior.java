/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/1.20.1/remappedSrc/net/hibiscus/naturespirit/NatureSpirit.java
 */


package net.leafenzo.mint.block;

import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.dispenser.ModBoatDispenserBehavior;
import net.leafenzo.mint.entity.EmberArrowEntity;
import net.leafenzo.mint.entity.ModBoatEntity;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.BlockPlacementDispenserBehavior;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class DispenserBehavior {
    public static void RegisterDispenserBehaviors() {
        ModInit.LOGGER.debug("Registering dispenser behaviors for " + Super.MOD_ID);

        //Directly get the blockItem for each one instead of using "ShulkerBoxBlock.get(dyeColor)"
        for(Block block : ModBlocks.SHULKER_BOX_BLOCKS) { DispenserBlock.registerBehavior(block.asItem(), new BlockPlacementDispenserBehavior()); }

        for(ModBoatEntity.ModBoat boat : ModBoatEntity.ModBoat.values()) {
            DispenserBlock.registerBehavior(boat.boat(), new ModBoatDispenserBehavior(boat, false));
            DispenserBlock.registerBehavior(boat.chestBoat(), new ModBoatDispenserBehavior(boat, true));
        }

        DispenserBlock.registerBehavior(ModItems.EMBER_ARROW, new ProjectileDispenserBehavior() {
            @Override
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                return new EmberArrowEntity(world, position.getX(), position.getY(), position.getZ());
            }
        });
    }
}
