package net.augmentedduck.technocraft.item.custom;

import net.minecraft.world.item.Item;

/**
 * IC2-style forge cutter. Not consumed when used to cut plates into
 * cables — see {@link net.augmentedduck.technocraft.recipe.custom.CutterItemRecipe},
 * which damages it by 1 per craft instead of shrinking its stack.
 */
public class CutterItem extends Item {

    public static final int MAX_DURABILITY = 256;

    public CutterItem(Properties properties) {
        super(properties.durability(MAX_DURABILITY));
    }
}
