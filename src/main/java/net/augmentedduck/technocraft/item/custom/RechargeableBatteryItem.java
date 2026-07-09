package net.augmentedduck.technocraft.item.custom;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class RechargeableBatteryItem extends Item {

    public static final int CAPACITY = 100_000;
    public static final int MAX_TRANSFER = 1_000;

    public RechargeableBatteryItem(Properties properties) {
        super(properties.component(ModDataComponents.ENERGY.get(), 0).stacksTo(64));
    }

    public static int getEnergy(ItemStack stack) {
        return Mth.clamp(stack.getOrDefault(ModDataComponents.ENERGY.get(), 0), 0, CAPACITY);
    }

    public static void setEnergy(ItemStack stack, int energy) {
        stack.set(ModDataComponents.ENERGY.get(), Mth.clamp(energy, 0, CAPACITY));
    }

    // Stacks only when empty or fully charged, any partial charge is unique and unstackable
    @Override
    public int getMaxStackSize(ItemStack stack) {
        int energy = getEnergy(stack);
        return (energy == 0 || energy == CAPACITY) ? 64 : 1;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13.0F * getEnergy(stack) / CAPACITY);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        float f = Math.max(0.0F, (float) getEnergy(stack) / CAPACITY);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.technocraft.rechargeable_battery.tooltip", getEnergy(stack), CAPACITY));
    }

    public static class ItemBatteryEnergyStorage implements IEnergyStorage {
        
        private final ItemStack stack;

        public ItemBatteryEnergyStorage(ItemStack stack) {
            this.stack = stack;
        }

        @Override
        public int receiveEnergy(int toReceive, boolean simulate) {
            int current = getEnergy(stack);
            int received = Math.min(Math.min(toReceive, MAX_TRANSFER), CAPACITY - current);

            if (received <= 0) return 0;
            if (!simulate) setEnergy(stack, current + received);
            return received;
        }

        @Override
        public int extractEnergy(int toExtract, boolean simulate) {
            int current = getEnergy(stack);
            int extracted = Math.min(Math.min(toExtract, MAX_TRANSFER), current);

            if (extracted <= 0) return 0;
            if (!simulate) setEnergy(stack, current - extracted);
            return extracted;
        }

        @Override
        public int getEnergyStored() {
            return getEnergy(stack);
        }

        @Override
        public int getMaxEnergyStored() {
            return CAPACITY;
        }

        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return true;
        }

    }
}
