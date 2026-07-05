package net.augmentedduck.technocraft.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder{

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // TIN
        List<ItemLike> TIN_SMELTABLES = List.of(ModItems.TIN_RAW, ModItems.TIN_DUST, ModItems.TIN_CRUSHED, ModItems.TIN_WASHED);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TIN_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.TIN_INGOT.get())
            .unlockedBy("has_tin", has(ModItems.TIN_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 9)
            .requires(ModBlocks.TIN_BLOCK)
            .unlockedBy("has_tin_block", has(ModBlocks.TIN_BLOCK)).save(recipeOutput, "technocraft:tin_ingot_from_block");
        
        oreSmelting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.7f, 200, "tin_ingot");
        oreBlasting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.7f, 100, "tin_ingot");

        // SILVER
        List<ItemLike> SILVER_SMELTABLES = List.of(ModItems.SILVER_RAW, ModItems.SILVER_DUST, ModItems.SILVER_CRUSHED, ModItems.SILVER_WASHED);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SILVER_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.SILVER_INGOT.get())
            .unlockedBy("has_silver", has(ModItems.SILVER_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 9)
            .requires(ModBlocks.SILVER_BLOCK)
            .unlockedBy("has_silver_block", has(ModBlocks.SILVER_BLOCK)).save(recipeOutput, "technocraft:silver_ingot_from_block");
        
        oreSmelting(recipeOutput, SILVER_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 0.7f, 200, "silver_ingot");
        oreBlasting(recipeOutput, SILVER_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 0.7f, 100, "silver_ingot");

        // STEEL
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.STEEL_INGOT.get())
            .unlockedBy("has_steel", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 9)
            .requires(ModBlocks.STEEL_BLOCK)
            .unlockedBy("has_steel_block", has(ModBlocks.STEEL_BLOCK)).save(recipeOutput, "technocraft:steel_ingot_from_block");
        
        // LEAD
        List<ItemLike> LEAD_SMELTABLES = List.of(ModItems.LEAD_RAW, ModItems.LEAD_DUST, ModItems.LEAD_CRUSHED, ModItems.LEAD_WASHED);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LEAD_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.LEAD_INGOT.get())
            .unlockedBy("has_lead", has(ModItems.LEAD_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 9)
            .requires(ModBlocks.LEAD_BLOCK)
            .unlockedBy("has_lead_block", has(ModBlocks.LEAD_BLOCK)).save(recipeOutput, "technocraft:lead_ingot_from_block");
        
        oreSmelting(recipeOutput, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 0.7f, 200, "lead_ingot");
        oreBlasting(recipeOutput, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 0.7f, 100, "lead_ingot");
        
        // BRONZE
        List<ItemLike> BRONZE_SMELTABLES = List.of(ModItems.BRONZE_DUST);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRONZE_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.BRONZE_INGOT.get())
            .unlockedBy("has_bronze", has(ModItems.BRONZE_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 9)
            .requires(ModBlocks.BRONZE_BLOCK)
            .unlockedBy("has_bronze_block", has(ModBlocks.BRONZE_BLOCK)).save(recipeOutput, "technocraft:bronze_ingot_from_block");
        
        oreSmelting(recipeOutput, BRONZE_SMELTABLES, RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 0.7f, 200, "bronze_ingot");
        oreBlasting(recipeOutput, BRONZE_SMELTABLES, RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 0.7f, 100, "bronze_ingot");
        
        // COPPER
        List<ItemLike> COPPER_SMELTABLES = List.of(ModItems.COPPER_DUST, ModItems.COPPER_CRUSHED, ModItems.COPPER_WASHED);
        oreSmelting(recipeOutput, COPPER_SMELTABLES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7f, 200, "copper_ingot");
        oreBlasting(recipeOutput, COPPER_SMELTABLES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7f, 100, "copper_ingot");
        
        // GOLD
        List<ItemLike> GOLD_SMELTABLES = List.of(ModItems.GOLD_DUST, ModItems.GOLD_CRUSHED, ModItems.GOLD_WASHED);
        oreSmelting(recipeOutput, GOLD_SMELTABLES, RecipeCategory.MISC, Items.GOLD_INGOT, 0.7f, 200, "gold_ingot");
        oreBlasting(recipeOutput, GOLD_SMELTABLES, RecipeCategory.MISC, Items.GOLD_INGOT, 0.7f, 100, "gold_ingot");
        
        // IRON
        List<ItemLike> IRON_SMELTABLES = List.of(ModItems.IRON_DUST, ModItems.IRON_CRUSHED, ModItems.IRON_WASHED);
        oreSmelting(recipeOutput, IRON_SMELTABLES, RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 200, "iron_ingot");
        oreBlasting(recipeOutput, IRON_SMELTABLES, RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 100, "iron_ingot");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemLike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemLike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemLike), has(itemLike)).save(recipeOutput, Technocraft.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemLike));
        }
    }
}
