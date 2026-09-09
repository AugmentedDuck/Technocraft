package net.augmentedduck.technocraft.recipe.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class CutterItemRecipeSerializer implements RecipeSerializer<CutterItemRecipe> {

    private static final MapCodec<CutterItemRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(CutterItemRecipe::getInput),
        ItemStack.CODEC.fieldOf("result").forGetter(CutterItemRecipe::getOutput)
    ).apply(instance, CutterItemRecipe::new));

    private static final StreamCodec<RegistryFriendlyByteBuf, CutterItemRecipe> STREAM_CODEC = StreamCodec.composite(
        Ingredient.CONTENTS_STREAM_CODEC, CutterItemRecipe::getInput,
        ItemStack.STREAM_CODEC, CutterItemRecipe::getOutput,
        CutterItemRecipe::new
    );

    @Override
    public MapCodec<CutterItemRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, CutterItemRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}