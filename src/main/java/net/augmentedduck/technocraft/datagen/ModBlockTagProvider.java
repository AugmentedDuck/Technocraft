package net.augmentedduck.technocraft.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.Tags;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Technocraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        //////////////////////////////////
        // MINABLE WITH {TOOL}
        //////////////////////////////////
        
        // PICKAXE
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            // ORES
            .add(ModBlocks.TIN_ORE.get())
            .add(ModBlocks.SILVER_ORE.get())
            .add(ModBlocks.LEAD_ORE.get())
            
            //DEEP SLATE ORES
            .add(ModBlocks.TIN_DEEPSLATE_ORE.get())
            .add(ModBlocks.SILVER_DEEPSLATE_ORE.get())
            .add(ModBlocks.LEAD_DEEPSLATE_ORE.get())
            
            // BLOCKS
            .add(ModBlocks.BRONZE_BLOCK.get())
            .add(ModBlocks.SILVER_BLOCK.get())
            .add(ModBlocks.STEEL_BLOCK.get())
            .add(ModBlocks.LEAD_BLOCK.get())
            .add(ModBlocks.TIN_BLOCK.get())
            
            // GENERATORS
            .add(ModBlocks.GENERATOR_BLOCK.get())
            
            // CONSUMERS
            .add(ModBlocks.ELECTRIC_FURNACE_BLOCK.get())
            
            ;
        
        //////////////////////////////////
        // MINABLE WITH TOOL LEVEL:
        //////////////////////////////////
        
        // STONE
        tag(BlockTags.NEEDS_STONE_TOOL)
            // ORES
            .add(ModBlocks.TIN_ORE.get())
            .add(ModBlocks.LEAD_ORE.get())
            .add(ModBlocks.SILVER_ORE.get())
            
            // DEEPSLATE ORES
            .add(ModBlocks.TIN_DEEPSLATE_ORE.get())
            .add(ModBlocks.LEAD_DEEPSLATE_ORE.get())
            .add(ModBlocks.SILVER_DEEPSLATE_ORE.get())
        
            ;
        // IRON
        tag(BlockTags.NEEDS_IRON_TOOL)
            // BLOCKS            
            .add(ModBlocks.BRONZE_BLOCK.get())
            .add(ModBlocks.SILVER_BLOCK.get())
            .add(ModBlocks.LEAD_BLOCK.get())
            .add(ModBlocks.TIN_BLOCK.get())

            // GENERATORS
            .add(ModBlocks.GENERATOR_BLOCK.get())
            
            // CONSUMERS
            .add(ModBlocks.ELECTRIC_FURNACE_BLOCK.get())

            ;

        // DIAMOND
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
            // BLOCKS
            .add(ModBlocks.STEEL_BLOCK.get());

        //////////////////////////////////
        // OTHER
        //////////////////////////////////
        
        // STORAGE BLOCKS
        tag(Tags.Blocks.STORAGE_BLOCKS)
            .add(ModBlocks.BRONZE_BLOCK.get())
            .add(ModBlocks.SILVER_BLOCK.get())
            .add(ModBlocks.STEEL_BLOCK.get())
            .add(ModBlocks.LEAD_BLOCK.get())
            .add(ModBlocks.TIN_BLOCK.get());
    }

}
