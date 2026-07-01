package net.augmentedduck.technocraft.item;

import net.augmentedduck.technocraft.Technocraft;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Technocraft.MODID);

    // INGOTS
    public static final DeferredItem<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", () -> new Item(new Item.Properties()));

    // ITEM CASING
    public static final DeferredItem<Item> TIN_CASING = ITEMS.register("tin_casing", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LEAD_CASING = ITEMS.register("lead_casing", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILVER_CASING = ITEMS.register("silver_casing", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_CASING = ITEMS.register("steel_casing", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRONZE_CASING = ITEMS.register("bronze_casing", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> IRON_CASING = ITEMS.register("iron_casing", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_CASING = ITEMS.register("copper_casing", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_CASING = ITEMS.register("gold_casing", () -> new Item(new Item.Properties()));

    // DENSE PLATE
    public static final DeferredItem<Item> TIN_DENSE_PLATE = ITEMS.register("tin_dense_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LEAD_DENSE_PLATE = ITEMS.register("lead_dense_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILVER_DENSE_PLATE = ITEMS.register("silver_dense_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_DENSE_PLATE = ITEMS.register("steel_dense_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRONZE_DENSE_PLATE = ITEMS.register("bronze_dense_plate", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> IRON_DENSE_PLATE = ITEMS.register("iron_dense_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_DENSE_PLATE = ITEMS.register("copper_dense_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_DENSE_PLATE = ITEMS.register("gold_dense_plate", () -> new Item(new Item.Properties()));

    // PLATE
    public static final DeferredItem<Item> TIN_PLATE = ITEMS.register("tin_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LEAD_PLATE = ITEMS.register("lead_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILVER_PLATE = ITEMS.register("silver_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_PLATE = ITEMS.register("steel_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRONZE_PLATE = ITEMS.register("bronze_plate", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> IRON_PLATE = ITEMS.register("iron_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PLATE = ITEMS.register("copper_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_PLATE = ITEMS.register("gold_plate", () -> new Item(new Item.Properties()));

    // TINY DUST

    // DUST

    // WASHED

    // CRUSHED

    // RAW

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
