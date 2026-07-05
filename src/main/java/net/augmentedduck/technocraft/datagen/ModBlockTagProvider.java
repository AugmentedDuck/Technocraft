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

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Technocraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModBlocks.BRONZE_BLOCK.get())
            .add(ModBlocks.SILVER_BLOCK.get())
            .add(ModBlocks.STEEL_BLOCK.get())
            .add(ModBlocks.LEAD_BLOCK.get())
            .add(ModBlocks.TIN_BLOCK.get());
        
        tag(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.BRONZE_BLOCK.get())
            .add(ModBlocks.SILVER_BLOCK.get())
            .add(ModBlocks.LEAD_BLOCK.get())
            .add(ModBlocks.TIN_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(ModBlocks.STEEL_BLOCK.get());
    }

}
