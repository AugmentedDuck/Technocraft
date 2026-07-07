package net.augmentedduck.technocraft;

import net.augmentedduck.technocraft.block.entity.ModBlockEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = Technocraft.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModCapabilities {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.GENERATOR_BE.get(), (be, side) -> be.getItemHandler());
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.GENERATOR_BE.get(), (be, side) -> be.getEnergyStorage());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.ELECTRIC_FURNACE_BE.get(), (be, side) -> be.getItemHandler());
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.ELECTRIC_FURNACE_BE.get(), (be, side) -> be.getEnergyStorage());
    }
}
