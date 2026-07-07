package net.augmentedduck.technocraft.block.entity;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Technocraft.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GeneratorBlockEntity>> GENERATOR_BE = BLOCK_ENTITIES.register("generator_be", () -> BlockEntityType.Builder.of(GeneratorBlockEntity::new, ModBlocks.GENERATOR_BLOCK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ElectricFurnaceBlockEntity>> ELECTRIC_FURNACE_BE = BLOCK_ENTITIES.register("electric_furnace_be", () -> BlockEntityType.Builder.of(ElectricFurnaceBlockEntity::new, ModBlocks.ELECTRIC_FURNACE_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
