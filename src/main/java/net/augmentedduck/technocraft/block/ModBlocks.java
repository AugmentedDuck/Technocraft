package net.augmentedduck.technocraft.block;

import java.util.function.Supplier;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.custom.*;
import net.augmentedduck.technocraft.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Technocraft.MODID);

    ///////////////////////////////////
    // STATIC BLOCKS
    ///////////////////////////////////
    
    // BLOCKS
    public static final DeferredBlock<Block> TIN_BLOCK = registerBlock("tin_block", () -> new Block(BlockBehaviour.Properties.of().strength(5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> LEAD_BLOCK = registerBlock("lead_block", () -> new Block(BlockBehaviour.Properties.of().strength(5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> SILVER_BLOCK = registerBlock("silver_block", () -> new Block(BlockBehaviour.Properties.of().strength(5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> STEEL_BLOCK = registerBlock("steel_block", () -> new Block(BlockBehaviour.Properties.of().strength(5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> BRONZE_BLOCK = registerBlock("bronze_block", () -> new Block(BlockBehaviour.Properties.of().strength(5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    // ORES
    public static final DeferredBlock<Block> TIN_ORE = registerBlock("tin_ore", () -> new Block(BlockBehaviour.Properties.of().strength(3f, 3f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> SILVER_ORE = registerBlock("silver_ore", () -> new Block(BlockBehaviour.Properties.of().strength(3f, 3f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> LEAD_ORE = registerBlock("lead_ore", () -> new Block(BlockBehaviour.Properties.of().strength(3f, 3f).requiresCorrectToolForDrops()));

    // DEEPSLATE ORES
    public static final DeferredBlock<Block> TIN_DEEPSLATE_ORE = registerBlock("tin_deepslate_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.5f, 3f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> SILVER_DEEPSLATE_ORE = registerBlock("silver_deepslate_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.5f, 3f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> LEAD_DEEPSLATE_ORE = registerBlock("lead_deepslate_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.5f, 3f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));    

    ///////////////////////////////////
    // CUSTOM BLOCKS
    ///////////////////////////////////
    
    // GENERATORS
    public static final DeferredBlock<Block> GENERATOR_BLOCK = registerBlock("generator", () -> new GeneratorBlock(BlockBehaviour.Properties.of().strength(3.5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> SOLAR_PANEL_BLOCK = registerBlock("solar_panel", () -> new SolarPanelBlock(BlockBehaviour.Properties.of().strength(3.5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> RTG_BLOCK = registerBlock("rtg", () -> new RTGBlock(BlockBehaviour.Properties.of().strength(3.5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    
    // CONSUMERS
    public static final DeferredBlock<Block> ELECTRIC_FURNACE_BLOCK = registerBlock("electric_furnace", () -> new ElectricFurnaceBlock(BlockBehaviour.Properties.of().strength(3.5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));    
    public static final DeferredBlock<Block> MACERATOR_BLOCK = registerBlock("macerator", () -> new ExtractorBlock(BlockBehaviour.Properties.of().strength(3.5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));    
    public static final DeferredBlock<Block> COMPRESSOR_BLOCK = registerBlock("compressor", () -> new CompressorBlock(BlockBehaviour.Properties.of().strength(3.5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));    
    public static final DeferredBlock<Block> EXTRACTOR_BLOCK = registerBlock("extractor", () -> new ExtractorBlock(BlockBehaviour.Properties.of().strength(3.5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));    
    public static final DeferredBlock<Block> EXTRUDER_BLOCK = registerBlock("extruder", () -> new ExtruderBlock(BlockBehaviour.Properties.of().strength(3.5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));    
    public static final DeferredBlock<Block> ROLLER_BLOCK = registerBlock("roller", () -> new RollerBlock(BlockBehaviour.Properties.of().strength(3.5f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL)));    

    // CABLES
    public static final DeferredBlock<Block> TIN_CABLE_BLOCK = registerBlock("tin_cable", () -> new TinCableBlock(BlockBehaviour.Properties.of().strength(1f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL).noOcclusion()));    
    public static final DeferredBlock<Block> COPPER_CABLE_BLOCK = registerBlock("copper_cable", () -> new CopperCableBlock(BlockBehaviour.Properties.of().strength(1f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL).noOcclusion()));    
    public static final DeferredBlock<Block> GOLD_CABLE_BLOCK = registerBlock("gold_cable", () -> new GoldCableBlock(BlockBehaviour.Properties.of().strength(1f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL).noOcclusion()));    
    public static final DeferredBlock<Block> SILVER_CABLE_BLOCK = registerBlock("silver_cable", () -> new SilverCableBlock(BlockBehaviour.Properties.of().strength(1f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL).noOcclusion()));    
    public static final DeferredBlock<Block> SUPER_CONDUCTING_CABLE_BLOCK = registerBlock("super_conducting_cable", () -> new SuperConductingCableBlock(BlockBehaviour.Properties.of().strength(1f, 6f).requiresCorrectToolForDrops().sound(SoundType.METAL).noOcclusion()));    


    /**
     * Registers a block AND its corresponding BlockItem in one call, so every
     * block placed here automatically gets an inventory item too.
     */
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
