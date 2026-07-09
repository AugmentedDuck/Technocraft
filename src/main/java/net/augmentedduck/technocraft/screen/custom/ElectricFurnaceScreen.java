package net.augmentedduck.technocraft.screen.custom;

import net.augmentedduck.technocraft.Technocraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ElectricFurnaceScreen extends AbstractMachineScreen<ElectricFurnaceMenu>{

    public ElectricFurnaceScreen(ElectricFurnaceMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "textures/gui/container/electric_furnace.png"));
    }

    @Override
    protected void renderProgress(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCooking()) {
            int arrowWidth = 24;
            int progressScaled = (int) (arrowWidth * ((float) menu.getCookProgress() / menu.getCookTime()));
            guiGraphics.blit(texture, x + 79, y + 34, 176, 0, progressScaled, 17);
        }
    }

    @Override
    protected int energyBarTextureU() {
        return 200;
    }
}
