package net.augmentedduck.technocraft.screen.custom;

import net.augmentedduck.technocraft.Technocraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ElectricFurnaceScreen extends AbstractContainerScreen<ElectricFurnaceMenu>{

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "textures/gui/container/electric_furnace.png");

    public ElectricFurnaceScreen(ElectricFurnaceMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        // Progress arrow (input slot -> output slot)
        if (menu.isCooking()) {
            int arrowWidth = 24;
            int progressScaled = (int) (arrowWidth * ((float) menu.getCookProgress() / menu.getCookTime()));
            guiGraphics.blit(TEXTURE, x + 79, y + 34, 176, 0, progressScaled, 17);
        }

        // Energy bar
        int barHeight = 52;
        int energyScaled = menu.getMaxEnergy() == 0 ? 0 : (int) (barHeight * ((float) menu.getEnergy() / menu.getMaxEnergy()));
        guiGraphics.blit(TEXTURE, x + 153, y + 15 + barHeight - energyScaled, 200, barHeight - energyScaled, 13, energyScaled);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

}
