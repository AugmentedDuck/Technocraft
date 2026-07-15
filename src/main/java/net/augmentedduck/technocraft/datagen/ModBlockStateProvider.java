package net.augmentedduck.technocraft.datagen;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.augmentedduck.technocraft.block.custom.AbstractCableBlock;
import net.augmentedduck.technocraft.block.custom.GeneratorBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider{

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Technocraft.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ///////////////////////////////////
        // SIMPLE BLOCK WITH ITEM
        ///////////////////////////////////
        
        // BLOCKS
        blockWithItem(ModBlocks.BRONZE_BLOCK);
        blockWithItem(ModBlocks.TIN_BLOCK);
        blockWithItem(ModBlocks.SILVER_BLOCK);
        blockWithItem(ModBlocks.STEEL_BLOCK);
        blockWithItem(ModBlocks.LEAD_BLOCK);
        
        // ORES
        blockWithItem(ModBlocks.TIN_ORE);
        blockWithItem(ModBlocks.SILVER_ORE);
        blockWithItem(ModBlocks.LEAD_ORE);
        
        // DEEPSLATE ORES
        blockWithItem(ModBlocks.TIN_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.SILVER_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.LEAD_DEEPSLATE_ORE);

        /////////////////////////////////////
        // MACHINE BLOCKS
        /////////////////////////////////////

        // GENERATORS
        machineBlockState(ModBlocks.GENERATOR_BLOCK);
        topBlockState(ModBlocks.SOLAR_PANEL_BLOCK);
        topBlockState(ModBlocks.RTG_BLOCK);

        
        // CONSUMERS
        machineBlockState(ModBlocks.ELECTRIC_FURNACE_BLOCK);
        machineBlockState(ModBlocks.MACERATOR_BLOCK);
        machineBlockState(ModBlocks.COMPRESSOR_BLOCK);

        /////////////////////////////////////
        // CABLES
        /////////////////////////////////////
        
        cableBlockState(ModBlocks.TIN_CABLE_BLOCK);
        cableBlockState(ModBlocks.COPPER_CABLE_BLOCK);
        cableBlockState(ModBlocks.GOLD_CABLE_BLOCK);
        cableBlockState(ModBlocks.SILVER_CABLE_BLOCK);
        cableBlockState(ModBlocks.SUPER_CONDUCTING_CABLE_BLOCK);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    /**
     * For blocks with LIT/UNLIT and FACING states. With the same bottom and top face.
     * 
     * @param deferredBlock The block you want to add
     */
    private void machineBlockState(DeferredBlock<?> deferredBlock) {
        Block machine = deferredBlock.get();
        String name = deferredBlock.getId().getPath();

        // UNLIT
        BlockModelBuilder offModel = models().orientableWithBottom(name, modLoc("block/machine_side"), modLoc("block/" + name + "_front_unlit"), modLoc("block/machine_bottom"), modLoc("block/machine_top"));
        
        // LIT
        BlockModelBuilder onModel = models().orientableWithBottom(name + "_lit", modLoc("block/machine_side"), modLoc("block/" + name + "_front_lit"), modLoc("block/machine_bottom"), modLoc("block/machine_top"));

        getVariantBuilder(machine).forAllStates(state -> {
            Direction facing = state.getValue(GeneratorBlock.FACING);
            boolean lit = state.getValue(GeneratorBlock.LIT);
            
            return ConfiguredModel.builder().modelFile(lit ? onModel : offModel).rotationY(((int) facing.toYRot() + 180) % 360).build();
        });

        itemModels().withExistingParent(name, modLoc("block/" + name));
    }

    private void topBlockState(DeferredBlock<?> deferredBlock) {
        Block machine = deferredBlock.get();
        String name = deferredBlock.getId().getPath();
        
        BlockModelBuilder model = models().cubeBottomTop(name, modLoc("block/machine_side"), modLoc("block/machine_bottom"), modLoc("block/" + name + "_top"));

        getVariantBuilder(machine).forAllStates(state -> {
            
            return ConfiguredModel.builder().modelFile(model).build();
        });

        itemModels().withExistingParent(name, modLoc("block/" + name));
    }

    private void cableBlockState(DeferredBlock<?> deferredBlock) {
        Block cable = deferredBlock.get();
        String name = deferredBlock.getId().getPath();

        ResourceLocation texture = modLoc("block/" + name);

        ModelFile coreModel = models().withExistingParent(name + "_core", modLoc("block/cable_core")).texture("texture", texture);

        ModelFile downArm  = models().withExistingParent(name + "_arm_down",  modLoc("block/cable_arm")).texture("texture", texture);
        ModelFile upArm    = models().withExistingParent(name + "_arm_up",    modLoc("block/cable_arm")).texture("texture", texture);
        ModelFile northArm = models().withExistingParent(name + "_arm_north", modLoc("block/cable_arm")).texture("texture", texture);
        ModelFile southArm = models().withExistingParent(name + "_arm_south", modLoc("block/cable_arm")).texture("texture", texture);
        ModelFile westArm  = models().withExistingParent(name + "_arm_west",  modLoc("block/cable_arm")).texture("texture", texture);
        ModelFile eastArm  = models().withExistingParent(name + "_arm_east",  modLoc("block/cable_arm")).texture("texture", texture);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(cable);

        builder.part().modelFile(coreModel).addModel().end();

        builder.part().modelFile(downArm).addModel().condition(AbstractCableBlock.DOWN, true).end();
        builder.part().modelFile(upArm).rotationX(180).addModel().condition(AbstractCableBlock.UP, true).end();
        builder.part().modelFile(northArm).rotationX(270).addModel().condition(AbstractCableBlock.NORTH, true).end();
        builder.part().modelFile(southArm).rotationX(90).addModel().condition(AbstractCableBlock.SOUTH, true).end();
        builder.part().modelFile(westArm).rotationX(90).rotationY(90).addModel().condition(AbstractCableBlock.WEST, true).end();
        builder.part().modelFile(eastArm).rotationX(90).rotationY(270).addModel().condition(AbstractCableBlock.EAST, true).end();

        itemModels().getBuilder(name).parent(new UncheckedModelFile(modLoc("block/" + name + "_inventory")));
    }
}
