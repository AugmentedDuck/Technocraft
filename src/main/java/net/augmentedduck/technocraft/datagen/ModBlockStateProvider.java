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

        generatorBlockState();
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void generatorBlockState() {
        Block generator = ModBlocks.GENERATOR_BLOCK.get();
        BlockModelBuilder offModel = models().orientableWithBottom("generator", modLoc("block/generator_side"), modLoc("block/generator_front_unlit"), modLoc("block/generator_bottom"), modLoc("block/generator_top"));
        BlockModelBuilder onModel = models().orientableWithBottom("generator", modLoc("block/generator_side"), modLoc("block/generator_front_lit"), modLoc("block/generator_bottom"), modLoc("block/generator_top"));

        getVariantBuilder(generator).forAllStates(state -> {
            Direction facing = state.getValue(GeneratorBlock.FACING);
            boolean lit = state.getValue(GeneratorBlock.LIT);
            
            return ConfiguredModel.builder().modelFile(lit ? onModel : offModel).rotationY((int) facing.toYRot()).build();
        });

        itemModels().withExistingParent("generator", modLoc("block/generator"));
    }
}
