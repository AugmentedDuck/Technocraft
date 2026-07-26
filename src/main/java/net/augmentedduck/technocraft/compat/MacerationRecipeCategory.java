package net.augmentedduck.technocraft.compat;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.recipe.custom.MacerationRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

public class MacerationRecipeCategory extends AbstractModRecipeCategory<MacerationRecipe> {

    public static final RecipeType<RecipeHolder<MacerationRecipe>> RECIPE_TYPE = RecipeType.create(Technocraft.MODID, "macerating", (Class<RecipeHolder<MacerationRecipe>>) (Class<?>) RecipeHolder.class);

    public MacerationRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper, RECIPE_TYPE, Component.translatable("block.technocraft.macerator"),
            ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "textures/gui/container/macerator.png"),
            new ItemStack(ModBlocks.MACERATOR_BLOCK.get()));
    }
}
