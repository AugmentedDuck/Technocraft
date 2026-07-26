package net.augmentedduck.technocraft.compat;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.recipe.custom.ExtractorRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

public class ExtractorRecipeCategory extends AbstractModRecipeCategory<ExtractorRecipe> {

    public static final RecipeType<RecipeHolder<ExtractorRecipe>> RECIPE_TYPE = RecipeType.create(Technocraft.MODID, "extractor", (Class<RecipeHolder<ExtractorRecipe>>) (Class<?>) RecipeHolder.class);

    public ExtractorRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper, RECIPE_TYPE, Component.translatable("block.technocraft.extractor"),
            ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "textures/gui/container/extractor.png"),
            new ItemStack(ModBlocks.EXTRACTOR_BLOCK.get()));
    }
}
