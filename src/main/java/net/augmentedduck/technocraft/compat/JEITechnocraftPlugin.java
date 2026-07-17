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
import net.augmentedduck.technocraft.recipe.custom.*;
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
        registration.addRecipeCategories(new CompressorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ExtractorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ExtruderRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new RollerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = getRecipeManager();
        if (recipeManager == null) return;

        List<RecipeHolder<MacerationRecipe>> maceratingRecipes = recipeManager.getAllRecipesFor(ModRecipeTypes.MACERATING.get());
        registration.addRecipes(MacerationRecipeCategory.RECIPE_TYPE, maceratingRecipes);

        List<RecipeHolder<CompressorRecipe>> compressorRecipes = recipeManager.getAllRecipesFor(ModRecipeTypes.COMPRESSOR.get());
        registration.addRecipes(CompressorRecipeCategory.RECIPE_TYPE, compressorRecipes);

        List<RecipeHolder<ExtractorRecipe>> extractorRecipes = recipeManager.getAllRecipesFor(ModRecipeTypes.EXTRACTOR.get());
        registration.addRecipes(ExtractorRecipeCategory.RECIPE_TYPE, extractorRecipes);

        List<RecipeHolder<ExtruderRecipe>> extruderRecipes = recipeManager.getAllRecipesFor(ModRecipeTypes.EXTRUDER.get());
        registration.addRecipes(ExtruderRecipeCategory.RECIPE_TYPE, extruderRecipes);

        List<RecipeHolder<RollerRecipe>> rollerRecipes = recipeManager.getAllRecipesFor(ModRecipeTypes.ROLLER.get());
        registration.addRecipes(RollerRecipeCategory.RECIPE_TYPE, rollerRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRIC_FURNACE_BLOCK.get()), RecipeTypes.SMELTING);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MACERATOR_BLOCK.get()), MacerationRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.COMPRESSOR_BLOCK.get()), CompressorRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.EXTRACTOR_BLOCK.get()), ExtractorRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.EXTRUDER_BLOCK.get()), ExtruderRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ROLLER_BLOCK.get()), RollerRecipeCategory.RECIPE_TYPE);
    }

    private RecipeManager getRecipeManager() {
        Minecraft minecraft = Minecraft.getInstance();
        return minecraft.level != null ? minecraft.level.getRecipeManager() : null;
    }
}
