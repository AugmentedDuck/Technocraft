package net.augmentedduck.technocraft.recipe.custom;

import net.augmentedduck.technocraft.recipe.ModRecipeSerializers;
import net.augmentedduck.technocraft.recipe.ModRecipeTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;

public class ExtruderRecipe extends AbstractModSingleRecipe {

    public ExtruderRecipe(Ingredient input, ItemStack output) {
        super(input, output, 1);
    }

    public ExtruderRecipe(Ingredient input, ItemStack output, int inputCount) {
        super(input, output, inputCount);
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return ModRecipeSerializers.EXTRUDER_SERIALIZER.get();
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return ModRecipeTypes.EXTRUDER.get();
    }
}
