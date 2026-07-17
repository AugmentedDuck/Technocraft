package net.augmentedduck.technocraft.screen;

import java.util.function.Supplier;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.screen.custom.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Technocraft.MODID);

    // GENERATORS
    public static final Supplier<MenuType<GeneratorMenu>> GENERATOR_MENU = MENUS.register("generator_menu", () -> IMenuTypeExtension.create(GeneratorMenu::new));
    public static final Supplier<MenuType<RTGMenu>> RTG_MENU = MENUS.register("rtg_menu", () -> IMenuTypeExtension.create(RTGMenu::new));
    
    // CONSUMERS
    public static final Supplier<MenuType<ElectricFurnaceMenu>> ELECTRIC_FURNACE_MENU = MENUS.register("electric_furnace_menu", () -> IMenuTypeExtension.create(ElectricFurnaceMenu::new));
    public static final Supplier<MenuType<MaceratorMenu>> MACERATOR_MENU = MENUS.register("macerator_menu", () -> IMenuTypeExtension.create(MaceratorMenu::new));
    public static final Supplier<MenuType<CompressorMenu>> COMPRESSOR_MENU = MENUS.register("compressor_menu", () -> IMenuTypeExtension.create(CompressorMenu::new));
    public static final Supplier<MenuType<ExtractorMenu>> EXTRACTOR_MENU = MENUS.register("extractor_menu", () -> IMenuTypeExtension.create(ExtractorMenu::new));
    public static final Supplier<MenuType<ExtruderMenu>> EXTRUDER_MENU = MENUS.register("extruder_menu", () -> IMenuTypeExtension.create(ExtruderMenu::new));
    public static final Supplier<MenuType<RollerMenu>> ROLLER_MENU = MENUS.register("roller_menu", () -> IMenuTypeExtension.create(RollerMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
