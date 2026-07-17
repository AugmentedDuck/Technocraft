package net.augmentedduck.technocraft;

import net.augmentedduck.technocraft.screen.ModMenuTypes;
import net.augmentedduck.technocraft.screen.custom.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = Technocraft.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = Technocraft.MODID, value = Dist.CLIENT)
public class TechnocraftClient {
    public TechnocraftClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
       
    }

    @SubscribeEvent
    static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.GENERATOR_MENU.get(), GeneratorScreen::new);
        event.register(ModMenuTypes.RTG_MENU.get(), RTGScreen::new);
        event.register(ModMenuTypes.ELECTRIC_FURNACE_MENU.get(), ElectricFurnaceScreen::new);
        event.register(ModMenuTypes.MACERATOR_MENU.get(), MaceratorScreen::new);
        event.register(ModMenuTypes.COMPRESSOR_MENU.get(), CompressorScreen::new);
        event.register(ModMenuTypes.EXTRACTOR_MENU.get(), ExtractorScreen::new);
        event.register(ModMenuTypes.EXTRUDER_MENU.get(), ExtruderScreen::new);
        event.register(ModMenuTypes.ROLLER_MENU.get(), RollerScreen::new);
    }
}
