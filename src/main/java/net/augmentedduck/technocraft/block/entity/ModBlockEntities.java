package net.augmentedduck.technocraft.block.entity;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Handles the registration of all custom Block Entities (Tile Entities) for the mod.
 * Uses NeoForge's Deferred Register system to safely register content at the correct time.
 */
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Technocraft.MODID);

    // GENERATORS
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GeneratorBlockEntity>> GENERATOR_BE = BLOCK_ENTITIES.register("generator_be", () -> BlockEntityType.Builder.of(GeneratorBlockEntity::new, ModBlocks.GENERATOR_BLOCK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL_BE = BLOCK_ENTITIES.register("solar_panel_be", () -> BlockEntityType.Builder.of(SolarPanelBlockEntity::new, ModBlocks.SOLAR_PANEL_BLOCK.get()).build(null));
    
    // CONSUMERS
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ElectricFurnaceBlockEntity>> ELECTRIC_FURNACE_BE = BLOCK_ENTITIES.register("electric_furnace_be", () -> BlockEntityType.Builder.of(ElectricFurnaceBlockEntity::new, ModBlocks.ELECTRIC_FURNACE_BLOCK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MaceratorBlockEntity>> MACERATOR_BE = BLOCK_ENTITIES.register("macerator_be", () -> BlockEntityType.Builder.of(MaceratorBlockEntity::new, ModBlocks.MACERATOR_BLOCK.get()).build(null));

    // CABLES
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TinCableBlockEntity>> TIN_CABLE_BE = BLOCK_ENTITIES.register("tin_cable_be", () -> BlockEntityType.Builder.of(TinCableBlockEntity::new, ModBlocks.TIN_CABLE_BLOCK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CopperCableBlockEntity>> COPPER_CABLE_BE = BLOCK_ENTITIES.register("copper_cable_be", () -> BlockEntityType.Builder.of(CopperCableBlockEntity::new, ModBlocks.COPPER_CABLE_BLOCK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GoldCableBlockEntity>> GOLD_CABLE_BE = BLOCK_ENTITIES.register("gold_cable_be", () -> BlockEntityType.Builder.of(GoldCableBlockEntity::new, ModBlocks.GOLD_CABLE_BLOCK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SilverCableBlockEntity>> SILVER_CABLE_BE = BLOCK_ENTITIES.register("silver_cable_be", () -> BlockEntityType.Builder.of(SilverCableBlockEntity::new, ModBlocks.SILVER_CABLE_BLOCK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SuperConductingCableBlockEntity>> SUPER_CONDUCTING_CABLE_BE = BLOCK_ENTITIES.register("super_conducting_cable_be", () -> BlockEntityType.Builder.of(SuperConductingCableBlockEntity::new, ModBlocks.SUPER_CONDUCTING_CABLE_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
