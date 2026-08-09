package net.augmentedduck.technocraft.worldgen;

import net.augmentedduck.technocraft.Technocraft;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE = registerKey("add_tin_ore");
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE_LOWER = registerKey("add_tin_ore_lower");
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE_EXTRA = registerKey("add_tin_ore_extra");

    public static final ResourceKey<BiomeModifier> ADD_LEAD_ORE_UPPER = registerKey("add_lead_ore_upper");
    public static final ResourceKey<BiomeModifier> ADD_LEAD_ORE_MIDDLE = registerKey("add_lead_ore_middle");
    public static final ResourceKey<BiomeModifier> ADD_LEAD_ORE_SMALL = registerKey("add_lead_ore_small");

    public static final ResourceKey<BiomeModifier> ADD_SILVER_ORE = registerKey("add_silver_ore");
    public static final ResourceKey<BiomeModifier> ADD_SILVER_ORE_LOWER = registerKey("add_silver_ore_lower");


    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(ADD_TIN_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_PLACED_KEY)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_TIN_ORE_LOWER, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_PLACED_LOWER_KEY)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_TIN_ORE_EXTRA, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_PLACED_EXTRA_KEY)), GenerationStep.Decoration.UNDERGROUND_ORES));
     
        context.register(ADD_LEAD_ORE_UPPER, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_LEAD_UPPER)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_LEAD_ORE_MIDDLE, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_LEAD_MIDDLE)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_LEAD_ORE_SMALL, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_LEAD_SMALL)), GenerationStep.Decoration.UNDERGROUND_ORES));
    
        context.register(ADD_SILVER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_SILVER)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_SILVER_ORE_LOWER, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_SILVER_LOWER)), GenerationStep.Decoration.UNDERGROUND_ORES));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Technocraft.MODID, name));
    }
}
