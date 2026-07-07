package net.augmentedduck.technocraft.datagen;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.block.custom.GeneratorBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider{

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Technocraft.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BRONZE_BLOCK);
        blockWithItem(ModBlocks.TIN_BLOCK);
        blockWithItem(ModBlocks.SILVER_BLOCK);
        blockWithItem(ModBlocks.STEEL_BLOCK);
        blockWithItem(ModBlocks.LEAD_BLOCK);
        
        blockWithItem(ModBlocks.TIN_ORE);
        blockWithItem(ModBlocks.SILVER_ORE);
        blockWithItem(ModBlocks.LEAD_ORE);
        
        blockWithItem(ModBlocks.TIN_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.SILVER_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.LEAD_DEEPSLATE_ORE);

        machineBlockState(ModBlocks.GENERATOR_BLOCK);
        machineBlockState(ModBlocks.ELECTRIC_FURNACE_BLOCK);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void machineBlockState(DeferredBlock<?> deferredBlock) {
        Block machine = deferredBlock.get();
        String name = deferredBlock.getId().getPath(); // e.g. "generator"

        BlockModelBuilder offModel = models().orientableWithBottom(name, modLoc("block/machine_side"), modLoc("block/" + name + "_front_unlit"), modLoc("block/machine_bottom"), modLoc("block/machine_top"));
        BlockModelBuilder onModel = models().orientableWithBottom(name + "_lit", modLoc("block/machine_side"), modLoc("block/" + name + "_front_lit"), modLoc("block/machine_bottom"), modLoc("block/machine_top"));

        getVariantBuilder(machine).forAllStates(state -> {
            Direction facing = state.getValue(GeneratorBlock.FACING);
            boolean lit = state.getValue(GeneratorBlock.LIT);
            
            return ConfiguredModel.builder().modelFile(lit ? onModel : offModel).rotationY(((int) facing.toYRot() + 180) % 360).build();
        });

        itemModels().withExistingParent(name, modLoc("block/" + name));
    }
}
