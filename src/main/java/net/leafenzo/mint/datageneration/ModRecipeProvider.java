package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.recipe.ModRecipeSerializer;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;


public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) { super(output); }
    public static void offerReversibleCompactingRecipes(Consumer<RecipeJsonProvider> exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, String compactingId, @Nullable String compactingGroup, String reverseId, @Nullable String reverseGroup) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9).input(compactItem).group(reverseGroup).criterion(RecipeProvider.hasItem(compactItem), RecipeProvider.conditionsFromItem(compactItem)).offerTo(exporter, new Identifier(reverseId+"_from_"+compactingId));
        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem).input(Character.valueOf('#'), baseItem).pattern("###").pattern("###").pattern("###").group(compactingGroup).criterion(RecipeProvider.hasItem(baseItem), RecipeProvider.conditionsFromItem(baseItem)).offerTo(exporter, new Identifier(compactingId+"_from_"+reverseId));
    }


    public static void offerDyeMixingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input1, ItemConvertible input2, RecipeCategory recipeCategory, int count) {
        Identifier outputId = Registries.ITEM.getId(output.asItem());
        Identifier input1Id = Registries.ITEM.getId(input1.asItem());
        Identifier input2Id = Registries.ITEM.getId(input2.asItem());

        if(input1 != input2) {
            ShapelessRecipeJsonBuilder.create(recipeCategory, output, count)
                    .input(input1)
                    .input(input2)
                    .group(outputId.getPath())
                    .criterion(FabricRecipeProvider.hasItem(input1), FabricRecipeProvider.conditionsFromItem(input1))
                    .criterion(FabricRecipeProvider.hasItem(input2), FabricRecipeProvider.conditionsFromItem(input2))
                    .offerTo(exporter, outputId.getPath() + "_from_" + input1Id.getPath() + "_and_" + input2Id.getPath());
        }
        else {
            ShapelessRecipeJsonBuilder.create(recipeCategory, output, count)
                    .input(input1)
                    .input(input2)
                    .group(outputId.getPath())
                    .criterion(FabricRecipeProvider.hasItem(input1), FabricRecipeProvider.conditionsFromItem(input1))
                    .offerTo(exporter, outputId.getPath() + "_from_" + input1Id.getPath() + "_and_" + input2Id.getPath());
        }
    }


    public static void offerCarpetRecipes(Consumer<RecipeJsonProvider> exporter, Block[] carpets, Block[] inputs) {
        if(carpets.length > inputs.length) { throw new RuntimeException();
//            throw new RuntimeException("More carpets than wools");
        }
        for(int i = 0; i < carpets.length; i++) {
//            if(carpets[i] == null) { throw new RuntimeException("Index with null value in carpets"); }
//            if(inputs[i] == null) { throw new RuntimeException("Index with null value in wools"); }
            offerCarpetRecipe(exporter, carpets[i], inputs[i]);
        }
    }
    public static void offerTerracottaDyeingRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerTerracottaDyeingRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerConcretePowderDyeingRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerConcretePowderDyeingRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerGlazedTerracottaSmeltingRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(inputs[i].asItem()), RecipeCategory.DECORATIONS, outputs[i].asItem(), 0.1f, 200)
                .criterion(FabricRecipeProvider.hasItem(inputs[i]), FabricRecipeProvider.conditionsFromItem(inputs[i].asItem()))
                .offerTo(exporter);
        }
    }
    public static void offerBannerRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerBannerRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerCandleDyeingRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerCandleDyeingRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerBedRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerBedRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerStainedGlassDyeingRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerStainedGlassDyeingRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerStainedGlassPaneRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerStainedGlassPaneRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerStainedGlassPaneDyeingRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerStainedGlassPaneDyeingRecipe(exporter, outputs[i], inputs[i]);
        }
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        // MINT - Special
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MINT_COOKIE, 4)
                .input(Items.WHEAT)
                .input(Items.WHEAT)
                .input(Items.COCOA_BEANS)
                .input(ModItems.MINT_SPRIG)
                .criterion(FabricRecipeProvider.hasItem(ModItems.MINT_SPRIG),
                        FabricRecipeProvider.conditionsFromItem(ModItems.MINT_SPRIG))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.MINT_COOKIE) + "_shapeless"));
        offerShapelessRecipe(exporter, ModItems.MINT_DYE, ModItems.MINT_SPRIG, RecipeCategory.MISC.getName(), 1);
        this.offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.MINT_SPRIG, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MINT_SPRIG_BLOCK);

        //Dyes from combining vanilla dyes
        this.offerDyeMixingRecipe(exporter, ModItems.MINT_DYE, Items.LIME_DYE, Items.LIGHT_BLUE_DYE, RecipeCategory.MISC, 2);
        this.offerDyeMixingRecipe(exporter, ModItems.PEACH_DYE, Items.PINK_DYE, Items.ORANGE_DYE, RecipeCategory.MISC, 2);
        this.offerDyeMixingRecipe(exporter, ModItems.PERIWINKLE_DYE, Items.PURPLE_DYE, Items.LIGHT_BLUE_DYE, RecipeCategory.MISC, 2);
        this.offerDyeMixingRecipe(exporter, ModItems.ARTICHOKE_DYE, Items.LIME_DYE, Items.YELLOW_DYE, RecipeCategory.MISC, 2);
        this.offerDyeMixingRecipe(exporter, ModItems.FUCHSIA_DYE, Items.MAGENTA_DYE, Items.RED_DYE, RecipeCategory.MISC, 2);
        this.offerDyeMixingRecipe(exporter, ModItems.VERMILION_DYE, Items.ORANGE_DYE, Items.RED_DYE, RecipeCategory.MISC, 2);
        this.offerDyeMixingRecipe(exporter, ModItems.SHAMROCK_DYE, Items.LIME_DYE, Items.LIME_DYE, RecipeCategory.MISC, 2);
        this.offerDyeMixingRecipe(exporter, ModItems.INDIGO_DYE, Items.BLUE_DYE, Items.PURPLE_DYE, RecipeCategory.MISC, 2);
        this.offerDyeMixingRecipe(exporter, ModItems.BANANA_DYE, Items.YELLOW_DYE, Items.WHITE_DYE, RecipeCategory.MISC, 2);

        // Main
