package net.augmentedduck.technocraft.screen.custom;

import net.augmentedduck.technocraft.Technocraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GeneratorScreen extends AbstractContainerScreen<GeneratorMenu> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "textures/gui/container/generator.png");

    public GeneratorScreen(GeneratorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        // Burn time indicator (fuel slot, 56,53 -> right next to it)
        // int flameHeight = 14;
        // int litScaled = menu.getLitDuration() == 0 ? 0 : (int) (flameHeight * ((float) menu.getLitTime() / menu.getLitDuration()));
        // guiGraphics.fill(x + 76, y + 53 + (flameHeight - litScaled), x + 86, y + 53 + flameHeight, 0xFFFF8000); // orange

        // Energy bar (right side of GUI)
        // int barHeight = 52;
        // int energyScaled = menu.getMaxEnergy() == 0 ? 0 : (int) (barHeight * ((float) menu.getEnergy() / menu.getMaxEnergy()));
        // guiGraphics.fill(x + 152, y + 15 + (barHeight - energyScaled), x + 166, y + 15 + barHeight, 0xFFE00000); // red, like FE bars

        if (menu.isLit()) {
            int flameHeight = 14;
            int scaled = (int) (flameHeight * ((float) menu.getLitTime() / menu.getLitDuration()));
            guiGraphics.blit(TEXTURE, x + 56, y + 36 + flameHeight - scaled, 212, flameHeight - scaled, 14, scaled);
        }

        int barHeight = 52;
        int energyScaled = menu.getMaxEnergy() == 0 ? 0 : (int) (barHeight * ((float) menu.getEnergy() / menu.getMaxEnergy()));
        guiGraphics.blit(TEXTURE, x + 153, y + 15 + barHeight - energyScaled, 176, barHeight - energyScaled, 13, energyScaled);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
