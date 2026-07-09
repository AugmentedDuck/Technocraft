package net.augmentedduck.technocraft.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.item.ModItems;
import net.augmentedduck.technocraft.recipe.custom.MacerationRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TIN_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.TIN_INGOT.get())
            .unlockedBy("has_tin", has(ModItems.TIN_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TIN_DUST.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.TIN_TINY_DUST.get())
            .unlockedBy("has_tin_tiny_dust", has(ModItems.TIN_TINY_DUST)).save(recipeOutput);
            
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 9)
            .requires(ModBlocks.TIN_BLOCK)
            .unlockedBy("has_tin_block", has(ModBlocks.TIN_BLOCK)).save(recipeOutput, "technocraft:tin_ingot_from_block");
            
        List<ItemLike> TIN_SMELTABLES = List.of(ModItems.TIN_RAW, ModItems.TIN_DUST, ModItems.TIN_CRUSHED, ModItems.TIN_WASHED, ModBlocks.TIN_ORE, ModBlocks.TIN_DEEPSLATE_ORE);
        oreSmelting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.7f, 200, "tin_ingot");
        oreBlasting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.7f, 100, "tin_ingot");

        oreMacerating(recipeOutput, List.of(ModItems.TIN_RAW, ModBlocks.TIN_ORE, ModBlocks.TIN_DEEPSLATE_ORE), ModItems.TIN_CRUSHED.get(), 2, "tin_crushed");

        // SILVER
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SILVER_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.SILVER_INGOT.get())
            .unlockedBy("has_silver", has(ModItems.SILVER_INGOT)).save(recipeOutput);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SILVER_DUST.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.SILVER_TINY_DUST.get())
            .unlockedBy("has_silver_tiny_dust", has(ModItems.SILVER_TINY_DUST)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 9)
            .requires(ModBlocks.SILVER_BLOCK)
            .unlockedBy("has_silver_block", has(ModBlocks.SILVER_BLOCK)).save(recipeOutput, "technocraft:silver_ingot_from_block");
        
        List<ItemLike> SILVER_SMELTABLES = List.of(ModItems.SILVER_RAW, ModItems.SILVER_DUST, ModItems.SILVER_CRUSHED, ModItems.SILVER_WASHED, ModBlocks.SILVER_ORE, ModBlocks.SILVER_DEEPSLATE_ORE);
        oreSmelting(recipeOutput, SILVER_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 0.7f, 200, "silver_ingot");
        oreBlasting(recipeOutput, SILVER_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 0.7f, 100, "silver_ingot");

        oreMacerating(recipeOutput, List.of(ModItems.SILVER_RAW, ModBlocks.SILVER_ORE, ModBlocks.SILVER_DEEPSLATE_ORE), ModItems.SILVER_CRUSHED.get(), 2, "silver_crushed");

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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LEAD_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.LEAD_INGOT.get())
            .unlockedBy("has_lead", has(ModItems.LEAD_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LEAD_DUST.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.LEAD_TINY_DUST.get())
            .unlockedBy("has_lead_tiny_dust", has(ModItems.LEAD_TINY_DUST)).save(recipeOutput);
        
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 9)
            .requires(ModBlocks.LEAD_BLOCK)
            .unlockedBy("has_lead_block", has(ModBlocks.LEAD_BLOCK)).save(recipeOutput, "technocraft:lead_ingot_from_block");
        
        List<ItemLike> LEAD_SMELTABLES = List.of(ModItems.LEAD_RAW, ModItems.LEAD_DUST, ModItems.LEAD_CRUSHED, ModItems.LEAD_WASHED, ModBlocks.LEAD_ORE, ModBlocks.LEAD_DEEPSLATE_ORE);
        oreSmelting(recipeOutput, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 0.7f, 200, "lead_ingot");
        oreBlasting(recipeOutput, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 0.7f, 100, "lead_ingot");

        oreMacerating(recipeOutput, List.of(ModItems.LEAD_RAW, ModBlocks.LEAD_ORE, ModBlocks.LEAD_DEEPSLATE_ORE), ModItems.LEAD_CRUSHED.get(), 2, "lead_crushed");
        
        // BRONZE
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRONZE_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.BRONZE_INGOT.get())
            .unlockedBy("has_bronze", has(ModItems.BRONZE_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 9)
            .requires(ModBlocks.BRONZE_BLOCK)
            .unlockedBy("has_bronze_block", has(ModBlocks.BRONZE_BLOCK)).save(recipeOutput, "technocraft:bronze_ingot_from_block");
        
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BRONZE_DUST.get(), 1)
            .requires(ModItems.COPPER_DUST, 3)
            .requires(ModItems.TIN_DUST)
            .unlockedBy("has_copper_dust", has(ModItems.COPPER_DUST)).save(recipeOutput);

        List<ItemLike> BRONZE_SMELTABLES = List.of(ModItems.BRONZE_DUST);
        oreSmelting(recipeOutput, BRONZE_SMELTABLES, RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 0.7f, 200, "bronze_ingot");
        oreBlasting(recipeOutput, BRONZE_SMELTABLES, RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 0.7f, 100, "bronze_ingot");
        
        // COPPER      
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_DUST.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.COPPER_TINY_DUST.get())
            .unlockedBy("has_copper_tiny_dust", has(ModItems.COPPER_TINY_DUST)).save(recipeOutput);
        
        List<ItemLike> COPPER_SMELTABLES = List.of(ModItems.COPPER_DUST, ModItems.COPPER_CRUSHED, ModItems.COPPER_WASHED);
        oreSmelting(recipeOutput, COPPER_SMELTABLES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7f, 200, "copper_ingot");
        oreBlasting(recipeOutput, COPPER_SMELTABLES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7f, 100, "copper_ingot");
        
        oreMacerating(recipeOutput, List.of(Items.RAW_COPPER, Items.COPPER_ORE, Items.DEEPSLATE_COPPER_ORE), ModItems.COPPER_CRUSHED.get(), 2, "copper_crushed");
        
        // GOLD
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_DUST.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.GOLD_TINY_DUST.get())
            .unlockedBy("has_gold_tiny_dust", has(ModItems.GOLD_TINY_DUST)).save(recipeOutput);

        List<ItemLike> GOLD_SMELTABLES = List.of(ModItems.GOLD_DUST, ModItems.GOLD_CRUSHED, ModItems.GOLD_WASHED);
        oreSmelting(recipeOutput, GOLD_SMELTABLES, RecipeCategory.MISC, Items.GOLD_INGOT, 0.7f, 200, "gold_ingot");
        oreBlasting(recipeOutput, GOLD_SMELTABLES, RecipeCategory.MISC, Items.GOLD_INGOT, 0.7f, 100, "gold_ingot");

        oreMacerating(recipeOutput, List.of(Items.RAW_GOLD, Items.GOLD_ORE, Items.DEEPSLATE_GOLD_ORE), ModItems.GOLD_CRUSHED.get(), 2, "gold_crushed");
        
        // IRON
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_DUST.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.IRON_TINY_DUST.get())
            .unlockedBy("has_iron_tiny_dust", has(ModItems.IRON_TINY_DUST)).save(recipeOutput);
        
        List<ItemLike> IRON_SMELTABLES = List.of(ModItems.IRON_DUST, ModItems.IRON_CRUSHED, ModItems.IRON_WASHED);
        oreSmelting(recipeOutput, IRON_SMELTABLES, RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 200, "iron_ingot");
        oreBlasting(recipeOutput, IRON_SMELTABLES, RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 100, "iron_ingot");

        oreMacerating(recipeOutput, List.of(Items.RAW_IRON, Items.IRON_ORE, Items.DEEPSLATE_IRON_ORE), ModItems.IRON_CRUSHED.get(), 2, "iron_crushed");
    }

    /** Registers a smelting recipe for every ingredient in pIngredients -> pResult (one recipe file per ingredient). */
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    /** Blasting counterpart of {@link #oreSmelting} */
    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreMacerating(RecipeOutput recipeOutput, ItemLike ingredient, ItemLike result, int count ,String group) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "macerating/" + getItemName(result) + "_from_" + getItemName(ingredient));
        recipeOutput.accept(id, new MacerationRecipe(Ingredient.of(ingredient), new ItemStack(result, count)), null);
    }

    protected static void oreMacerating(RecipeOutput recipeOutput, List<ItemLike> ingredients, ItemLike result, int count, String group) {
        for (ItemLike ingredient : ingredients) {
            oreMacerating(recipeOutput, ingredient, result, count, group);
        }
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemLike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemLike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemLike), has(itemLike)).save(recipeOutput, Technocraft.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemLike));
        }
    }
}
