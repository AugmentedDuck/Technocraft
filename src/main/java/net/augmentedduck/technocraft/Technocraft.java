package net.augmentedduck.technocraft;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.augmentedduck.technocraft.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Technocraft.MODID)
public class Technocraft {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "technocraft";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Technocraft(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Technocraft) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
       
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            // INGOTS
            event.accept(ModItems.TIN_INGOT);
            event.accept(ModItems.LEAD_INGOT);
            event.accept(ModItems.SILVER_INGOT);
            event.accept(ModItems.STEEL_INGOT);
            event.accept(ModItems.BRONZE_INGOT);

            // CASING
            event.accept(ModItems.TIN_CASING);
            event.accept(ModItems.LEAD_CASING);
            event.accept(ModItems.SILVER_CASING);
            event.accept(ModItems.STEEL_CASING);
            event.accept(ModItems.BRONZE_CASING);

            event.accept(ModItems.IRON_CASING);
            event.accept(ModItems.GOLD_CASING);
            event.accept(ModItems.COPPER_CASING);

            // DENSE PLATE
            event.accept(ModItems.TIN_DENSE_PLATE);
            event.accept(ModItems.LEAD_DENSE_PLATE);
            event.accept(ModItems.SILVER_DENSE_PLATE);
            event.accept(ModItems.STEEL_DENSE_PLATE);
            event.accept(ModItems.BRONZE_DENSE_PLATE);

            event.accept(ModItems.IRON_DENSE_PLATE);
            event.accept(ModItems.GOLD_DENSE_PLATE);
            event.accept(ModItems.COPPER_DENSE_PLATE);
            
            // PLATE
            event.accept(ModItems.TIN_PLATE);
            event.accept(ModItems.LEAD_PLATE);
            event.accept(ModItems.SILVER_PLATE);
            event.accept(ModItems.STEEL_PLATE);
            event.accept(ModItems.BRONZE_PLATE);

            event.accept(ModItems.IRON_PLATE);
            event.accept(ModItems.GOLD_PLATE);
            event.accept(ModItems.COPPER_PLATE);

            // RAW
            event.accept(ModItems.TIN_RAW);
            event.accept(ModItems.LEAD_RAW);
            event.accept(ModItems.SILVER_RAW);

            // CRUSHED
            event.accept(ModItems.TIN_CRUSHED);
            event.accept(ModItems.LEAD_CRUSHED);
            event.accept(ModItems.SILVER_CRUSHED);

            event.accept(ModItems.IRON_CRUSHED);
            event.accept(ModItems.GOLD_CRUSHED);
            event.accept(ModItems.COPPER_CRUSHED);

            // WASHED
            event.accept(ModItems.TIN_WASHED);
            event.accept(ModItems.LEAD_WASHED);
            event.accept(ModItems.SILVER_WASHED);

            event.accept(ModItems.IRON_WASHED);
            event.accept(ModItems.GOLD_WASHED);
            event.accept(ModItems.COPPER_WASHED);

            // DUST
            event.accept(ModItems.TIN_DUST);
            event.accept(ModItems.LEAD_DUST);
            event.accept(ModItems.SILVER_DUST);
            event.accept(ModItems.BRONZE_DUST);

            event.accept(ModItems.IRON_DUST);
            event.accept(ModItems.GOLD_DUST);
            event.accept(ModItems.COPPER_DUST);

            // TINY DUST
            event.accept(ModItems.TIN_TINY_DUST);
            event.accept(ModItems.LEAD_TINY_DUST);
            event.accept(ModItems.SILVER_TINY_DUST);

            event.accept(ModItems.IRON_TINY_DUST);
            event.accept(ModItems.GOLD_TINY_DUST);
            event.accept(ModItems.COPPER_TINY_DUST);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        
    }
}
