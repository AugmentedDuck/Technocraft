package net.augmentedduck.technocraft.worldgen;

import java.util.List;

import net.augmentedduck.technocraft.Technocraft;
import net.augmentedduck.technocraft.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE_KEY = registerKey("tin_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE_BURRIED_KEY = registerKey("tin_ore_burried");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SILVER_ORE_KEY = registerKey("silver_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_LEAD_ORE_KEY = registerKey("lead_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_LEAD_ORE_SMALL_KEY = registerKey("lead_ore_small");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldTinOres = List.of(
            OreConfiguration.target(stoneReplaceable, ModBlocks.TIN_ORE.get().defaultBlockState()),
            OreConfiguration.target(deepslateReplaceable, ModBlocks.TIN_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldSilverOres = List.of(
            OreConfiguration.target(stoneReplaceable, ModBlocks.SILVER_ORE.get().defaultBlockState()),
            OreConfiguration.target(deepslateReplaceable, ModBlocks.SILVER_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldLeadOres = List.of(
            OreConfiguration.target(stoneReplaceable, ModBlocks.LEAD_ORE.get().defaultBlockState()),
            OreConfiguration.target(deepslateReplaceable, ModBlocks.LEAD_DEEPSLATE_ORE.get().defaultBlockState())
        );

        register(context, OVERWORLD_TIN_ORE_KEY, Feature.ORE, new OreConfiguration(overworldTinOres, 7));
        register(context, OVERWORLD_TIN_ORE_BURRIED_KEY, Feature.ORE, new OreConfiguration(overworldTinOres, 7, 0.5F));

        register(context, OVERWORLD_LEAD_ORE_KEY, Feature.ORE, new OreConfiguration(overworldLeadOres, 7));
        register(context, OVERWORLD_LEAD_ORE_SMALL_KEY, Feature.ORE, new OreConfiguration(overworldLeadOres, 2));
        
        register(context, OVERWORLD_SILVER_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSilverOres, 6));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configureation) {
        context.register(key, new ConfiguredFeature<>(feature, configureation));
    }
}
