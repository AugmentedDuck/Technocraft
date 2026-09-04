package net.augmentedduck.technocraft.item;

import java.util.function.Supplier;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Technocraft.MODID);
    
    public static final Supplier<CreativeModeTab> TECHNOCRAFT_TAB = CREATIVE_MODE_TAB.register("technocraft_tab", () -> CreativeModeTab.builder()
                                                                                                                                            .icon(() -> new ItemStack(ModItems.GOLD_CASING.get()))
                                                                                                                                            .title(Component.translatable("creativetab.technocraft.tab_title"))
                                                                                                                                            .displayItems((itemDisplayParameters, output) -> {
            
            ///////////////////////////////
            // ITEMS
            ///////////////////////////////
            // INGOTS
            output.accept(ModItems.TIN_INGOT);
            output.accept(ModItems.LEAD_INGOT);
            output.accept(ModItems.SILVER_INGOT);
            output.accept(ModItems.STEEL_INGOT);
            output.accept(ModItems.BRONZE_INGOT);

            // CASING
            output.accept(ModItems.TIN_CASING);
            output.accept(ModItems.LEAD_CASING);
            output.accept(ModItems.SILVER_CASING);
            output.accept(ModItems.STEEL_CASING);
            output.accept(ModItems.BRONZE_CASING);

            output.accept(ModItems.IRON_CASING);
            output.accept(ModItems.GOLD_CASING);
            output.accept(ModItems.COPPER_CASING);

            // DENSE PLATE
            output.accept(ModItems.TIN_DENSE_PLATE);
            output.accept(ModItems.LEAD_DENSE_PLATE);
            output.accept(ModItems.SILVER_DENSE_PLATE);
            output.accept(ModItems.STEEL_DENSE_PLATE);
            output.accept(ModItems.BRONZE_DENSE_PLATE);

            output.accept(ModItems.IRON_DENSE_PLATE);
            output.accept(ModItems.GOLD_DENSE_PLATE);
            output.accept(ModItems.COPPER_DENSE_PLATE);
            
            // PLATE
            output.accept(ModItems.TIN_PLATE);
            output.accept(ModItems.LEAD_PLATE);
            output.accept(ModItems.SILVER_PLATE);
            output.accept(ModItems.STEEL_PLATE);
            output.accept(ModItems.BRONZE_PLATE);

            output.accept(ModItems.IRON_PLATE);
            output.accept(ModItems.GOLD_PLATE);
            output.accept(ModItems.COPPER_PLATE);

            // RAW
            output.accept(ModItems.TIN_RAW);
            output.accept(ModItems.LEAD_RAW);
            output.accept(ModItems.SILVER_RAW);

            // CRUSHED
            output.accept(ModItems.TIN_CRUSHED);
            output.accept(ModItems.LEAD_CRUSHED);
            output.accept(ModItems.SILVER_CRUSHED);

            output.accept(ModItems.IRON_CRUSHED);
            output.accept(ModItems.GOLD_CRUSHED);
            output.accept(ModItems.COPPER_CRUSHED);

            // WASHED
            output.accept(ModItems.TIN_WASHED);
            output.accept(ModItems.LEAD_WASHED);
            output.accept(ModItems.SILVER_WASHED);

            output.accept(ModItems.IRON_WASHED);
            output.accept(ModItems.GOLD_WASHED);
            output.accept(ModItems.COPPER_WASHED);

            // DUST
            output.accept(ModItems.TIN_DUST);
            output.accept(ModItems.LEAD_DUST);
            output.accept(ModItems.SILVER_DUST);
            output.accept(ModItems.BRONZE_DUST);

            output.accept(ModItems.IRON_DUST);
            output.accept(ModItems.GOLD_DUST);
            output.accept(ModItems.COPPER_DUST);

            output.accept(ModItems.DIAMOND_DUST);
            output.accept(ModItems.ENERGIUM_DUST);

            // TINY DUST
            output.accept(ModItems.TIN_TINY_DUST);
            output.accept(ModItems.LEAD_TINY_DUST);
            output.accept(ModItems.SILVER_TINY_DUST);

            output.accept(ModItems.IRON_TINY_DUST);
            output.accept(ModItems.GOLD_TINY_DUST);
            output.accept(ModItems.COPPER_TINY_DUST);

            // ITEMS
            output.accept(ModItems.RECHARGEABLE_BATTERY);
            output.accept(ModItems.RTG_FUEL);
            output.accept(ModItems.ELECTRONIC_CIRCUIT);
            output.accept(ModItems.COIL);

            // TOOLS
            output.accept(ModItems.POWER_METER);

            ///////////////////////////////
            // BLOCKS
            ///////////////////////////////
            
            // BLOCKS
            output.accept(ModBlocks.TIN_BLOCK);
            output.accept(ModBlocks.LEAD_BLOCK);
            output.accept(ModBlocks.SILVER_BLOCK);
            output.accept(ModBlocks.STEEL_BLOCK);
            output.accept(ModBlocks.BRONZE_BLOCK);

            output.accept(ModBlocks.MACHINE_CASING);

            // ORE
            output.accept(ModBlocks.TIN_ORE);
            output.accept(ModBlocks.LEAD_ORE);
            output.accept(ModBlocks.SILVER_ORE);
            
            // DEEPSLATE ORE
            output.accept(ModBlocks.TIN_DEEPSLATE_ORE);
            output.accept(ModBlocks.LEAD_DEEPSLATE_ORE);
            output.accept(ModBlocks.SILVER_DEEPSLATE_ORE);
            
            // GENERATORS
            output.accept(ModBlocks.GENERATOR_BLOCK);
            output.accept(ModBlocks.SOLAR_PANEL_BLOCK);
            output.accept(ModBlocks.RTG_BLOCK);

            // CABLES
            output.accept(ModBlocks.TIN_CABLE_BLOCK);
            output.accept(ModBlocks.COPPER_CABLE_BLOCK);
            output.accept(ModBlocks.GOLD_CABLE_BLOCK);
            output.accept(ModBlocks.SILVER_CABLE_BLOCK);
            output.accept(ModBlocks.SUPER_CONDUCTING_CABLE_BLOCK);
            
            output.accept(ModBlocks.INS_TIN_CABLE_BLOCK);
            output.accept(ModBlocks.INS_COPPER_CABLE_BLOCK);
            output.accept(ModBlocks.INS_GOLD_CABLE_BLOCK);
            output.accept(ModBlocks.INS_SILVER_CABLE_BLOCK);

            // CONSUMERS
            output.accept(ModBlocks.ELECTRIC_FURNACE_BLOCK);
            output.accept(ModBlocks.MACERATOR_BLOCK);
            output.accept(ModBlocks.COMPRESSOR_BLOCK);
            output.accept(ModBlocks.EXTRACTOR_BLOCK);
            output.accept(ModBlocks.EXTRUDER_BLOCK);
            output.accept(ModBlocks.ROLLER_BLOCK);
            
                                                                                                                                              })
                                                                                                                                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
