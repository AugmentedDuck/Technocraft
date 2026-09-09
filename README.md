# Technocraft
 
An [IndustrialCraft 2](https://wiki.industrial-craft.net/)–inspired tech mod for **Minecraft 1.21.1** (NeoForge), built around ore processing chains, tiered FE energy, and electric machines.
 
> Mod ID: `technocraft` · Package: `net.augmentedduck.technocraft`
 
---

## Requirements
 
- Minecraft **1.21.1**
- **NeoForge** (matching 1.21.1 build)
- [JEI](https://www.curseforge.com/minecraft/mc-mods/jei) *(optional, for recipe browsing)*

## Overview
 
Technocraft brings the classic IC2 progression to modern NeoForge: mine raw ore, macerate it into crushed ore for double yields, wash and process it down into dust, then smelt it into ingots. Power everything with a tiered [Forge Energy](https://docs.neoforged.net/docs/energy/) network of generators, cables, and machines.
 
## Features
 
- **Multi-stage ore processing** — raw ore → crushed → (washed) → dust → ingot.
- **Tiered FE energy system** — LV / MV / HV / EV / IV, each roughly 4× the transfer rate of the last, with matching cable tiers.
- **Electric machines** — Generator, Electric Furnace, Macerator, Compressor, Extractor, Extruder, and Roller.
- **Power generation** — a fuel-burning Generator, a day/sky-exposure Solar Panel, (and a fuel-cell RTG).
- **Cable networks** — insulated and bare cable variants.
- **Rechargeable Battery** — a portable, stackable-when-empty-or-full FE storage item.
- **Power Meter** — a handheld tool that reports cable throughput or a block's stored/max energy
- **JEI integration** — every custom machine recipe (Macerating, Compressing, Extracting, Extruding, Rolling) is browsable in-game.
- **New ores & metals** — Tin, Silver, and Lead ore generation, plus Bronze and Steel alloys, each following the full processing chain.

## Machines
 
| Machine | Role | Input → Output |
|---|---|---|
| **Generator** | Power source | Burns vanilla fuel items into FE, can charge held items or push power outward |
| **Solar Panel** | Power source | Generates FE while exposed to open sky during the day |
| **RTG** (WIP) | Power source | Passively generates FE from RTG Fuel Pellets, no light/fuel-burn required |
| **Electric Furnace** | Consumer | Runs vanilla smelting recipes on FE instead of fuel |
| **Macerator** | Consumer | Grinds ores/items into crushed forms or dust (typically 2× yield over a furnace) |
| **Compressor** | Consumer | Compresses multiple items into a denser result (e.g. 9 ingots → 1 block) |
| **Extractor** | Consumer | Extracts sub-components from an item (e.g. bricks → brick, wool → string) |
| **Extruder** | Consumer | Draws ingots into cable and other extruded shapes |
| **Press** | Consumer | Presses ingots into plates and plates into item casings |
 
Every consumer machine shares a common battery-charge slot, an input/output slot pair, and an energy bar.
 
## Energy System
 
Technocraft uses IC2-style voltage tiers layered on top of Forge Energy (FE):
 
| Tier | Max Transfer (FE/t) |
|---|---|
| LV | 320 |
| MV | 1,280 |
| HV | 5,120 |
| EV | 20,480 |
| IV | 81,920 |
 
- **Cables** relay energy instantly across a connected network, splitting the offer fairly across every reachable receiver and throttling to the lowest-tier cable segment in the path.
- **Generators** (Generator, Solar Panel, RTG) push surplus energy to adjacent capability-exposing blocks each tick, splitting evenly among all neighbors.
- **Consumer machines** hold their own internal FE buffer, charged either from an inserted Rechargeable Battery or from an adjacent cable/generator, and spend energy per-tick while processing.

## Ore Processing Chain
 
Following IC2 convention, most metals can go through the full chain for maximum yield:
 
```
Raw Ore ──[Macerator]──> Crushed ──[Extractor/Wash]──> Washed ──[Smelt/Blast]──> Ingot
                                                                        │
                                                          [Macerator] ──┴──> Dust ──[Smelt]──> Ingot
```
 
Ingots can then be:
- **Compressed** (9 → 1) into storage Blocks
- **Rolled** into Plates, and Plates into Item Casings
- **Extruded** into Cable
  
## Building from Source
 
```bash
git clone https://github.com/<your-username>/technocraft.git
cd technocraft
./gradlew build
```
 
The built jar will be located in `build/libs/`.
 
To run a development client:
 
```bash
./gradlew runClient
```
 
## Credits & Inspiration
 
Technocraft is a fan-made tribute to **[IndustrialCraft 2](https://wiki.industrial-craft.net/)**, reimagining its ore processing and energy progression for modern NeoForge. Not affiliated with the original IC2 team.
