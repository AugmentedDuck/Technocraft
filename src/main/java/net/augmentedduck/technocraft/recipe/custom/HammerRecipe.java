package net.augmentedduck.technocraft.recipe.custom;

import java.util.List;

import net.augmentedduck.technocraft.item.ModItems;

import net.augmentedduck.technocraft.recipe.ModRecipeSerializers;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

/**
 * Crafting-table recipe for hammering a material into a plate.
 *
 * <p>The grid must contain exactly one {@link net.augmentedduck.technocraft.item.custom.HammerItem}
 * and exactly one stack matching {@link #input}, with nothing else. Unlike a
 * normal shapeless recipe, the hammer is not consumed: {@link #getRemainingItems}
 * returns a copy of it with 1 extra point of damage instead of an empty stack,
 * so vanilla's crafting-slot logic puts it straight back in the grid. Once it
 * would exceed max damage, an empty stack is returned instead and the hammer
 * breaks like any other tool.
 *
 * <p>{@code getType()} must return {@link RecipeType#CRAFTING} rather than a
 * mod-specific type — the crafting table's {@code slotsChanged} handler only
 * ever looks up recipes under that type, same as vanilla's own special
 * crafting recipes (map cloning, armor dyeing, etc).
 */
public class HammerRecipe implements CraftingRecipe {

    private final Ingredient input;
    private final ItemStack output;

    public HammerRecipe(Ingredient input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean foundHammer = false;
        boolean foundMaterial = false;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() == ModItems.HAMMER.get()) {
                if (foundHammer) return false;
                foundHammer = true;
            } else if (this.input.test(stack)) {
                if (foundMaterial) return false;
                foundMaterial = true;
            } else {
                return false;
            }
        }

        return foundHammer && foundMaterial;
    }

    @Override
    public ItemStack assemble(CraftingInput input, Provider registries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public ItemStack getResultItem(Provider registries) {
        return output;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        // Best-effort for the recipe book / JEI grid highlighting; matches()
        // above is still the actual source of truth for validity.
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(input);
        ingredients.add(Ingredient.of(ModItems.HAMMER.get()));
        return ingredients;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.getItem() != ModItems.HAMMER.get()) continue;

            int newDamage = stack.getDamageValue() + 1;
            if (newDamage < stack.getMaxDamage()) {
                ItemStack hammer = stack.copy();
                hammer.setDamageValue(newDamage);
                remaining.set(i, hammer);
            }
            // else: leave EMPTY — the hammer breaks
        }

        return remaining;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.HAMMER_SERIALIZER.get();
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    @Override
    public RecipeType<? extends Recipe<CraftingInput>> getType() {
        return RecipeType.CRAFTING;
    }

}
