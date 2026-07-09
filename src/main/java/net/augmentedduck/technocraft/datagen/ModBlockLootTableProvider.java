package net.augmentedduck.technocraft.datagen;

import java.util.Set;

import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        ////////////////////////////////////
        // SELF DROPPING
        ////////////////////////////////////
        
        // NORMAL BLOCKS
        dropSelf(ModBlocks.TIN_BLOCK.get());
        dropSelf(ModBlocks.SILVER_BLOCK.get());
        dropSelf(ModBlocks.STEEL_BLOCK.get());
        dropSelf(ModBlocks.BRONZE_BLOCK.get());
        dropSelf(ModBlocks.LEAD_BLOCK.get());

        // CUSTOM BLOCKS
        dropSelf(ModBlocks.GENERATOR_BLOCK.get());
        dropSelf(ModBlocks.ELECTRIC_FURNACE_BLOCK.get());
        dropSelf(ModBlocks.MACERATOR_BLOCK.get());

        /////////////////////////////////////
        // NON SELF DROPPING
        /////////////////////////////////////
        
        // ORES
        add(ModBlocks.TIN_ORE.get(), block -> createOreDrop(ModBlocks.TIN_ORE.get(), ModItems.TIN_RAW.get()));
        add(ModBlocks.SILVER_ORE.get(), block -> createOreDrop(ModBlocks.SILVER_ORE.get(), ModItems.SILVER_RAW.get()));
        add(ModBlocks.LEAD_ORE.get(), block -> createOreDrop(ModBlocks.LEAD_ORE.get(), ModItems.LEAD_RAW.get()));
        
        // DEEPSLATE ORES
        add(ModBlocks.TIN_DEEPSLATE_ORE.get(), block -> createOreDrop(ModBlocks.TIN_DEEPSLATE_ORE.get(), ModItems.TIN_RAW.get()));
        add(ModBlocks.SILVER_DEEPSLATE_ORE.get(), block -> createOreDrop(ModBlocks.SILVER_DEEPSLATE_ORE.get(), ModItems.SILVER_RAW.get()));
        add(ModBlocks.LEAD_DEEPSLATE_ORE.get(), block -> createOreDrop(ModBlocks.LEAD_DEEPSLATE_ORE.get(), ModItems.LEAD_RAW.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                                                                                                      .apply(ApplyBonusCount.addOreBonusCount((registryLookup.getOrThrow(Enchantments.FORTUNE))))));
    }
}
