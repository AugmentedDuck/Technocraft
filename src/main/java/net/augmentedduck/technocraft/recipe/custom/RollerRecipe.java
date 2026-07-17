package net.augmentedduck.technocraft.recipe.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.augmentedduck.technocraft.recipe.ModRecipeSerializers;
import net.augmentedduck.technocraft.recipe.ModRecipeTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;

/**
 * A single-ingredient, single-result recipe used by the Macerator.
 *
 * <p>Unlike vanilla cooking recipes, maceration has no experience or fuel concept,
 * it's purely energy-driven, so it needs its own recipe type rather than reusing
 * {@link net.minecraft.world.item.crafting.SmeltingRecipe}.
 */
public class RollerRecipe extends AbstractModSingleRecipe {

    public RollerRecipe(Ingredient input, ItemStack output) {
        super(input, output, 1);
    }

    public RollerRecipe(Ingredient input, ItemStack output, int inputCount) {
        super(input, output, inputCount);
    }


    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return ModRecipeSerializers.ROLLER_SERIALIZER.get();
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return ModRecipeTypes.ROLLER.get();
    }

    public static class Serializer implements RecipeSerializer<RollerRecipe> {
        
        public static final MapCodec<RollerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(RollerRecipe::getInput), 
            ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.output),
            Codec.INT.optionalFieldOf("count", 1).forGetter(RollerRecipe::getInputCount)
        ).apply(instance, RollerRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, RollerRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, RollerRecipe::getInput, 
            ItemStack.STREAM_CODEC, recipe -> recipe.output, 
            ByteBufCodecs.VAR_INT, RollerRecipe::getInputCount,
            RollerRecipe::new
        );
        
        @Override
        public MapCodec<RollerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, RollerRecipe> streamCodec() {
            return STREAM_CODEC;
        }

    }
}
