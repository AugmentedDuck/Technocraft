package net.augmentedduck.technocraft.datagen;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider{

    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, Technocraft.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        ///////////////////////////////
        // ITEMS
        ///////////////////////////////
        
        // INGOTS
        add(ModItems.TIN_INGOT.get(), "Tin Ingot");
        add(ModItems.LEAD_INGOT.get(), "Lead Ingot");
        add(ModItems.SILVER_INGOT.get(), "Silver Ingot");
        add(ModItems.STEEL_INGOT.get(), "Steel Ingot");
        add(ModItems.BRONZE_INGOT.get(), "Bronze Ingot");

        // ITEM CASING
        add(ModItems.TIN_CASING.get(), "Tin Item Casing");
        add(ModItems.LEAD_CASING.get(), "Lead Item Casing");
        add(ModItems.SILVER_CASING.get(), "Silver Item Casing");
        add(ModItems.STEEL_CASING.get(), "Steel Item Casing");
        add(ModItems.BRONZE_CASING.get(), "Bronze Item Casing");
        add(ModItems.IRON_CASING.get(), "Iron Item Casing");
        add(ModItems.GOLD_CASING.get(), "Gold Item Casing");
        add(ModItems.COPPER_CASING.get(), "Copper Item Casing");

        // DENSE PLATE
        add(ModItems.TIN_DENSE_PLATE.get(), "Tin Dense Plate");
        add(ModItems.LEAD_DENSE_PLATE.get(), "Lead Dense Plate");
        add(ModItems.SILVER_DENSE_PLATE.get(), "Silver Dense Plate");
        add(ModItems.STEEL_DENSE_PLATE.get(), "Steel Dense Plate");
        add(ModItems.BRONZE_DENSE_PLATE.get(), "Bronze Dense Plate");
        add(ModItems.IRON_DENSE_PLATE.get(), "Iron Dense Plate");
        add(ModItems.GOLD_DENSE_PLATE.get(), "Gold Dense Plate");
        add(ModItems.COPPER_DENSE_PLATE.get(), "Copper Dense Plate");

        // PLATE
        add(ModItems.TIN_PLATE.get(), "Tin Plate");
        add(ModItems.LEAD_PLATE.get(), "Lead Plate");
        add(ModItems.SILVER_PLATE.get(), "Silver Plate");
        add(ModItems.STEEL_PLATE.get(), "Steel Plate");
        add(ModItems.BRONZE_PLATE.get(), "Bronze Plate");
        add(ModItems.IRON_PLATE.get(), "Iron Plate");
        add(ModItems.GOLD_PLATE.get(), "Gold Plate");
        add(ModItems.COPPER_PLATE.get(), "Copper Plate");

        // RAW
        add(ModItems.TIN_RAW.get(), "Raw Tin");
        add(ModItems.LEAD_RAW.get(), "Raw Lead");
        add(ModItems.SILVER_RAW.get(), "Raw Silver");

        // CRUSHED
        add(ModItems.TIN_CRUSHED.get(), "Crushed Tin");
        add(ModItems.LEAD_CRUSHED.get(), "Crushed Lead");
        add(ModItems.SILVER_CRUSHED.get(), "Crushed Silver");
        add(ModItems.IRON_CRUSHED.get(), "Crushed Iron");
        add(ModItems.GOLD_CRUSHED.get(), "Crushed Gold");
        add(ModItems.COPPER_CRUSHED.get(), "Crushed Copper");

        // WASHED
        add(ModItems.TIN_WASHED.get(), "Washed Crushed Tin");
        add(ModItems.LEAD_WASHED.get(), "Washed Crushed Lead");
        add(ModItems.SILVER_WASHED.get(), "Washed Crushed Silver");
        add(ModItems.IRON_WASHED.get(), "Washed Crushed Iron");
        add(ModItems.GOLD_WASHED.get(), "Washed Crushed Gold");
        add(ModItems.COPPER_WASHED.get(), "Washed Crushed Copper");

        // DUST
        add(ModItems.TIN_DUST.get(), "Tin Dust");
        add(ModItems.LEAD_DUST.get(), "Lead Dust");
        add(ModItems.SILVER_DUST.get(), "Silver Dust");
        add(ModItems.BRONZE_DUST.get(), "Bronze Dust");
        add(ModItems.IRON_DUST.get(), "Iron Dust");
        add(ModItems.GOLD_DUST.get(), "Gold Dust");
        add(ModItems.COPPER_DUST.get(), "Copper Dust");

        // TINY DUST PILE
        add(ModItems.TIN_TINY_DUST.get(), "Tiny Pile of Tin");
        add(ModItems.LEAD_TINY_DUST.get(), "Tiny Pile of Lead");
        add(ModItems.SILVER_TINY_DUST.get(), "Tiny Pile of Silver");
        add(ModItems.IRON_TINY_DUST.get(), "Tiny Pile of Iron");
        add(ModItems.GOLD_TINY_DUST.get(), "Tiny Pile of Gold");
        add(ModItems.COPPER_TINY_DUST.get(), "Tiny Pile of Copper");

        // TOOLS
        add(ModItems.POWER_METER.get(), "Power Meter");
        
        // OTHER
        add(ModItems.RECHARGEABLE_BATTERY.get(), "Rechargeable Battery");
        add(ModItems.RTG_FUEL.get(), "RTG Fuel Pellet");

        ///////////////////////////////
        // BLOCKS
        ///////////////////////////////
        
        // BLOCKS
        add(ModBlocks.TIN_BLOCK.get(), "Block of Tin");
        add(ModBlocks.STEEL_BLOCK.get(), "Block of Steel");
        add(ModBlocks.SILVER_BLOCK.get(), "Block of Silver");
        add(ModBlocks.LEAD_BLOCK.get(), "Block of Lead");
        add(ModBlocks.BRONZE_BLOCK.get(), "Block of Bronze");

        // ORE
        add(ModBlocks.TIN_ORE.get(), "Tin Ore");
        add(ModBlocks.SILVER_ORE.get(), "Silver Ore");
        add(ModBlocks.LEAD_ORE.get(), "Lead Ore");

        // DEEPSLATE ORE
        add(ModBlocks.TIN_DEEPSLATE_ORE.get(), "Tin Deepslate Ore");
        add(ModBlocks.SILVER_DEEPSLATE_ORE.get(), "Silver Deepslate Ore");
        add(ModBlocks.LEAD_DEEPSLATE_ORE.get(), "Lead Deepslate Ore");
        
        // GENERATORS
        add(ModBlocks.GENERATOR_BLOCK.get(), "Generator");
        add(ModBlocks.SOLAR_PANEL_BLOCK.get(), "Solar Panel");
        add(ModBlocks.RTG_BLOCK.get(), "Radioisotope Thermoelectric Generator");
        
        // CONSUMERS
        add(ModBlocks.ELECTRIC_FURNACE_BLOCK.get(), "Electric Furnace");
        add(ModBlocks.MACERATOR_BLOCK.get(), "Macerator");
        add(ModBlocks.COMPRESSOR_BLOCK.get(), "Compressor");

        // CABLES
        add(ModBlocks.TIN_CABLE_BLOCK.get(), "Tin Cable");
        add(ModBlocks.COPPER_CABLE_BLOCK.get(), "Copper Cable");
        add(ModBlocks.GOLD_CABLE_BLOCK.get(), "Gold Cable");
        add(ModBlocks.SILVER_CABLE_BLOCK.get(), "Silver Cable");
        add(ModBlocks.SUPER_CONDUCTING_CABLE_BLOCK.get(), "Super Conducting Cable");

        ///////////////////////////////
        // OTHER
        ///////////////////////////////
        
        // TITLES
        add("creativetab.technocraft.tab_title", "Technocraft");

        // TOOLTIPS
        add("item.technocraft.rechargeable_battery.tooltip", "Energy: %s / %s FE");

        // POWER METER
        add("item.technocraft.power_meter.flow", "Cable flow: %s FE/t");
        add("item.technocraft.power_meter.storage", "Stored: %s / %s FE");
    }

}
