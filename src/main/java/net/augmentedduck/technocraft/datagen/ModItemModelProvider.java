package net.augmentedduck.technocraft.datagen;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider{

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Technocraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // BRONZE
        basicItem(ModItems.BRONZE_CASING.get());
        basicItem(ModItems.BRONZE_DENSE_PLATE.get());
        basicItem(ModItems.BRONZE_DUST.get());
        basicItem(ModItems.BRONZE_INGOT.get());
        basicItem(ModItems.BRONZE_PLATE.get());

        // COPPER
        basicItem(ModItems.COPPER_CASING.get());
        basicItem(ModItems.COPPER_CRUSHED.get());
        basicItem(ModItems.COPPER_DENSE_PLATE.get());
        basicItem(ModItems.COPPER_DUST.get());
        basicItem(ModItems.COPPER_PLATE.get());
        basicItem(ModItems.COPPER_TINY_DUST.get());
        basicItem(ModItems.COPPER_WASHED.get());

        // GOLD
        basicItem(ModItems.GOLD_CASING.get());
        basicItem(ModItems.GOLD_CRUSHED.get());
        basicItem(ModItems.GOLD_DENSE_PLATE.get());
        basicItem(ModItems.GOLD_DUST.get());
        basicItem(ModItems.GOLD_PLATE.get());
        basicItem(ModItems.GOLD_TINY_DUST.get());
        basicItem(ModItems.GOLD_WASHED.get());

        // IRON
        basicItem(ModItems.IRON_CASING.get());
        basicItem(ModItems.IRON_CRUSHED.get());
        basicItem(ModItems.IRON_DENSE_PLATE.get());
        basicItem(ModItems.IRON_DUST.get());
        basicItem(ModItems.IRON_PLATE.get());
        basicItem(ModItems.IRON_TINY_DUST.get());
        basicItem(ModItems.IRON_WASHED.get());

        // LEAD
        basicItem(ModItems.LEAD_CASING.get());
        basicItem(ModItems.LEAD_CRUSHED.get());
        basicItem(ModItems.LEAD_DENSE_PLATE.get());
        basicItem(ModItems.LEAD_DUST.get());
        basicItem(ModItems.LEAD_INGOT.get());
        basicItem(ModItems.LEAD_PLATE.get());
        basicItem(ModItems.LEAD_RAW.get());
        basicItem(ModItems.LEAD_TINY_DUST.get());
        basicItem(ModItems.LEAD_WASHED.get());

        // SILVER
        basicItem(ModItems.SILVER_CASING.get());
        basicItem(ModItems.SILVER_CRUSHED.get());
        basicItem(ModItems.SILVER_DENSE_PLATE.get());
        basicItem(ModItems.SILVER_DUST.get());
        basicItem(ModItems.SILVER_INGOT.get());
        basicItem(ModItems.SILVER_PLATE.get());
        basicItem(ModItems.SILVER_RAW.get());
        basicItem(ModItems.SILVER_TINY_DUST.get());
        basicItem(ModItems.SILVER_WASHED.get());

        // STEEL
        basicItem(ModItems.STEEL_CASING.get());
        basicItem(ModItems.STEEL_DENSE_PLATE.get());
        basicItem(ModItems.STEEL_INGOT.get());
        basicItem(ModItems.STEEL_PLATE.get());

        // TIN
        basicItem(ModItems.TIN_CASING.get());
        basicItem(ModItems.TIN_CRUSHED.get());
        basicItem(ModItems.TIN_DENSE_PLATE.get());
        basicItem(ModItems.TIN_DUST.get());
        basicItem(ModItems.TIN_INGOT.get());
        basicItem(ModItems.TIN_PLATE.get());
        basicItem(ModItems.TIN_RAW.get());
        basicItem(ModItems.TIN_TINY_DUST.get());
        basicItem(ModItems.TIN_WASHED.get());

        // OTHER
        basicItem(ModItems.RECHARGEABLE_BATTERY.get());
        basicItem(ModItems.POWER_METER.get());
    }

}
