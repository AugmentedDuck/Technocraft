package net.augmentedduck.technocraft.compat;

import java.util.List;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.recipe.ModRecipeTypes;
import net.augmentedduck.technocraft.recipe.custom.MacerationRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

/**
 * JEI integration: tells JEI which of our machines can actually process which
 * vanilla recipe types, so players see possible options in JEI's UI.
 */
@JeiPlugin
public class JEITechnocraftPlugin implements IModPlugin{

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new MacerationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = getRecipeManager();
        if (recipeManager == null) return;

        List<RecipeHolder<MacerationRecipe>> maceratingRecipes = recipeManager.getAllRecipesFor(ModRecipeTypes.MACERATING.get());
        registration.addRecipes(MacerationRecipeCategory.RECIPE_TYPE, maceratingRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRIC_FURNACE_BLOCK.get()), RecipeTypes.SMELTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MACERATOR_BLOCK.get()), MacerationRecipeCategory.RECIPE_TYPE);
    }

    private RecipeManager getRecipeManager() {
        Minecraft minecraft = Minecraft.getInstance();
        return minecraft.level != null ? minecraft.level.getRecipeManager() : null;
    }
}
