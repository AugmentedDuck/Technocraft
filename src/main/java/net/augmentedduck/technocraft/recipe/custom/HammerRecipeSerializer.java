package net.augmentedduck.technocraft.recipe.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class HammerRecipeSerializer implements RecipeSerializer<HammerRecipe> {

    private static final MapCodec<HammerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(HammerRecipe::getInput),
        ItemStack.CODEC.fieldOf("result").forGetter(HammerRecipe::getOutput)
    ).apply(instance, HammerRecipe::new));

    private static final StreamCodec<RegistryFriendlyByteBuf, HammerRecipe> STREAM_CODEC = StreamCodec.composite(
        Ingredient.CONTENTS_STREAM_CODEC, HammerRecipe::getInput,
        ItemStack.STREAM_CODEC, HammerRecipe::getOutput,
        HammerRecipe::new
    );

    @Override
    public MapCodec<HammerRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, HammerRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}