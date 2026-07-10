package net.augmentedduck.technocraft;

import net.augmentedduck.technocraft.block.entity.ModBlockEntities;
import net.augmentedduck.technocraft.item.ModItems;
import net.augmentedduck.technocraft.item.custom.RechargeableBatteryItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = Technocraft.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModCapabilities {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        // BLOCKS
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.GENERATOR_BE.get(), (be, side) -> be.getItemHandler(side));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.GENERATOR_BE.get(), (be, side) -> be.getEnergyStorage());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.ELECTRIC_FURNACE_BE.get(), (be, side) -> be.getItemHandler(side));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.ELECTRIC_FURNACE_BE.get(), (be, side) -> be.getEnergyStorage());

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.MACERATOR_BE.get(), (be, side) -> be.getItemHandler(side));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.MACERATOR_BE.get(), (be, side) -> be.getEnergyStorage());

        // CABLES
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.TIN_CABLE_BE.get(), (be, side) -> be.getEnergyStorage());

        // ITEMS
        event.registerItem(Capabilities.EnergyStorage.ITEM, (stack, ctx) -> new RechargeableBatteryItem.ItemBatteryEnergyStorage(stack),ModItems.RECHARGEABLE_BATTERY.get());
    }
}
