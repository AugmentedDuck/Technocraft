package net.augmentedduck.technocraft.recipe.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.augmentedduck.technocraft.recipe.ModRecipeSerializers;
import net.augmentedduck.technocraft.recipe.ModRecipeTypes;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

/**
 * A single-ingredient, single-result recipe used by the Macerator.
 *
 * <p>Unlike vanilla cooking recipes, maceration has no experience or fuel concept,
 * it's purely energy-driven, so it needs its own recipe type rather than reusing
 * {@link net.minecraft.world.item.crafting.SmeltingRecipe}.
 */
public class MacerationRecipe implements Recipe<SingleRecipeInput> {

    private final Ingredient input;
    private final ItemStack output;

    public MacerationRecipe(Ingredient input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public Ingredient getInput() {
        return input;
    }

    @Override
    public boolean matches(SingleRecipeInput recipeInput, Level level) {
        return input.test(recipeInput.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput recipeInput, Provider registries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(Provider registries) {
        return output;
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return ModRecipeSerializers.MACERATION_SERIALIZER.get();
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return ModRecipeTypes.MACERATING.get();
    }

    public static class Serializer implements RecipeSerializer<MacerationRecipe> {
        
        public static final MapCodec<MacerationRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(MacerationRecipe::getInput), 
            ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.output)
        ).apply(instance, MacerationRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, MacerationRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, MacerationRecipe::getInput, 
            ItemStack.STREAM_CODEC, recipe -> recipe.output, MacerationRecipe::new
        );
        
        @Override
        public MapCodec<MacerationRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MacerationRecipe> streamCodec() {
            return STREAM_CODEC;
        }

    }
}
