package net.augmentedduck.technocraft.compat;

import java.util.Arrays;
import java.util.List;

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
import net.augmentedduck.technocraft.recipe.custom.MacerationRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

public class MacerationRecipeCategory implements IRecipeCategory<RecipeHolder<MacerationRecipe>>{

    public static final RecipeType<RecipeHolder<MacerationRecipe>> RECIPE_TYPE = RecipeType.create(Technocraft.MODID, "macerating", (Class<RecipeHolder<MacerationRecipe>>) (Class<?>) RecipeHolder.class);

    private final IDrawable background;
    private final IDrawable icon;

    public MacerationRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "textures/gui/container/macerator.png"), 54, 15, 84, 56);
        this.icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.MACERATOR_BLOCK.get()));
    }

    @Override
    public RecipeType<RecipeHolder<MacerationRecipe>> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.technocraft.macerator");
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
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<MacerationRecipe> recipeHolder, IFocusGroup focuses) {
        MacerationRecipe recipe = recipeHolder.value();

        List<ItemStack> inputStacks = Arrays.stream(recipe.getInput().getItems()).map(stack -> {
            ItemStack display = stack.copy();
            display.setCount(recipe.getInputCount());
            return display;
        }).toList();

        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addItemStacks(inputStacks);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 62, 20).addItemStack(recipe.getResultItem(null));
    }
}
