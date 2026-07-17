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
import net.augmentedduck.technocraft.recipe.custom.ExtractorRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

public class ExtractorRecipeCategory implements IRecipeCategory<RecipeHolder<ExtractorRecipe>>{

    public static final RecipeType<RecipeHolder<ExtractorRecipe>> RECIPE_TYPE = RecipeType.create(Technocraft.MODID, "extractor", (Class<RecipeHolder<ExtractorRecipe>>) (Class<?>) RecipeHolder.class);

    private final IDrawable background;
    private final IDrawable icon;

    public ExtractorRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "textures/gui/container/extractor.png"), 54, 15, 84, 56);
        this.icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.EXTRACTOR_BLOCK.get()));
    }

    @Override
    public RecipeType<RecipeHolder<ExtractorRecipe>> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.technocraft.extractor");
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
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<ExtractorRecipe> recipeHolder, IFocusGroup focuses) {
        ExtractorRecipe recipe = recipeHolder.value();

        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addIngredients(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 62, 20).addItemStack(recipe.getResultItem(null));
    }
}
