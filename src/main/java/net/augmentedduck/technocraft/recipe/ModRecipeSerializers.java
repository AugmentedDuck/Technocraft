package net.augmentedduck.technocraft.recipe;

import java.util.function.Supplier;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.recipe.custom.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Technocraft.MODID);

    public static final Supplier<RecipeSerializer<MacerationRecipe>> MACERATION_SERIALIZER = SERIALIZERS.register("macerating", () -> new SingleRecipeSerializer<>(MacerationRecipe::new));
    public static final Supplier<RecipeSerializer<CompressorRecipe>> COMPRESSOR_SERIALIZER = SERIALIZERS.register("compressor", () -> new SingleRecipeSerializer<>(CompressorRecipe::new));
    public static final Supplier<RecipeSerializer<ExtractorRecipe>> EXTRACTOR_SERIALIZER = SERIALIZERS.register("extractor", () -> new SingleRecipeSerializer<>(ExtractorRecipe::new));
    public static final Supplier<RecipeSerializer<ExtruderRecipe>> EXTRUDER_SERIALIZER = SERIALIZERS.register("extruder", () -> new SingleRecipeSerializer<>(ExtruderRecipe::new));
    public static final Supplier<RecipeSerializer<RollerRecipe>> ROLLER_SERIALIZER = SERIALIZERS.register("roller", () -> new SingleRecipeSerializer<>(RollerRecipe::new));

    public static final Supplier<RecipeSerializer<HammerRecipe>> HAMMER_SERIALIZER = SERIALIZERS.register("hammering", HammerRecipeSerializer::new);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
