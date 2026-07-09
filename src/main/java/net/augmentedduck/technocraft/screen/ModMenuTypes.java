package net.augmentedduck.technocraft.screen;

import java.util.function.Supplier;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.screen.custom.ElectricFurnaceMenu;
import net.augmentedduck.technocraft.screen.custom.GeneratorMenu;
import net.augmentedduck.technocraft.screen.custom.MaceratorMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Technocraft.MODID);

    // GENERATORS
    public static final Supplier<MenuType<GeneratorMenu>> GENERATOR_MENU = MENUS.register("generator_menu", () -> IMenuTypeExtension.create(GeneratorMenu::new));
    
    // CONSUMERS
    public static final Supplier<MenuType<ElectricFurnaceMenu>> ELECTRIC_FURNACE_MENU = MENUS.register("electric_furnace_menu", () -> IMenuTypeExtension.create(ElectricFurnaceMenu::new));
    public static final Supplier<MenuType<MaceratorMenu>> MACERATOR_MENU = MENUS.register("macerator_menu", () -> IMenuTypeExtension.create(MaceratorMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
