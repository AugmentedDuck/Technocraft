package net.augmentedduck.technocraft.screen.custom;

import net.augmentedduck.technocraft.Technocraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GeneratorScreen extends AbstractMachineScreen<GeneratorMenu> {

    public GeneratorScreen(GeneratorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "textures/gui/container/generator.png"));
    }

    @Override
    protected void renderProgress(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isLit()) {
            int flameHeight = 14;
            int scaled = (int) (flameHeight * ((float) menu.getLitTime() / menu.getLitDuration()));
            guiGraphics.blit(texture, x + 56, y + 36 + flameHeight - scaled, 212, flameHeight - scaled, 14, scaled);
        }
    }

    @Override
    protected int energyBarTextureU() {
        return 176;
    }
}
