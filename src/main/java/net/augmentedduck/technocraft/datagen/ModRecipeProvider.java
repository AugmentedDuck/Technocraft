package net.augmentedduck.technocraft.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.item.ModItems;
import net.augmentedduck.technocraft.recipe.custom.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder{

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ///////////////////////////////////////////////////
        /// 
        /// TIN
        /// 
        ///////////////////////////////////////////////////
        
        // TIN BLOCK
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TIN_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.TIN_INGOT.get())
            .unlockedBy("has_tin", has(ModItems.TIN_INGOT)).save(recipeOutput);

        compressing(recipeOutput, ModItems.TIN_INGOT.get(), 9, ModBlocks.TIN_BLOCK.get(), 1, "tin_block");

        // TIN DUST
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TIN_DUST.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.TIN_TINY_DUST.get())
            .unlockedBy("has_tin_tiny_dust", has(ModItems.TIN_TINY_DUST)).save(recipeOutput);
        
        compressing(recipeOutput, ModItems.TIN_TINY_DUST, 9, ModItems.TIN_DUST, 1, "tin_dust");
        macerating(recipeOutput, ModItems.TIN_DENSE_PLATE, ModItems.TIN_DUST, 9, "tin_dust");
        macerating(recipeOutput, List.of(ModItems.TIN_WASHED, ModItems.TIN_PLATE, ModItems.TIN_INGOT, ModItems.TIN_CRUSHED), ModItems.TIN_DUST, 1, "tin_dust");
        // 2 EMPTY CAN [MACERATING] DUST

        // TIN INGOT
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 9)
            .requires(ModBlocks.TIN_BLOCK)
            .unlockedBy("has_tin_block", has(ModBlocks.TIN_BLOCK)).save(recipeOutput, "technocraft:tin_ingot_from_block");
            
        List<ItemLike> TIN_SMELTABLES = List.of(ModItems.TIN_RAW, ModItems.TIN_DUST, ModItems.TIN_CRUSHED, ModItems.TIN_WASHED, ModBlocks.TIN_ORE, ModBlocks.TIN_DEEPSLATE_ORE);
        oreSmelting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.7f, 200, "tin_ingot");
        oreBlasting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.7f, 100, "tin_ingot");

        // CUSHED TIN
        macerating(recipeOutput, List.of(ModItems.TIN_RAW, ModBlocks.TIN_ORE, ModBlocks.TIN_DEEPSLATE_ORE), ModItems.TIN_CRUSHED.get(), 2, "tin_crushed");

        // DENSE PLATE
        compressing(recipeOutput, ModItems.TIN_PLATE.get(), 9, ModItems.TIN_DENSE_PLATE.get(), 1, "tin_dense_plate");

        // TIN CAN
        // FILLED CAN [EXTRACTING] EMPTY CAN
        // ITEM CASING [EXTRUDING] EMPTY CAN

        // CABLE
        extruding(recipeOutput, ModItems.TIN_INGOT, ModBlocks.TIN_CABLE_BLOCK, 3, "tin_cable");

        // ITEM CASING
        rolling(recipeOutput, ModItems.TIN_PLATE, ModItems.TIN_CASING, 2, "tin_casing");

        // PLATE
        rolling(recipeOutput, ModItems.TIN_INGOT, ModItems.TIN_PLATE, 1, "tin_plate");
        hammering(recipeOutput, ModItems.TIN_INGOT.get(), ModItems.TIN_PLATE, 1, "tin_plate");

        // TODO TIN: HYDRATED DUST + EMPTY CAN + FILLED CAN

        ///////////////////////////////////////////////////
        /// 
        /// SILVER
        /// 
        ///////////////////////////////////////////////////
        
        // SILVER BLOCK
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SILVER_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.SILVER_INGOT.get())
            .unlockedBy("has_silver", has(ModItems.SILVER_INGOT)).save(recipeOutput);
        
        compressing(recipeOutput, ModItems.SILVER_INGOT.get(), 9, ModBlocks.SILVER_BLOCK.get(), 1, "silver_block");
        
        
        // SILVER DUST
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SILVER_DUST.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.SILVER_TINY_DUST.get())
            .unlockedBy("has_silver_tiny_dust", has(ModItems.SILVER_TINY_DUST)).save(recipeOutput);

        compressing(recipeOutput, ModItems.SILVER_TINY_DUST, 9, ModItems.SILVER_DUST, 1, "silver_dust");
        macerating(recipeOutput, ModItems.SILVER_DENSE_PLATE, ModItems.SILVER_DUST, 9, "silver_dust");
        macerating(recipeOutput, List.of(ModItems.SILVER_WASHED, ModItems.SILVER_PLATE, ModItems.SILVER_INGOT, ModItems.SILVER_CRUSHED), ModItems.SILVER_DUST, 1, "silver_dust");


        // SILVER INGOT
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 9)
            .requires(ModBlocks.SILVER_BLOCK)
            .unlockedBy("has_silver_block", has(ModBlocks.SILVER_BLOCK)).save(recipeOutput, "technocraft:silver_ingot_from_block");
        
        List<ItemLike> SILVER_SMELTABLES = List.of(ModItems.SILVER_RAW, ModItems.SILVER_DUST, ModItems.SILVER_CRUSHED, ModItems.SILVER_WASHED, ModBlocks.SILVER_ORE, ModBlocks.SILVER_DEEPSLATE_ORE);
        oreSmelting(recipeOutput, SILVER_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 0.7f, 200, "silver_ingot");
        oreBlasting(recipeOutput, SILVER_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 0.7f, 100, "silver_ingot");

        // CRUSHED SILVER
        macerating(recipeOutput, List.of(ModItems.SILVER_RAW, ModBlocks.SILVER_ORE, ModBlocks.SILVER_DEEPSLATE_ORE), ModItems.SILVER_CRUSHED.get(), 2, "silver_crushed");

        // DENSE PLATE
        compressing(recipeOutput, ModItems.SILVER_PLATE.get(), 9, ModItems.SILVER_DENSE_PLATE.get(), 1, "silver_dense_plate");
        
        // CABLE
        extruding(recipeOutput, ModItems.SILVER_INGOT, ModBlocks.SILVER_CABLE_BLOCK, 3, "silver_cable");
        
        // ITEM CASING
        rolling(recipeOutput, ModItems.SILVER_PLATE, ModItems.SILVER_CASING, 2, "silver_casing");

        // PLATE
        rolling(recipeOutput, ModItems.SILVER_INGOT, ModItems.SILVER_PLATE, 1, "silver_plate");

        ///////////////////////////////////////////////////
        /// 
        /// STEEL
        /// 
        ///////////////////////////////////////////////////

        // STEEL BLOCK
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.STEEL_INGOT.get())
            .unlockedBy("has_steel", has(ModItems.STEEL_INGOT)).save(recipeOutput);
        
        compressing(recipeOutput, ModItems.STEEL_INGOT.get(), 9, ModBlocks.STEEL_BLOCK.get(), 1, "steel_block");

        // STEEL INGOT
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 9)
            .requires(ModBlocks.STEEL_BLOCK)
            .unlockedBy("has_steel_block", has(ModBlocks.STEEL_BLOCK)).save(recipeOutput, "technocraft:steel_ingot_from_block");

        // DENSE PLATE
        compressing(recipeOutput, ModItems.STEEL_PLATE.get(), 9, ModItems.STEEL_DENSE_PLATE.get(), 1, "steel_dense_plate");

        // SHAFT
        // BLOCK [EXTRUDING] SHAFT

        // ITEM CASING
        rolling(recipeOutput, ModItems.STEEL_PLATE, ModItems.STEEL_CASING, 2, "steel_casing");

        // PLATE
        rolling(recipeOutput, ModItems.STEEL_INGOT, ModItems.STEEL_PLATE, 1, "steel_plate");

        // TODO STEEL: SHAFT

        ///////////////////////////////////////////////////
        /// 
        /// LEAD
        /// 
        ///////////////////////////////////////////////////
        
        // LEAD BLOCK
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LEAD_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.LEAD_INGOT.get())
            .unlockedBy("has_lead", has(ModItems.LEAD_INGOT)).save(recipeOutput);

        compressing(recipeOutput, ModItems.LEAD_INGOT.get(), 9, ModBlocks.LEAD_BLOCK.get(), 1, "lead_block");

        // LEAD DUST
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LEAD_DUST.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.LEAD_TINY_DUST.get())
            .unlockedBy("has_lead_tiny_dust", has(ModItems.LEAD_TINY_DUST)).save(recipeOutput);
        
        compressing(recipeOutput, ModItems.LEAD_TINY_DUST, 9, ModItems.LEAD_DUST, 1, "lead_dust");
        macerating(recipeOutput, ModItems.LEAD_DENSE_PLATE, ModItems.LEAD_DUST, 9, "lead_dust");
        macerating(recipeOutput, List.of(ModItems.LEAD_WASHED, ModItems.LEAD_PLATE, ModItems.LEAD_INGOT, ModItems.LEAD_CRUSHED), ModItems.LEAD_DUST, 1, "lead_dust");
        
        // LEAD INGOT
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 9)
            .requires(ModBlocks.LEAD_BLOCK)
            .unlockedBy("has_lead_block", has(ModBlocks.LEAD_BLOCK)).save(recipeOutput, "technocraft:lead_ingot_from_block");
        
        List<ItemLike> LEAD_SMELTABLES = List.of(ModItems.LEAD_RAW, ModItems.LEAD_DUST, ModItems.LEAD_CRUSHED, ModItems.LEAD_WASHED, ModBlocks.LEAD_ORE, ModBlocks.LEAD_DEEPSLATE_ORE);
        oreSmelting(recipeOutput, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 0.7f, 200, "lead_ingot");
        oreBlasting(recipeOutput, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 0.7f, 100, "lead_ingot");

        // CRUSHED LEAD
        macerating(recipeOutput, List.of(ModItems.LEAD_RAW, ModBlocks.LEAD_ORE, ModBlocks.LEAD_DEEPSLATE_ORE), ModItems.LEAD_CRUSHED.get(), 2, "lead_crushed");
        
        // DENSE PLATE
        compressing(recipeOutput, ModItems.LEAD_PLATE.get(), 9, ModItems.LEAD_DENSE_PLATE.get(), 1, "lead_dense_plate");

        // ITEM CASING
        rolling(recipeOutput, ModItems.LEAD_PLATE, ModItems.LEAD_CASING, 2, "lead_casing");

        // PLATE
        rolling(recipeOutput, ModItems.LEAD_INGOT, ModItems.LEAD_PLATE, 1, "lead_plate");
        ///////////////////////////////////////////////////
        /// 
        /// BRONZE
        /// 
        ///////////////////////////////////////////////////

        // BRONZE BLOCK
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRONZE_BLOCK.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.BRONZE_INGOT.get())
            .unlockedBy("has_bronze", has(ModItems.BRONZE_INGOT)).save(recipeOutput);

        compressing(recipeOutput, ModItems.BRONZE_INGOT.get(), 9, ModBlocks.BRONZE_BLOCK.get(), 1, "bronze_block");

        // BRONZE DUST
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BRONZE_DUST.get(), 1)
            .requires(ModItems.COPPER_DUST, 3)
            .requires(ModItems.TIN_DUST)
            .unlockedBy("has_copper_dust", has(ModItems.COPPER_DUST)).save(recipeOutput);

        macerating(recipeOutput, ModItems.BRONZE_DENSE_PLATE, ModItems.BRONZE_DUST, 9, "bronze_dust");
        macerating(recipeOutput, List.of(ModItems.BRONZE_PLATE, ModItems.BRONZE_INGOT), ModItems.BRONZE_DUST, 1, "bronze_dust");
        
        // BRONZE INGOT
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 9)
            .requires(ModBlocks.BRONZE_BLOCK)
            .unlockedBy("has_bronze_block", has(ModBlocks.BRONZE_BLOCK)).save(recipeOutput, "technocraft:bronze_ingot_from_block");
        
        List<ItemLike> BRONZE_SMELTABLES = List.of(ModItems.BRONZE_DUST);
        oreSmelting(recipeOutput, BRONZE_SMELTABLES, RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 0.7f, 200, "bronze_ingot");
        oreBlasting(recipeOutput, BRONZE_SMELTABLES, RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 0.7f, 100, "bronze_ingot");

        // DENSE PLATE
        compressing(recipeOutput, ModItems.BRONZE_PLATE.get(), 9, ModItems.BRONZE_DENSE_PLATE.get(), 1, "bronze_dense_plate");
        
        // SHAFT
        // BLOCK [EXTRUDING] SHAFT

        // ITEM CASING
        rolling(recipeOutput, ModItems.BRONZE_PLATE, ModItems.BRONZE_CASING, 2, "bronze_casing");
        
        // PLATE
        rolling(recipeOutput, ModItems.BRONZE_INGOT, ModItems.BRONZE_PLATE, 1, "bronze_plate");

        // TODO BRONZE: SHAFT

        ///////////////////////////////////////////////////
        /// 
        /// COPPER
        /// 
        ///////////////////////////////////////////////////

        // COPPER BLOCK
        compressing(recipeOutput, Items.COPPER_INGOT, 9, Blocks.COPPER_BLOCK, 1, "copper_block");

        // RAW COPPER BLOCK
        compressing(recipeOutput, Items.RAW_COPPER, 9, Blocks.RAW_COPPER_BLOCK, 1, "raw_copper_block");

        // COPPER DUST
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_DUST.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.COPPER_TINY_DUST.get())
            .unlockedBy("has_copper_tiny_dust", has(ModItems.COPPER_TINY_DUST)).save(recipeOutput);

        compressing(recipeOutput, ModItems.COPPER_TINY_DUST, 9, ModItems.LEAD_DUST, 1, "copper_dust");
        macerating(recipeOutput, ModItems.COPPER_DENSE_PLATE, ModItems.COPPER_DUST, 9, "copper_dust");
        macerating(recipeOutput, List.of(ModItems.COPPER_WASHED, ModItems.COPPER_PLATE, Items.COPPER_INGOT, ModItems.COPPER_CRUSHED), ModItems.COPPER_DUST, 1, "copper_dust");


        // COPPER INGOT
        List<ItemLike> COPPER_SMELTABLES = List.of(ModItems.COPPER_DUST, ModItems.COPPER_CRUSHED, ModItems.COPPER_WASHED);
        oreSmelting(recipeOutput, COPPER_SMELTABLES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7f, 200, "copper_ingot");
        oreBlasting(recipeOutput, COPPER_SMELTABLES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7f, 100, "copper_ingot");
        
        // CRUSHED COPPER
        macerating(recipeOutput, List.of(Items.RAW_COPPER, Items.COPPER_ORE, Items.DEEPSLATE_COPPER_ORE), ModItems.COPPER_CRUSHED.get(), 2, "copper_crushed");

        // DENSE PLATE
        compressing(recipeOutput, ModItems.COPPER_PLATE.get(), 9, ModItems.COPPER_DENSE_PLATE.get(), 1, "copper_dense_plate");
        
        // CABLE
        extruding(recipeOutput, Items.COPPER_INGOT, ModBlocks.COPPER_CABLE_BLOCK, 3, "copper_cable");

        // ITEM CASING
        rolling(recipeOutput, ModItems.COPPER_PLATE, ModItems.COPPER_CASING, 2, "copper_casing");

        // PLATE
        rolling(recipeOutput, Items.COPPER_INGOT, ModItems.COPPER_PLATE, 1, "copper_plate");

        ///////////////////////////////////////////////////
        /// 
        /// GOLD
        /// 
        ///////////////////////////////////////////////////

        // GOLD BLOCK
        compressing(recipeOutput, Items.GOLD_INGOT, 9, Blocks.GOLD_BLOCK, 1, "gold_block");

        // RAW GOLD BLOCK
        compressing(recipeOutput, Items.RAW_GOLD, 9, Blocks.RAW_GOLD_BLOCK, 1, "raw_gold_block");

        // GOLD DUST
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_DUST.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.GOLD_TINY_DUST.get())
            .unlockedBy("has_gold_tiny_dust", has(ModItems.GOLD_TINY_DUST)).save(recipeOutput);

        compressing(recipeOutput, ModItems.GOLD_TINY_DUST, 9, ModItems.GOLD_DUST, 1, "gold_dust");
        macerating(recipeOutput, ModItems.GOLD_DENSE_PLATE, ModItems.GOLD_DUST, 9, "gold_dust");
        macerating(recipeOutput, List.of(ModItems.GOLD_WASHED, ModItems.GOLD_PLATE, Items.GOLD_INGOT, ModItems.GOLD_CRUSHED), ModItems.GOLD_DUST, 1, "gold_dust");

        // GOLD INGOT
        List<ItemLike> GOLD_SMELTABLES = List.of(ModItems.GOLD_DUST, ModItems.GOLD_CRUSHED, ModItems.GOLD_WASHED);
        oreSmelting(recipeOutput, GOLD_SMELTABLES, RecipeCategory.MISC, Items.GOLD_INGOT, 0.7f, 200, "gold_ingot");
        oreBlasting(recipeOutput, GOLD_SMELTABLES, RecipeCategory.MISC, Items.GOLD_INGOT, 0.7f, 100, "gold_ingot");

        // CRUSHED GOLD
        macerating(recipeOutput, List.of(Items.RAW_GOLD, Items.GOLD_ORE, Items.DEEPSLATE_GOLD_ORE), ModItems.GOLD_CRUSHED.get(), 2, "gold_crushed");

        // DENSE PLATE
        compressing(recipeOutput, ModItems.GOLD_PLATE.get(), 9, ModItems.GOLD_DENSE_PLATE.get(), 1, "gold_dense_plate");
        
        // CABLE
        extruding(recipeOutput, Items.GOLD_INGOT, ModBlocks.GOLD_CABLE_BLOCK, 3, "gold_cable");

        // ITEM CASING
        rolling(recipeOutput, ModItems.GOLD_PLATE, ModItems.GOLD_CASING, 2, "gold_casing");

        // PLATE
        rolling(recipeOutput, Items.GOLD_INGOT, ModItems.GOLD_PLATE, 1, "gold_plate");

        ///////////////////////////////////////////////////
        /// 
        /// IRON
        /// 
        ///////////////////////////////////////////////////
        
        // IRON BLOCK
        compressing(recipeOutput, Items.IRON_INGOT, 9, Blocks.IRON_BLOCK, 1, "iron_block");

        // RAW IRON BLOCK
        compressing(recipeOutput, Items.RAW_IRON, 9, Blocks.RAW_IRON_BLOCK, 1, "raw_iron_block");

        // IRON DUST
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_DUST.get())
            .pattern("BBB")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', ModItems.IRON_TINY_DUST.get())
            .unlockedBy("has_iron_tiny_dust", has(ModItems.IRON_TINY_DUST)).save(recipeOutput);
        
        compressing(recipeOutput, ModItems.IRON_TINY_DUST, 9, ModItems.IRON_DUST, 1, "iron_dust");
        macerating(recipeOutput, ModItems.IRON_DENSE_PLATE, ModItems.IRON_DUST, 9, "iron_dust");
        macerating(recipeOutput, List.of(ModItems.IRON_WASHED, ModItems.IRON_PLATE, Items.IRON_INGOT, ModItems.IRON_CRUSHED, ModItems.STEEL_INGOT, ModItems.STEEL_PLATE), ModItems.IRON_DUST, 1, "iron_dust");
        // EMPTY FUEL CELL [MACERATING] DUST

        // IRON INGOT
        List<ItemLike> IRON_SMELTABLES = List.of(ModItems.IRON_DUST, ModItems.IRON_CRUSHED, ModItems.IRON_WASHED);
        oreSmelting(recipeOutput, IRON_SMELTABLES, RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 200, "iron_ingot");
        oreBlasting(recipeOutput, IRON_SMELTABLES, RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 100, "iron_ingot");

        // CRUSHED IRON
        macerating(recipeOutput, List.of(Items.RAW_IRON, Items.IRON_ORE, Items.DEEPSLATE_IRON_ORE), ModItems.IRON_CRUSHED.get(), 2, "iron_crushed");

        // DENSE PLATE
        compressing(recipeOutput, ModItems.IRON_PLATE.get(), 9, ModItems.IRON_DENSE_PLATE.get(), 1, "iron_dense_plate");

        // SHAFT
        // BLOCK [EXTRUDING] SHAFT

        // FENCE
        // ITEM CASING [EXTRUDING] FENCE

        // ITEM CASING
        rolling(recipeOutput, ModItems.IRON_PLATE, ModItems.IRON_CASING, 2, "iron_casing");

        // PLATE
        rolling(recipeOutput, Items.IRON_INGOT, ModItems.IRON_PLATE, 1, "iron_plate");

        // TODO IRON: SHAFT + FENCE

        ///////////////////////////////////////////////////
        /// 
        /// Bamboo
        /// 
        ///////////////////////////////////////////////////

        // BAMBOO BLOCK
        compressing(recipeOutput, Items.BAMBOO, 9, Blocks.BAMBOO_BLOCK, 1, "bamboo_block");

        ///////////////////////////////////////////////////
        /// 
        /// Coal
        /// 
        ///////////////////////////////////////////////////

        // COAL BLOCK
        compressing(recipeOutput, Items.COAL, 9, Blocks.COAL_BLOCK, 1, "coal_block");

        // CARBON PLATE
        // RAW CARBON MESH [COMPRESSING] CARBON PLATE

        // COMPRESSED COAL BALL
        // COAL BALL [COMPRESSING] COMPRESSED COAL BALL

        // COAL DUST
        // HYDRATED DUST [SMELTING] DUST
        // COAL [MACERATING] DUST
        // COAL BLOCK [MACERATING] 9 DUST

        // TODO COAL: RAW CARBON MESH + CARBON PLATE + BALL + COMPRESSED BALL + DUST + HYDRATED DUST

        ///////////////////////////////////////////////////
        /// 
        /// Redstone
        /// 
        ///////////////////////////////////////////////////
        
        // REDSTONE BLOCK
        compressing(recipeOutput, Items.REDSTONE, 9, Blocks.REDSTONE_BLOCK, 1, "redstone_block");

        // REDSTONE
        macerating(recipeOutput, Items.REDSTONE_BLOCK, Items.REDSTONE, 9, "redstone");

        ///////////////////////////////////////////////////
        /// 
        /// Emerald
        /// 
        ///////////////////////////////////////////////////

        // EMERALD BLOCK
        compressing(recipeOutput, Items.EMERALD, 9, Blocks.EMERALD_BLOCK, 1, "emerald_block");

        // DUST
        // EMERALD [MACERATING] DUST


        // TODO EMERALD: DUST

        ///////////////////////////////////////////////////
        /// 
        /// Lapis Lazuli
        /// 
        ///////////////////////////////////////////////////
         
        // LAPIS LAZULI BLOCK
        compressing(recipeOutput, Items.LAPIS_LAZULI, 9, Blocks.LAPIS_BLOCK, 1, "lapis_lazuli_block");

        // PLATE
        // DUST [COMPRESSING] PLATE

        // DENSE PLATE
        // 9 PLATE [COMPRESSING] DENSE PLATE

        // DUST
        // LAPIS [MACERATING] DUST
        // LAPIS BLOCK [MACERATING] 9 DUST

        // TODO LAPIS LAZULI: PLATE + DENSE PLATE + DUST

        ///////////////////////////////////////////////////
        /// 
        /// Diamond
        /// 
        ///////////////////////////////////////////////////

        // DIAMOND
        // COAL CHUNK [COMPRESSING] DIAMOND

        // DIAMOND BLOCK
        compressing(recipeOutput, Items.DIAMOND, 9, Blocks.DIAMOND_BLOCK, 1, "diamond_block");

        // DUST
        macerating(recipeOutput, Items.DIAMOND, ModItems.DIAMOND_DUST, 1, "diamond_dust");
        
        ///////////////////////////////////////////////////
        /// 
        /// Amethyst
        /// 
        ///////////////////////////////////////////////////

        // AMETHYST BLOCK
        compressing(recipeOutput, Items.AMETHYST_SHARD, 4, Blocks.AMETHYST_BLOCK, 1, "amethyst_block");

        ///////////////////////////////////////////////////
        /// 
        /// Snow
        /// 
        ///////////////////////////////////////////////////

        // SNOW BLOCK
        compressing(recipeOutput, Items.SNOWBALL, 4, Blocks.SNOW_BLOCK, 1, "snow_block");
        // WATER BUCKET / FLUID CELL [COMPRESSING] SNOW BLOCK
        macerating(recipeOutput, Items.ICE, Items.SNOW_BLOCK, 1, "snow_block");

        // SNOWBALL
        extracting(recipeOutput, Items.SNOW_BLOCK, Items.SNOWBALL, 1, "snow_ball");

        // ICE
        compressing(recipeOutput, Items.SNOW_BLOCK, Blocks.ICE, 1, "ice");

        // PACKED ICE
        compressing(recipeOutput, Items.ICE, 9, Blocks.PACKED_ICE, 1, "packed_ice"); // MAYBE ONLY 2 IS NEEDED?

        ///////////////////////////////////////////////////
        /// 
        /// Dripstone
        /// 
        ///////////////////////////////////////////////////

        // DRIPSTONE BLOCK
        compressing(recipeOutput, Items.POINTED_DRIPSTONE, 4, Blocks.DRIPSTONE_BLOCK, 1, "dripstone_block");

        ///////////////////////////////////////////////////
        /// 
        /// Bone
        /// 
        ///////////////////////////////////////////////////

        // BONE BLOCK
        compressing(recipeOutput, Items.BONE_MEAL, 9, Blocks.BONE_BLOCK, 1, "bone_block");

        // BONE MEAL
        macerating(recipeOutput, Items.BONE, Items.BONE_MEAL, 4, "bone_meal");

        ///////////////////////////////////////////////////
        /// 
        /// Nether
        /// 
        ///////////////////////////////////////////////////

        // MAGMA BLOCK
        compressing(recipeOutput, Items.MAGMA_CREAM, 4, Blocks.MAGMA_BLOCK, 1, "magma_block");

        // NETHERWART BLOCK
        compressing(recipeOutput, Items.NETHER_WART, 9, Blocks.NETHER_WART_BLOCK, 1, "netherwart_block");

        // QUARTZ BLOCK
        compressing(recipeOutput, Items.QUARTZ, 4, Blocks.QUARTZ_BLOCK, 1, "quartz_block");

        // QUARTZ
        macerating(recipeOutput, Items.QUARTZ_STAIRS, Items.QUARTZ, 6, "quartz");
        macerating(recipeOutput, Items.QUARTZ_BLOCK, Items.QUARTZ, 4, "quartz");

        // NETHERITE BLOCK
        compressing(recipeOutput, Items.NETHERITE_INGOT, 9, Blocks.NETHERITE_BLOCK, 1, "netherite_block");

        // NETHERITE PLATE?

        // BLAZE ROD
        compressing(recipeOutput, Items.BLAZE_POWDER, 5, Items.BLAZE_ROD, 1, "blaze_rod");
        
        // BLAZE POWDER
        macerating(recipeOutput, Items.BLAZE_ROD, Items.BLAZE_POWDER, 5, "blaze_powder");

        // NETHER BRICKS
        compressing(recipeOutput, Items.NETHER_BRICK, 4, Blocks.NETHER_BRICKS, 1, "nether_bricks");

        // NETHER BRICK
        extracting(recipeOutput, Items.NETHER_BRICKS, Items.NETHER_BRICK, 4, "nether_brick");

        // GLOWSTONE
        compressing(recipeOutput, Items.GLOWSTONE_DUST, 4, Blocks.GLOWSTONE, 1, "glowstone");

        // GLOWSTONE DUST
        macerating(recipeOutput, Items.GLOWSTONE, Items.GLOWSTONE_DUST, 4, "glowstone_dust");

        // NETHERRACK DUST
        // NETHERRACK [MACERATING] DUST

        // TODO NETHERRACK: DUST

        ///////////////////////////////////////////////////
        /// 
        /// Kelp
        /// 
        ///////////////////////////////////////////////////

        // DRIED KELP BLOCK
        compressing(recipeOutput, Items.DRIED_KELP, 9, Blocks.DRIED_KELP_BLOCK, 1, "dried_kelp_block");

        ///////////////////////////////////////////////////
        /// 
        /// Honey
        /// 
        ///////////////////////////////////////////////////

        // HONEYCOMB BLOCK
        compressing(recipeOutput, Items.HONEYCOMB, 4, Blocks.HONEYCOMB_BLOCK, 1, "honeycomb_block");

        ///////////////////////////////////////////////////
        /// 
        /// Slime
        /// 
        ///////////////////////////////////////////////////

        // SLIME BLOCK
        compressing(recipeOutput, Items.SLIME_BALL, 9, Blocks.SLIME_BLOCK, 1, "slime_block");
        ///////////////////////////////////////////////////
        /// 
        /// SAND
        /// 
        ///////////////////////////////////////////////////

        // SAND
        macerating(recipeOutput, Items.COBBLESTONE, Blocks.SAND, 1, "sand");
        macerating(recipeOutput, Items.SANDSTONE, Blocks.SAND, 1, "sand");

        // SANDSTONE
        compressing(recipeOutput, Items.SAND, 4, Blocks.SANDSTONE, 1, "sandstone");

        ///////////////////////////////////////////////////
        /// 
        /// OBSIDIAN
        /// 
        ///////////////////////////////////////////////////
        
        // PLATE
        // DUST [COMPRESSING] PLATE

        // DENSE PLATE
        // 9 PLATE [COMPRESSING] DENSE PLATE

        // DUST
        // 9 TINY [COMPRESSING] DUST
        // OBSIDIAN [MACERATING] DUST
        
        // TODO OBSIDIAN: DUST + TINY DUST + PLATE + DENSE PLATE

        ///////////////////////////////////////////////////
        /// 
        /// NUCLEAR
        /// 
        ///////////////////////////////////////////////////
        
        // URANIUM 235
        // MOX PELLET [COMPRESSING] U235

        // FUEL ROD
        // IRON PLATE [EXTRUDING] EMPTY FUEL ROD

        // TODO PLUTONIUM: () + TINY PILE

        // TODO URANIUM: ORE + RAW + CRUSHED + WASHED + BLOCK + 235 + 238

        // TODO MOX: MOX PELLET

        // TODO NUCLEAR: EMPTY FUEL ROD

        ///////////////////////////////////////////////////
        /// 
        /// FLUID CELL
        /// 
        ///////////////////////////////////////////////////
        
        // AIR
        // EMPTY [COMPRESSING] COMPRESSED AIR

        // EMPTY CELL
        // COMPRESSED AIR [EXTRACTING] EMPTY

        // TODO FLUID CELL: EMPTY + COMPRESSED AIR + WATER

        ///////////////////////////////////////////////////
        /// 
        /// IRIDIUM
        /// 
        ///////////////////////////////////////////////////
        
        // IRIDIUM ORE
        // 9 SHARD [COMPRESSING] ORE

        // SHARD
        // ORE [MACERATING] SHARD

        // TODO IRIDIUM: ORE + SHARD

        ///////////////////////////////////////////////////
        /// 
        /// LITHIUM
        /// 
        ///////////////////////////////////////////////////
        
        // DUST
        // 9 TINY [COMPRESSING] DUST

        // TODO LITHIUM: DUST + TINY DUST

        ///////////////////////////////////////////////////
        /// 
        /// ALLOY
        /// 
        ///////////////////////////////////////////////////
        
        // ADVANCED ALLOY
        // MIXED INGOT [COMPRESSING] ADVANCED ALLOY

        // TODO ALLOY: MIXED METAL INGOT + ADVANCED ALLOY

        ///////////////////////////////////////////////////
        /// 
        /// ENERGIUM
        /// 
        ///////////////////////////////////////////////////
        
        // ENERGY CRYSTAL
        // 9 ENERGIUM DUST [COMPRESSING] ENERGY CRYSTAL

        // DUST
        // ENERGY CRYSTAL [MACERATING] 9 DUST
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENERGIUM_DUST.get(), 9)
            .pattern("RDR")
            .pattern("DRD")
            .pattern("RDR")
            .define('R', Items.REDSTONE)
            .define('D', ModItems.DIAMOND_DUST.get())
            .unlockedBy("has_diamond_dust", has(ModItems.DIAMOND_DUST)).save(recipeOutput);

        // TODO ENERGUIM: ENERGY CRYSTAL

        ///////////////////////////////////////////////////
        /// 
        /// RUBBER
        /// 
        ///////////////////////////////////////////////////
        
        // RUBBER
        // STICK RESIN [SMELTING] RUBBER
        // SAPLING [EXTRACTOR] RUBBER
        // STICKY RESIN [EXTRACTOR] 3 RUBBER
        // LOG [EXTRACTOR] RUBBER

        // TODO RUBBER: RUBBER TREE + RUBBER + STICKY RESIN

        ///////////////////////////////////////////////////
        /// 
        /// COFFEE
        /// 
        ///////////////////////////////////////////////////
        
        // WARM COFFEE
        // COLD [SMELTING] WARM

        // POWDER
        // BEANS [MACERATING] POWDER

        // TODO COFFEE: COLD + WARM + BEANS + POWDER + MUG

        ///////////////////////////////////////////////////
        /// 
        /// SULFUR
        /// 
        ///////////////////////////////////////////////////

        // DUST
        // 9 TINY [COMPRESSING] DUST
        // GUNPOWDER [EXTRACTING] DUST

        // TINY DUST
        // NETHERRACK DUST [EXTRACTING] TINY DUST

        // TODO SULFUR: DUST + TINY DUST

        ///////////////////////////////////////////////////
        /// 
        /// ENDER
        /// 
        ///////////////////////////////////////////////////
        
        // PURPUR BLOCK
        compressing(recipeOutput, Items.POPPED_CHORUS_FRUIT, 4, Blocks.PURPUR_BLOCK, 4, "purpur_block");

        // EYE OF ENDER DUST
        // EYE OF ENDER [MACERATING] DUST

        // ENDER PEARL DUST
        // ENDER PEARL [MACERATING] DUST

        // TODO ENDER: EYE OF ENDER DUST + ENDER PEARL DUST

        ///////////////////////////////////////////////////
        /// 
        /// CLAY
        /// 
        ///////////////////////////////////////////////////
        
        // BLOCK
        compressing(recipeOutput, Items.CLAY_BALL, 4, Blocks.CLAY, 1, "clay");

        // BRICKS
        compressing(recipeOutput, Items.BRICK, 4, Blocks.BRICKS, 1, "bricks");

        // BRICK
        extracting(recipeOutput, Items.BRICKS, Items.BRICK, 4, "brick");

        // CLAY BALL
        extracting(recipeOutput, Items.CLAY, Items.CLAY_BALL, 4, "clay_ball");

        // DUST
        // BLOCK [MACERATING] 2 DUST
        
        // TODO CLAY: DUST

        ///////////////////////////////////////////////////
        /// 
        /// PLANT STUFF
        /// 
        ///////////////////////////////////////////////////

        // GRIN POWDER
        // POISONOUS POTATO [MACERATING] GRIN
        // SPIDER EYE [MACERATING] 2 GRIN

        // BIOCHAFF
        // SAPPLING [MACERATING] BIOCHAFF
        // PLANT BALL [MACERATING] BIOCHAFF
        // 8 PUMPKIN [MACERATING] BIOCHAFF
        // 8 MELON SLICE [MACERATING] BIOCHAFF
        // 8 LEAVES(TAG) [MACERATING] BIOCHAFF
        // 16 SEEDS(TAG) [MACERATING] BIOCHAFF
        // 32 Weed [MACERATING] BIOCHAFF
        // 8 CACTUS [MACERATING] BIOCHAFF
        // 8 DEAD BUSH [MACERATING] BIOCHAFF
        // 8 POTATO [MACERATING] BIOCHAFF
        // 8 WHEAT [MACERATING] BIOCHAFF
        // 8 SUGARCANE [MACERATING] BIOCHAFF
        // 8 CARROT [MACERATING] BIOCHAFF


        // TODO PLANTS: BIOCHAFF + GRIN POWDER + PLANT BALL + WEED (unwanted plants)

        ///////////////////////////////////////////////////
        /// 
        /// RECYCLEING
        /// 
        ///////////////////////////////////////////////////
        
        // MEMORY CRYSTAL
        // RAW MEMORY CRYSTAL [SMELTING] MEMORY CRYSTAL

        // TODO RECYCLE: SCRAP + RAW MEMORY CRYSTAL + MEMORY CRYSTAL + UU MATTER

        ///////////////////////////////////////////////////
        /// 
        /// STONE
        /// 
        ///////////////////////////////////////////////////

        // COBBLESTONE
        // STONE [MACERATING] COBBLESTONE

        // TODO STONE: DUST
        ///////////////////////////////////////////////////
        /// 
        /// OTHER
        /// 
        ///////////////////////////////////////////////////

        // WOOL
        extracting(recipeOutput, ItemTags.WOOL, Items.WHITE_WOOL, 1, "white_wool");

        // IODINE
        // HYDRATED TIN [EXTRACTING] IODINE

        // FLINT
        macerating(recipeOutput, Items.GRAVEL, Items.FLINT, 1, "flint");

        // DIRT
        // BIOCHAFF [MACERATING] DIRT

        // STRING
        macerating(recipeOutput, Items.WHITE_WOOL, Items.STRING, 2, "sting");

        // RECHARGEABLE BATTERY
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RECHARGEABLE_BATTERY.get())
            .pattern(" T ")
            .pattern("CRC")
            .pattern("CRC")
            .define('T', ModBlocks.INS_TIN_CABLE_BLOCK.get())
            .define('C', ModItems.TIN_CASING.get())
            .define('R', Items.REDSTONE)
            .unlockedBy("has_ins_tin_cable", has(ModBlocks.INS_TIN_CABLE_BLOCK)).save(recipeOutput);

        // POWER METER
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWER_METER.get())
            .pattern(" G ")
            .pattern("CEC")
            .pattern("C C")
            .define('C', ModBlocks.INS_COPPER_CABLE_BLOCK.get())
            .define('E', ModItems.ELECTRONIC_CIRCUIT.get())
            .define('G', Items.GLOWSTONE)
            .unlockedBy("has_electronic_circuit", has(ModItems.ELECTRONIC_CIRCUIT)).save(recipeOutput);

        // ELECTRONIC CIRCUIT
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ELECTRONIC_CIRCUIT.get())
            .pattern("CCC")
            .pattern("RIR")
            .pattern("CCC")
            .define('C', ModBlocks.INS_COPPER_CABLE_BLOCK.get())
            .define('I', ModItems.IRON_PLATE.get())
            .define('R', Items.REDSTONE)
            .unlockedBy("has_ins_copper_cable", has(ModBlocks.INS_COPPER_CABLE_BLOCK)).save(recipeOutput);

        // GENERATOR
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GENERATOR_BLOCK.get())
            .pattern("B")
            .pattern("M")
            .pattern("F")
            .define('M', ModBlocks.MACHINE_CASING.get())
            .define('B', ModItems.RECHARGEABLE_BATTERY.get())
            .define('F', Items.FURNACE)
            .unlockedBy("has_rechargeable_battery", has(ModItems.RECHARGEABLE_BATTERY)).save(recipeOutput);
        
        // MACHINE CASING
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MACHINE_CASING.get())
            .pattern("III")
            .pattern("I I")
            .pattern("III")
            .define('I', ModItems.IRON_PLATE.get())
            .unlockedBy("has_iron_plate", has(ModItems.IRON_PLATE)).save(recipeOutput);
        
        // SUPER CABLE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SUPER_CONDUCTING_CABLE_BLOCK.get(), 6)
            .pattern("GGG")
            .pattern("ESE")
            .pattern("GGG")
            .define('E', ModItems.ENERGIUM_DUST.get())
            .define('S', ModItems.SILVER_DUST.get())
            .define('G', Items.GLASS)
            .unlockedBy("has_energium_dust", has(ModItems.ENERGIUM_DUST)).save(recipeOutput);
        
        // ELECTRIC FURNACE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ELECTRIC_FURNACE_BLOCK.get())
            .pattern("III")
            .pattern("IFI")
            .pattern("RER")
            .define('I', ModItems.IRON_PLATE.get())
            .define('E', ModItems.ELECTRONIC_CIRCUIT.get())
            .define('F', Items.FURNACE)
            .define('R', Items.REDSTONE)
            .unlockedBy("has_electronic_circuit", has(ModItems.ELECTRONIC_CIRCUIT)).save(recipeOutput);

        // MACERATOR
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MACERATOR_BLOCK.get())
            .pattern("FFF")
            .pattern("CMC")
            .pattern(" E ")
            .define('M', ModBlocks.MACHINE_CASING.get())
            .define('E', ModItems.ELECTRONIC_CIRCUIT.get())
            .define('F', Items.FLINT)
            .define('C', Items.COBBLESTONE)
            .unlockedBy("has_electronic_circuit", has(ModItems.ELECTRONIC_CIRCUIT)).save(recipeOutput);

        // COMPRESSOR
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COMPRESSOR_BLOCK.get())
            .pattern("S S")
            .pattern("SMS")
            .pattern("SES")
            .define('M', ModBlocks.MACHINE_CASING.get())
            .define('E', ModItems.ELECTRONIC_CIRCUIT.get())
            .define('S', Items.STONE)
            .unlockedBy("has_electronic_circuit", has(ModItems.ELECTRONIC_CIRCUIT)).save(recipeOutput);

        // COIL
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COIL.get())
            .pattern("CCC")
            .pattern("CIC")
            .pattern("CCC")
            .define('C', ModBlocks.COPPER_CABLE_BLOCK.get())
            .define('I', Items.IRON_INGOT)
            .unlockedBy("has_copper_cable", has(ModBlocks.COPPER_CABLE_BLOCK)).save(recipeOutput);
    }

    /** Registers a smelting recipe for every ingredient in pIngredients -> pResult (one recipe file per ingredient). */
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    /** Blasting counterpart of {@link #oreSmelting} */
    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreMacerating(RecipeOutput recipeOutput, ItemLike ingredient, int ingredientCount, ItemLike result, int count ,String group) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "macerating/" + getItemName(result) + "_from_" + getItemName(ingredient));
        recipeOutput.accept(id, new MacerationRecipe(Ingredient.of(ingredient), new ItemStack(result, count), ingredientCount), null);
    }

    protected static void macerating(RecipeOutput recipeOutput, ItemLike ingredient, ItemLike result, int count ,String group) {
        oreMacerating(recipeOutput, ingredient, 1, result, count, group);
    }

    protected static void macerating(RecipeOutput recipeOutput, List<ItemLike> ingredients, ItemLike result, int count, String group) {
        for (ItemLike ingredient : ingredients) {
            macerating(recipeOutput, ingredient, result, count, group);
        }
    }

    protected static void compressing(RecipeOutput recipeOutput, ItemLike ingredient, int ingredientCount, ItemLike result, int resultCount, String group) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "compressing/" + getItemName(result) + "_from_" + getItemName(ingredient));
        recipeOutput.accept(id, new CompressorRecipe(Ingredient.of(ingredient), new ItemStack(result, resultCount), ingredientCount), null);
    }

    protected static void compressing(RecipeOutput recipeOutput, ItemLike ingredient, ItemLike result, int resultCount, String group) {
        compressing(recipeOutput, ingredient, 1, result, resultCount, group);
    }

    protected static void compressing(RecipeOutput recipeOutput, TagKey<Item> ingredient, int ingredientCount, ItemLike result, int resultCount, String group) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "compressing/" + getItemName(result) + "_from_" + ingredient.location().getPath());
        recipeOutput.accept(id, new CompressorRecipe(Ingredient.of(ingredient), new ItemStack(result, resultCount), ingredientCount), null);
    }

    protected static void compressing(RecipeOutput recipeOutput, TagKey<Item> ingredient, ItemLike result, int resultCount, String group) {
        compressing(recipeOutput, ingredient, 1, result, resultCount, group);
    }

    protected static void extracting(RecipeOutput recipeOutput, ItemLike ingredient, int ingredientCount, ItemLike result, int resultCount, String group) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "extracting/" + getItemName(result) + "_from_" + getItemName(ingredient));
        recipeOutput.accept(id, new ExtractorRecipe(Ingredient.of(ingredient), new ItemStack(result, resultCount), ingredientCount), null);
    }

    protected static void extracting(RecipeOutput recipeOutput, ItemLike ingredient, ItemLike result, int resultCount, String group) {
        extracting(recipeOutput, ingredient, 1, result, resultCount, group);
    }

    protected static void extracting(RecipeOutput recipeOutput, TagKey<Item> ingredient, int ingredientCount, ItemLike result, int resultCount, String group) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "extracting/" + getItemName(result) + "_from_" + ingredient.location().getPath());
        recipeOutput.accept(id, new ExtractorRecipe(Ingredient.of(ingredient), new ItemStack(result, resultCount), ingredientCount), null);
    }

    protected static void extracting(RecipeOutput recipeOutput, TagKey<Item> ingredient, ItemLike result, int resultCount, String group) {
        extracting(recipeOutput, ingredient, 1, result, resultCount, group);
    }

    protected static void extruding(RecipeOutput recipeOutput, ItemLike ingredient, int ingredientCount, ItemLike result, int resultCount, String group) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "extruding/" + getItemName(result) + "_from_" + getItemName(ingredient));
        recipeOutput.accept(id, new ExtruderRecipe(Ingredient.of(ingredient), new ItemStack(result, resultCount), ingredientCount), null);
    }

    protected static void extruding(RecipeOutput recipeOutput, ItemLike ingredient, ItemLike result, int resultCount, String group) {
        extruding(recipeOutput, ingredient, 1, result, resultCount, group);
    }

    protected static void extruding(RecipeOutput recipeOutput, TagKey<Item> ingredient, int ingredientCount, ItemLike result, int resultCount, String group) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "extruding/" + getItemName(result) + "_from_" + ingredient.location().getPath());
        recipeOutput.accept(id, new ExtruderRecipe(Ingredient.of(ingredient), new ItemStack(result, resultCount), ingredientCount), null);
    }

    protected static void extruding(RecipeOutput recipeOutput, TagKey<Item> ingredient, ItemLike result, int resultCount, String group) {
        extruding(recipeOutput, ingredient, 1, result, resultCount, group);
    }

    protected static void rolling(RecipeOutput recipeOutput, ItemLike ingredient, int ingredientCount, ItemLike result, int resultCount, String group) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "rolling/" + getItemName(result) + "_from_" + getItemName(ingredient));
        recipeOutput.accept(id, new RollerRecipe(Ingredient.of(ingredient), new ItemStack(result, resultCount), ingredientCount), null);
    }

    protected static void rolling(RecipeOutput recipeOutput, ItemLike ingredient, ItemLike result, int resultCount, String group) {
        rolling(recipeOutput, ingredient, 1, result, resultCount, group);
    }

    protected static void rolling(RecipeOutput recipeOutput, TagKey<Item> ingredient, int ingredientCount, ItemLike result, int resultCount, String group) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "rolling/" + getItemName(result) + "_from_" + ingredient.location().getPath());
        recipeOutput.accept(id, new RollerRecipe(Ingredient.of(ingredient), new ItemStack(result, resultCount), ingredientCount), null);
    }

    protected static void rolling(RecipeOutput recipeOutput, TagKey<Item> ingredient, ItemLike result, int resultCount, String group) {
        rolling(recipeOutput, ingredient, 1, result, resultCount, group);
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemLike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemLike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemLike), has(itemLike)).save(recipeOutput, Technocraft.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemLike));
        }
    }

    protected static void hammering(RecipeOutput recipeOutput, ItemLike ingredient, ItemLike result, int resultCount, String group) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "hammering/" + getItemName(result) + "_from_" + getItemName(ingredient));
        recipeOutput.accept(id, new HammerRecipe(Ingredient.of(ingredient), new ItemStack(result, resultCount)), null);
    }
}
