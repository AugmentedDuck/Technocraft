package net.augmentedduck.technocraft.recipe.custom;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

public abstract class AbstractModSingleRecipe implements Recipe<SingleRecipeInput>{
    protected final Ingredient input;
    protected final ItemStack output;
    protected final int inputCount;

    public AbstractModSingleRecipe(Ingredient input, ItemStack output, int inputCount) {
        this.input = input;
        this.output = output;
        this.inputCount = inputCount;
    }

    public Ingredient getInput() {
        return input;
    }
    
    public int getInputCount() {
        return inputCount;
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
}
