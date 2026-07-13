package net.augmentedduck.technocraft.screen.custom;

import net.augmentedduck.technocraft.Technocraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class RTGScreen extends AbstractMachineScreen<RTGMenu>{

    public RTGScreen(RTGMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "textures/gui/container/rtg.png"));
    }

    @Override
    protected int energyBarTextureU() {
        return 200;
    }

}
