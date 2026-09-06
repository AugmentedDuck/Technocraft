package net.augmentedduck.technocraft.item.custom;

import net.minecraft.world.item.Item;

/**
 * IC2-style forge hammer. Not consumed when used to hammer ingots into
 * plates — see {@link net.augmentedduck.technocraft.recipe.custom.HammerRecipe},
 * which damages it by 1 per craft instead of shrinking its stack.
 */
public class HammerItem extends Item {

    public static final int MAX_DURABILITY = 256;

    public HammerItem(Properties properties) {
        super(properties.durability(MAX_DURABILITY));
    }
    
}
