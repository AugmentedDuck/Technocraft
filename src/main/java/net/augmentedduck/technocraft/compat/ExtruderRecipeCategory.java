package net.augmentedduck.technocraft.compat;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.recipe.custom.ExtruderRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

public class ExtruderRecipeCategory extends AbstractModRecipeCategory<ExtruderRecipe> {

    public static final RecipeType<RecipeHolder<ExtruderRecipe>> RECIPE_TYPE = RecipeType.create(Technocraft.MODID, "extruder", (Class<RecipeHolder<ExtruderRecipe>>) (Class<?>) RecipeHolder.class);

    public ExtruderRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper, RECIPE_TYPE, Component.translatable("block.technocraft.extruder"),
            ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "textures/gui/container/extruder.png"),
            new ItemStack(ModBlocks.EXTRUDER_BLOCK.get()));
    }
}
