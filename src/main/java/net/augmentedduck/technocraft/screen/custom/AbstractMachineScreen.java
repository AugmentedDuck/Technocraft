package net.augmentedduck.technocraft.screen.custom;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class AbstractMachineScreen<T extends AbstractMachineMenu> extends AbstractContainerScreen<T> {

    protected final ResourceLocation texture;

    public AbstractMachineScreen(T menu, Inventory playerInventory, Component title, ResourceLocation texture) {
        super(menu, playerInventory, title);

        this.texture = texture;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(texture, x, y, 0, 0, this.imageWidth, this.imageHeight);

        renderProgress(guiGraphics, x, y);
        renderEnergyBar(guiGraphics, x, y);
    }

    protected void renderProgress(GuiGraphics guiGraphics, int x, int y) {}

    protected void renderEnergyBar(GuiGraphics guiGraphics, int x, int y) {
        int barHeight = 52;
        int energyScaled = menu.getMaxEnergy() == 0 ? 0 : (int) (barHeight * ((float) menu.getEnergy() / menu.getMaxEnergy()));
        guiGraphics.blit(texture, x + 153, y + 15 + barHeight - energyScaled, energyBarTextureU(), barHeight - energyScaled, 13, energyScaled);
    }

    protected abstract int energyBarTextureU();

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
