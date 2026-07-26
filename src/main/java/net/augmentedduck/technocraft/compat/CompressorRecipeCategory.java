package net.augmentedduck.technocraft.compat;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.recipe.custom.CompressorRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

public class CompressorRecipeCategory extends AbstractModRecipeCategory<CompressorRecipe> {
    public static final RecipeType<RecipeHolder<CompressorRecipe>> RECIPE_TYPE = RecipeType.create(Technocraft.MODID, "compressing", (Class<RecipeHolder<CompressorRecipe>>) (Class<?>) RecipeHolder.class);

    public CompressorRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper, RECIPE_TYPE, Component.translatable("block.technocraft.compressor"),
            ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "textures/gui/container/compressor.png"),
            new ItemStack(ModBlocks.COMPRESSOR_BLOCK.get()));
    }
}
