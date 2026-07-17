package net.augmentedduck.technocraft.recipe;

import java.util.function.Supplier;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.recipe.custom.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Technocraft.MODID);

    public static final Supplier<RecipeType<MacerationRecipe>> MACERATING = registerRecipeType("macerating");
    public static final Supplier<RecipeType<CompressorRecipe>> COMPRESSOR = registerRecipeType("compressor");
    public static final Supplier<RecipeType<ExtractorRecipe>> EXTRACTOR = registerRecipeType("extractor");
    public static final Supplier<RecipeType<ExtruderRecipe>> EXTRUDER = registerRecipeType("extruder");
    public static final Supplier<RecipeType<RollerRecipe>> ROLLER = registerRecipeType("roller");

    private static <T extends Recipe<?>> Supplier<RecipeType<T>> registerRecipeType(String name) {
        return RECIPE_TYPES.register(name, () -> new RecipeType<T>() {
            @Override
            public String toString() {
                return Technocraft.MODID + ":" + name;
            }
        });
    }

    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }
}