//  WOOL_BLOCKS
        ComplexRecipeJsonBuilder.create(ModRecipeSerializer.WOOL_COLORING_RECIPE).offerTo(exporter, "wool_coloring_recipe");

//  CARPET_BLOCKS
        offerCarpetRecipes(exporter, ModBlocks.CARPET_BLOCKS, ModBlocks.WOOL_BLOCKS);
        ComplexRecipeJsonBuilder.create(ModRecipeSerializer.WOOL_CARPET_COLORING_RECIPE).offerTo(exporter, "wool_carpet_coloring_recipe");

//  TERRACOTTA_BLOCKS
        offerTerracottaDyeingRecipes(exporter, ModBlocks.TERRACOTTA_BLOCKS, ModItems.DYE_ITEMS);

//  CONCRETE_POWDER_BLOCKS
        offerConcretePowderDyeingRecipes(exporter, ModBlocks.CONCRETE_POWDER_BLOCKS, ModItems.DYE_ITEMS);

//  GLAZED_TERRACOTTA_BLOCKS
        offerGlazedTerracottaSmeltingRecipes(exporter, ModBlocks.GLAZED_TERRACOTTA_BLOCKS, ModBlocks.TERRACOTTA_BLOCKS);

//  STAINED_GLASS_BLOCKS
        offerStainedGlassDyeingRecipes(exporter, ModBlocks.STAINED_GLASS_BLOCKS, ModItems.DYE_ITEMS);

//  STAINED_GLASS_PANE_BLOCKS
        offerStainedGlassPaneRecipes(exporter, ModBlocks.STAINED_GLASS_PANE_BLOCKS, ModBlocks.STAINED_GLASS_BLOCKS);
        offerStainedGlassPaneDyeingRecipes(exporter, ModBlocks.STAINED_GLASS_PANE_BLOCKS, ModItems.DYE_ITEMS);

//  SHULKER_BOX_BLOCKS
        //Handled by the MIXIN to the serialized ShulkerBoxColoringRecipe

//  BED_BLOCKS
        offerBedRecipes(exporter, ModBlocks.BED_BLOCKS, ModBlocks.WOOL_BLOCKS);
        ComplexRecipeJsonBuilder.create(ModRecipeSerializer.BED_COLORING_RECIPE).offerTo(exporter, "bed_coloring_recipe");

//  CANDLE_BLOCKS
        offerCandleDyeingRecipes(exporter, ModBlocks.CANDLE_BLOCKS, ModItems.DYE_ITEMS);

//  BANNER_BLOCKS
        offerBannerRecipes(exporter, ModBlocks.BANNER_BLOCKS, ModBlocks.WOOL_BLOCKS);
    }
}