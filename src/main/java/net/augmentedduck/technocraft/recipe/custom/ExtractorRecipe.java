package net.augmentedduck.technocraft.recipe.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.augmentedduck.technocraft.recipe.ModRecipeSerializers;
import net.augmentedduck.technocraft.recipe.ModRecipeTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;

public class ExtractorRecipe extends AbstractModSingleRecipe {

    public ExtractorRecipe(Ingredient input, ItemStack output) {
        super(input, output);
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return ModRecipeSerializers.EXTRACTOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return ModRecipeTypes.EXTRACTOR.get();
    }

    public static class Serializer implements RecipeSerializer<ExtractorRecipe> {
        
        public static final MapCodec<ExtractorRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(ExtractorRecipe::getInput), 
            ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.output)
        ).apply(instance, ExtractorRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ExtractorRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, ExtractorRecipe::getInput, 
            ItemStack.STREAM_CODEC, recipe -> recipe.output, ExtractorRecipe::new
        );
        
        @Override
        public MapCodec<ExtractorRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ExtractorRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
