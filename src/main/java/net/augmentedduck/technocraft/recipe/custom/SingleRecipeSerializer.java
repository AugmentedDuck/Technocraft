package net.augmentedduck.technocraft.recipe.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

/**
 * Generic serializer for every {@link AbstractModSingleRecipe} subtype. They
 * all serialize the same three fields (ingredient / result / count), so each
 * recipe type just hands its constructor reference here instead of
 * redeclaring a Serializer inner class.
 */
public class SingleRecipeSerializer<R extends AbstractModSingleRecipe> implements RecipeSerializer<R> {

    @FunctionalInterface
    public interface Factory<R> {
        R create(Ingredient input, ItemStack output, int inputCount);
    }

    private final MapCodec<R> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, R> streamCodec;

    public SingleRecipeSerializer(Factory<R> factory) {
        this.codec = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(AbstractModSingleRecipe::getInput), 
            ItemStack.CODEC.fieldOf("result").forGetter(AbstractModSingleRecipe::getOutput),
            Codec.INT.optionalFieldOf("count", 1).forGetter(AbstractModSingleRecipe::getInputCount)
        ).apply(instance, factory::create));

        this.streamCodec = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, AbstractModSingleRecipe::getInput, 
            ItemStack.STREAM_CODEC, AbstractModSingleRecipe::getOutput, 
            ByteBufCodecs.VAR_INT, AbstractModSingleRecipe::getInputCount,
            factory::create
        );
    }

    @Override
    public MapCodec<R> codec() {
        return codec;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, R> streamCodec() {
        return streamCodec;
    }

}
