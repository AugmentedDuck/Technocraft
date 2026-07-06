package net.augmentedduck.technocraft.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.augmentedduck.technocraft.Technocraft;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEITechnocraftPlugin implements IModPlugin{

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, "jei_plugin");
    }

}
