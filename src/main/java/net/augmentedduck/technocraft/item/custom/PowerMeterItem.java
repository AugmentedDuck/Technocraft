package net.augmentedduck.technocraft.item.custom;

import net.augmentedduck.technocraft.block.entity.CableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

/**
 * Handheld diagnostic tool.
 *
 * <p>Right-clicking a Cable reports its current throughput; right-clicking
 * anything else exposing an energy capability reports its stored/max energy.
 * Both readings go to chat for now — a HUD overlay or tooltip is a natural
 * upgrade once there's more than a single number worth showing.
 */
public class PowerMeterItem extends Item{

    public PowerMeterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        
        Level level = context.getLevel();
        if (level.isClientSide) return InteractionResult.SUCCESS;

        Player player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;

        BlockPos pos = context.getClickedPos();

        if (level.getBlockEntity(pos) instanceof CableBlockEntity cable) {
            player.sendSystemMessage(Component.translatable("item.technocraft.power_meter.flow", cable.getFlow()));
            return InteractionResult.CONSUME;
        }

        IEnergyStorage storage = level.getCapability(Capabilities.EnergyStorage.BLOCK, pos, context.getClickedFace());
        if (storage != null) {
            player.sendSystemMessage(Component.translatable("item.technocraft.power_meter.storage", storage.getEnergyStored(), storage.getMaxEnergyStored()));
            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }

}
