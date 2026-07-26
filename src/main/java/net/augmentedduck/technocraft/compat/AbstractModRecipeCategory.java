package net.augmentedduck.technocraft.compat;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.augmentedduck.technocraft.recipe.custom.AbstractModSingleRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

/** 
 * Generic JEI category for every {@link AbstractModSingleRecipe}-based machine: one input slot, one output slot. 
 */
public abstract class AbstractModRecipeCategory<R extends AbstractModSingleRecipe> implements IRecipeCategory<RecipeHolder<R>>{
    private final RecipeType<RecipeHolder<R>> recipeType;
    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;
    
    protected AbstractModRecipeCategory(IGuiHelper guiHelper, RecipeType<RecipeHolder<R>> recipeType, Component title, ResourceLocation backgroundTexture, ItemStack iconStack) {
        this.recipeType = recipeType;
        this.title = title;
        this.background = guiHelper.createDrawable(backgroundTexture, 54, 15, 84, 56);
        this.icon = guiHelper.createDrawableItemStack(iconStack);
    }

    @Override
    public RecipeType<RecipeHolder<R>> getRecipeType() { return recipeType; }

    @Override 
    public Component getTitle() { return title; }

    @Override 
    public IDrawable getBackground() { return background; }

    @Nullable
    @Override
    public IDrawable getIcon() { return icon; }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<R> recipeHolder, IFocusGroup focuses) {
        R recipe = recipeHolder.value();

        List<ItemStack> inputStacks = Arrays.stream(recipe.getInput().getItems()).map(stack -> {
            ItemStack display = stack.copy();
            display.setCount(recipe.getInputCount());
            return display;
        }).toList();

        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addItemStacks(inputStacks);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 62, 20).addItemStack(recipe.getResultItem(null));
    }
}
