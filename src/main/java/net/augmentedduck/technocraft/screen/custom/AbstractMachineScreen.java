package net.augmentedduck.technocraft.screen.custom;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * Base screen implementation for Technocraft machines.
 * 
 * <p> This class provides common GUI fuctionality shared by all machine screens, including:
 * <ul>
 *  <li>Rendering the background texture
 *  <li>Rendering a vertical energy bar
 *  <li>A hook for rendering machine-specific program indicators.
 * </ul>
 * @param <T> The type of menu used by this screen
 */
public abstract class AbstractMachineScreen<T extends AbstractMachineMenu> extends AbstractContainerScreen<T> {
    /** The texture usd as the background for this machine GUI */
    protected final ResourceLocation texture;

    /**
     * Creats a new machine screen.
     * 
     * @param menu            The menu backing this screen
     * @param playerInventory The player's inventory
     * @param title           The title displayed at the top of the GUI
     * @param texture         The GUI texture
     */
    public AbstractMachineScreen(T menu, Inventory playerInventory, Component title, ResourceLocation texture) {
        super(menu, playerInventory, title);
        this.texture = texture;

        // Vanilla GUI size.
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    /**
     * Renders the background layer of GUI.
     * 
     * This includes the background texture, machine specific progresss indicator, and the energy bar.
     */
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(texture, x, y, 0, 0, this.imageWidth, this.imageHeight);

        renderProgress(guiGraphics, x, y);
        renderEnergyBar(guiGraphics, x, y);
    }

    /**
     * Renders any machine-specific progress indicator.
     * 
     * The default implementation does nothing. Subclasses may override this to draw progress arrows, cooking bars, etc.
     * 
     * @param guiGraphics The graphics context
     * @param x           The GUI's x-coordinate
     * @param y           The GUI's y-coordinate
     */
    protected void renderProgress(GuiGraphics guiGraphics, int x, int y) {}

    /**
     * Renders the machine's vertical energy bar.
     * 
     * The bar is scaled based on the current amount of stored energy.
     * 
     * @param guiGraphics The graphics context
     * @param x           The GUI's x-coordinate
     * @param y           The GUI's y-coordinate
     */
    protected void renderEnergyBar(GuiGraphics guiGraphics, int x, int y) {
        int barHeight = 52;
        int energyScaled = menu.getMaxEnergy() == 0 ? 0 : (int) (barHeight * ((float) menu.getEnergy() / menu.getMaxEnergy()));

        guiGraphics.blit(texture, x + 153, y + 15 + barHeight - energyScaled, energyBarTextureU(), barHeight - energyScaled, 13, energyScaled);
    }

    /**
     * Returns the U-coordinate of the energy bar within the GUI texture.
     *
     * This allows different machine GUIs to use different texture layouts
     * while sharing the same rendering logic.
     *
     * @return The texture U-coordinate of the energy bar
     */
    protected abstract int energyBarTextureU();

    /**
     * Renders the complete screen.
     *
     * This includes the GUI itself and any tooltips for hovered elements.
     */
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
