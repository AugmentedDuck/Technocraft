package net.augmentedduck.technocraft.energy;

/**
 * Energy storage for machines that only ever produce energy (e.g. the Generator).
 * maxReceive is forced to 0 so nothing can push FE back into a generator through
 * its capability. The only way energy enters is via {@link AbstractModEnergyStorage#addEnergy}
 * during the generator's own tick logic.
 */
public class GeneratorEnergyStorage extends AbstractModEnergyStorage {

    public GeneratorEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, 0, maxTransfer);
    }
}
