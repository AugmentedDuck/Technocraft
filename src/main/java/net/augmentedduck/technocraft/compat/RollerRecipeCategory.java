package net.augmentedduck.technocraft.compat;

import org.jetbrains.annotations.Nullable;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.recipe.custom.RollerRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

public class RollerRecipeCategory implements IRecipeCategory<RecipeHolder<RollerRecipe>>{

    public static final RecipeType<RecipeHolder<RollerRecipe>> RECIPE_TYPE = RecipeType.create(Technocraft.MODID, "roller", (Class<RecipeHolder<RollerRecipe>>) (Class<?>) RecipeHolder.class);

    private final IDrawable background;
    private final IDrawable icon;

    public RollerRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "textures/gui/container/roller.png"), 54, 15, 84, 56);
        this.icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.ROLLER_BLOCK.get()));
    }

    @Override
    public RecipeType<RecipeHolder<RollerRecipe>> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.technocraft.roller");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<RollerRecipe> recipeHolder, IFocusGroup focuses) {
        RollerRecipe recipe = recipeHolder.value();

        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addIngredients(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 62, 20).addItemStack(recipe.getResultItem(null));
    }
}
