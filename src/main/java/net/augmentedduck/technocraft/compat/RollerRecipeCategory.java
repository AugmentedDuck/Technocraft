package net.augmentedduck.technocraft.compat;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.recipe.custom.RollerRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

public class RollerRecipeCategory extends AbstractModRecipeCategory<RollerRecipe> {

    public static final RecipeType<RecipeHolder<RollerRecipe>> RECIPE_TYPE = RecipeType.create(Technocraft.MODID, "roller", (Class<RecipeHolder<RollerRecipe>>) (Class<?>) RecipeHolder.class);

    public RollerRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper, RECIPE_TYPE, Component.translatable("block.technocraft.roller"),
            ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "textures/gui/container/roller.png"),
            new ItemStack(ModBlocks.ROLLER_BLOCK.get()));
    }
}
