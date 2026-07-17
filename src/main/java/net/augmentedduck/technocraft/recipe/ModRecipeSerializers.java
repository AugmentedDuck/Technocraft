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

    public static final Supplier<MacerationRecipe.Serializer> MACERATION_SERIALIZER = SERIALIZERS.register("macerating", MacerationRecipe.Serializer::new);
    public static final Supplier<CompressorRecipe.Serializer> COMPRESSOR_SERIALIZER = SERIALIZERS.register("compressor", CompressorRecipe.Serializer::new);
    public static final Supplier<ExtractorRecipe.Serializer> EXTRACTOR_SERIALIZER = SERIALIZERS.register("extractor", ExtractorRecipe.Serializer::new);
    public static final Supplier<ExtruderRecipe.Serializer> EXTRUDER_SERIALIZER = SERIALIZERS.register("extruder", ExtruderRecipe.Serializer::new);
    public static final Supplier<RollerRecipe.Serializer> ROLLER_SERIALIZER = SERIALIZERS.register("roller", RollerRecipe.Serializer::new);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
