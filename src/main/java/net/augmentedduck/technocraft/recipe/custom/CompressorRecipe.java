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

public class CompressorRecipe extends AbstractModSingleRecipe {

    public CompressorRecipe(Ingredient input, ItemStack output) {
        super(input, output);
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return ModRecipeSerializers.COMPRESSOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return ModRecipeTypes.COMPRESSOR.get();
    }

    public static class Serializer implements RecipeSerializer<CompressorRecipe> {
        
        public static final MapCodec<CompressorRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(CompressorRecipe::getInput), 
            ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.output)
        ).apply(instance, CompressorRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CompressorRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, CompressorRecipe::getInput, 
            ItemStack.STREAM_CODEC, recipe -> recipe.output, CompressorRecipe::new
        );
        
        @Override
        public MapCodec<CompressorRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CompressorRecipe> streamCodec() {
            return STREAM_CODEC;
        }

    }
}
