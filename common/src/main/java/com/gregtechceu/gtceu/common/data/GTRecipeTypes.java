package com.gregtechceu.gtceu.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.recipe.GTRecipeSerializer;
import com.gregtechceu.gtceu.common.recipe.RockBreakerCondition;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.api.sound.ExistingSoundEntry;
import com.gregtechceu.gtceu.common.block.CoilBlock;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.TankWidget;
import com.lowdragmc.lowdraglib.msic.FluidStorage;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.lowdragmc.lowdraglib.utils.CycleItemStackHandler;
import com.lowdragmc.lowdraglib.utils.LocalizationUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.*;

/**
 * @author KilaBash
 * @date 2023/2/20
 * @implNote GTRecipeTypes
 */
public class GTRecipeTypes {

    public final static GTRecipeType STEAM_BOILER_RECIPES = register("steam_boiler").setIOSize(0, 1, 0, 0, 0, 1, 0, 1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BOILER_FUEL.get(true), DOWN_TO_UP)
            .onRecipeBuild((builder, provider) -> {
                var duration = (builder.duration / 12 / 80); // copied for large boiler
                if (duration > 0) {
                    GTRecipeTypes.LARGE_BOILER_RECIPES.copyFrom(builder).duration(duration).save(provider);
                }
            })
            .setSound(GTSoundEntries.FURNACE);

    public final static GTRecipeType FURNACE_RECIPES = register("electric_furnace", RecipeType.SMELTING).setIOSize(1, 1, 1, 1, 0, 0, 0, 0)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(4))
            .setSlotOverlay(false, false, GuiTextures.FURNACE_OVERLAY_1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSteamProgressBar(GuiTextures.PROGRESS_BAR_ARROW_STEAM, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.FURNACE);

    public final static GTRecipeType ALLOY_SMELTER_RECIPES = register("alloy_smelter").setIOSize(1, 2, 1, 1, 0, 0, 0, 0)
            .setSlotOverlay(false, false, GuiTextures.FURNACE_OVERLAY_1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSteamProgressBar(GuiTextures.PROGRESS_BAR_ARROW_STEAM, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.FURNACE);

    public final static GTRecipeType ARC_FURNACE_RECIPES = register("arc_furnace").setIOSize(1, 1, 1, 4, 1, 1, 0, 1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ARC)
            .onRecipeBuild((recipeBuilder, provider) -> {
                if (recipeBuilder.input.getOrDefault(FluidRecipeCapability.CAP, Collections.emptyList()).isEmpty() && recipeBuilder.tickInput.getOrDefault(FluidRecipeCapability.CAP, Collections.emptyList()).isEmpty()) {
                    recipeBuilder.inputFluids(GTMaterials.Oxygen.getFluid(recipeBuilder.duration));
                }
            });

    public final static GTRecipeType ASSEMBLER_RECIPES = register("assembler").setIOSize(1, 9, 1, 1, 0, 1, 0, 0)
            .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_CIRCUIT, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ASSEMBLER);


    public final static GTRecipeType AUTOCLAVE_RECIPES = register("autoclave").setIOSize(1, 2, 1, 2, 1, 1, 0, 1)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.CRYSTAL_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_CRYSTALLIZATION, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.FURNACE);


    public final static GTRecipeType BENDER_RECIPES = register("bender").setIOSize(2, 2, 1, 1, 0, 0, 0, 0)
            .setSlotOverlay(false, false, false, GuiTextures.BENDER_OVERLAY)
            .setSlotOverlay(false, false, true, GuiTextures.INT_CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MOTOR);


    public final static GTRecipeType BREWING_RECIPES = register("brewery").setIOSize(1, 1, 0, 0, 1, 1, 1, 1)
            .prepareBuilder(recipeBuilder -> recipeBuilder.duration(128).EUt(4))
            .setSlotOverlay(false, false, GuiTextures.BREWER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL_REACTOR);

    public final static GTRecipeType MACERATOR_RECIPES = register("macerator").setIOSize(1, 1, 1, 4, 0, 0, 0, 0)
            .setSlotOverlay(false, false, GuiTextures.CRUSHED_ORE_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, LEFT_TO_RIGHT)
            .setSteamProgressBar(GuiTextures.PROGRESS_BAR_MACERATE_STEAM, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MACERATOR);


    public final static GTRecipeType CANNER_RECIPES = register("canner").setIOSize(1, 2, 1, 2, 0, 1, 0, 1)
            .setSlotOverlay(false, false, false, GuiTextures.CANNER_OVERLAY)
            .setSlotOverlay(false, false, true, GuiTextures.CANISTER_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.CANISTER_OVERLAY)
            .setSlotOverlay(false, true, GuiTextures.DARK_CANISTER_OVERLAY)
            .setSlotOverlay(true, true, GuiTextures.DARK_CANISTER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_CANNER, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BATH);


    public final static GTRecipeType CENTRIFUGE_RECIPES = register("centrifuge").setIOSize(0, 2, 0, 6, 0, 1, 0, 6)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(5))
            .setSlotOverlay(false, false, false, GuiTextures.EXTRACTOR_OVERLAY)
            .setSlotOverlay(false, false, true, GuiTextures.CANISTER_OVERLAY)
            .setSlotOverlay(false, true, true, GuiTextures.CENTRIFUGE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);


    public final static GTRecipeType CHEMICAL_BATH_RECIPES = register("chemical_bath").setIOSize(1, 1, 1, 6, 1, 1, 0, 1)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(GTValues.VA[GTValues.LV]))
            .setSlotOverlay(false, false, true, GuiTextures.BREWER_OVERLAY)
            .setSlotOverlay(true, false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, true, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BATH);


    public final static GTRecipeType CHEMICAL_RECIPES = register("chemical_reactor").setIOSize(0, 2, 0, 2, 0, 3, 0, 2)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(GTValues.VA[GTValues.LV]))
            .setSlotOverlay(false, false, false, GuiTextures.MOLECULAR_OVERLAY_1)
            .setSlotOverlay(false, false, true, GuiTextures.MOLECULAR_OVERLAY_2)
            .setSlotOverlay(false, true, false, GuiTextures.MOLECULAR_OVERLAY_3)
            .setSlotOverlay(false, true, true, GuiTextures.MOLECULAR_OVERLAY_4)
            .setSlotOverlay(true, false, GuiTextures.VIAL_OVERLAY_1)
            .setSlotOverlay(true, true, GuiTextures.VIAL_OVERLAY_2)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setSound(GTValues.FOOLS.get() ? GTSoundEntries.SCIENCE : GTSoundEntries.CHEMICAL_REACTOR)
            // TODO consider allowing LCR to just read these recipes? instead of generating new (minimize extra jsons)
            .onRecipeBuild((recipeBuilder, provider) -> GTRecipeTypes.LARGE_CHEMICAL_RECIPES.copyFrom(recipeBuilder).save(provider));

    public final static GTRecipeType COMPRESSOR_RECIPES = register("compressor").setIOSize(1, 1, 1, 1, 0, 0, 0, 0)
            .prepareBuilder(recipeBuilder -> recipeBuilder.duration(200).EUt(2))
            .setSlotOverlay(false, false, GuiTextures.COMPRESSOR_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_COMPRESS, LEFT_TO_RIGHT)
            .setSteamProgressBar(GuiTextures.PROGRESS_BAR_COMPRESS_STEAM, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COMPRESSOR);


    public final static GTRecipeType CUTTER_RECIPES = register("cutter").setIOSize(1, 1, 1, 2, 0, 1, 0, 0)
            .setSlotOverlay(false, false, GuiTextures.SAWBLADE_OVERLAY)
            .setSlotOverlay(true, false, false, GuiTextures.CUTTER_OVERLAY)
            .setSlotOverlay(true, false, true, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SLICE, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CUT)
            .onRecipeBuild((recipeBuilder, provider) -> {
                if (recipeBuilder.input.getOrDefault(FluidRecipeCapability.CAP, Collections.emptyList()).isEmpty() && recipeBuilder.tickInput.getOrDefault(FluidRecipeCapability.CAP, Collections.emptyList()).isEmpty()) {
                    recipeBuilder
                            .copy(new ResourceLocation(recipeBuilder.id.toString() + "_water"))
                            .inputFluids(GTMaterials.Water.getFluid((int) Math.max(4, Math.min(1000, recipeBuilder.duration * recipeBuilder.EUt() / 320))))
                            .duration(recipeBuilder.duration * 2)
                            .save(provider);

                    recipeBuilder
                            .copy(new ResourceLocation(recipeBuilder.id.toString() + "_distlled_water"))
                            .inputFluids(GTMaterials.DistilledWater.getFluid((int) Math.max(3, Math.min(750, recipeBuilder.duration * recipeBuilder.EUt() / 426))))
                            .duration((int) (recipeBuilder.duration * 1.5))
                            .save(provider);

                    // Don't call buildAndRegister as we are mutating the original recipe and already in the middle of a buildAndRegister call.
                    // Adding a second call will result in duplicate recipe generation attempts
                    recipeBuilder
                            .inputFluids(GTMaterials.Lubricant.getFluid((int) Math.max(1, Math.min(250, recipeBuilder.duration * recipeBuilder.EUt() / 1280))))
                            .duration(Math.max(1, recipeBuilder.duration));
                }
            });

    public final static GTRecipeType DISTILLERY_RECIPES = register("distillery").setIOSize(1, 1, 0, 1, 1, 1, 1, 1)
            .setSlotOverlay(false, true, GuiTextures.BEAKER_OVERLAY_1)
            .setSlotOverlay(true, true, GuiTextures.BEAKER_OVERLAY_4)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(false, false, GuiTextures.INT_CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BOILER);


    public final static GTRecipeType ELECTROLYZER_RECIPES = register("electrolyzer").setIOSize(0, 2, 0, 6, 0, 1, 0, 6)
            .setSlotOverlay(false, false, false, GuiTextures.LIGHTNING_OVERLAY_1)
            .setSlotOverlay(false, false, true, GuiTextures.CANISTER_OVERLAY)
            .setSlotOverlay(false, true, true, GuiTextures.LIGHTNING_OVERLAY_2)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ELECTROLYZER);


    public final static GTRecipeType ELECTROMAGNETIC_SEPARATOR_RECIPES = register("electromagnetic_separator").setIOSize(1, 1, 1, 3, 0, 0, 0, 0)
            .setSlotOverlay(false, false, GuiTextures.CRUSHED_ORE_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MAGNET, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ARC);


    public final static GTRecipeType EXTRACTOR_RECIPES = register("extractor").setIOSize(0, 1, 0, 1, 0, 0, 0, 1)
            .prepareBuilder(recipeBuilder -> recipeBuilder.duration(400).EUt(2))
            .setSlotOverlay(false, false, GuiTextures.EXTRACTOR_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, LEFT_TO_RIGHT)
            .setSteamProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT_STEAM, LEFT_TO_RIGHT);

    public final static GTRecipeType EXTRUDER_RECIPES = register("extruder").setIOSize(2, 2, 1, 1, 0, 0, 0, 0)
            .setSlotOverlay(false, false, true, GuiTextures.MOLD_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRUDER, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COMPRESSOR);


    public final static GTRecipeType FERMENTING_RECIPES = register("fermenter").setIOSize(0, 1, 0, 1, 1, 1, 1, 1)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(2))
            .setSlotOverlay(false, false, true, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, true, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL_REACTOR);


    public final static GTRecipeType FLUID_HEATER_RECIPES = register("fluid_heater").setIOSize(1, 1, 0, 0, 1, 1, 1, 1)
            .setSlotOverlay(false, true, GuiTextures.HEATING_OVERLAY_1)
            .setSlotOverlay(true, true, GuiTextures.HEATING_OVERLAY_2)
            .setSlotOverlay(false, false, GuiTextures.INT_CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BOILER);


    public final static GTRecipeType FLUID_SOLIDFICATION_RECIPES = register("fluid_solidifier").setIOSize(1, 1, 1, 1, 1, 1, 0, 0)
            .setSlotOverlay(false, false, GuiTextures.SOLIDIFIER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);


    public final static GTRecipeType FORGE_HAMMER_RECIPES = register("forge_hammer").setIOSize(1, 1, 1, 1, 0, 0, 0, 0)
            .setSlotOverlay(false, false, GuiTextures.HAMMER_OVERLAY)
            .setSpecialTexture(78, 42, 20, 6, GuiTextures.PROGRESS_BAR_HAMMER_BASE)
            .setProgressBar(GuiTextures.PROGRESS_BAR_HAMMER, UP_TO_DOWN)
            .setSteamProgressBar(GuiTextures.PROGRESS_BAR_HAMMER_STEAM, UP_TO_DOWN)
            .setSound(GTSoundEntries.FORGE_HAMMER);


    public final static GTRecipeType FORMING_PRESS_RECIPES = register("forming_press").setIOSize(2, 6, 1, 1, 0, 0, 0, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_COMPRESS, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COMPRESSOR);


    public final static GTRecipeType LATHE_RECIPES = register("lathe").setIOSize(1, 1, 1, 2, 0, 0, 0, 0)
            .setSlotOverlay(false, false, GuiTextures.PIPE_OVERLAY_1)
            .setSlotOverlay(true, false, false, GuiTextures.PIPE_OVERLAY_2)
            .setSlotOverlay(true, false, true, GuiTextures.DUST_OVERLAY)
            .setSpecialTexture(98, 24, 5, 18, GuiTextures.PROGRESS_BAR_LATHE_BASE)
            .setProgressBar(GuiTextures.PROGRESS_BAR_LATHE, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CUT);


    public final static GTRecipeType MIXER_RECIPES = register("mixer").setIOSize(0, 6, 0, 1, 0, 2, 0, 1)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MIXER);


    public final static GTRecipeType ORE_WASHER_RECIPES = register("ore_washer").setIOSize(1, 2, 1, 3, 0, 1, 0, 0)
            .prepareBuilder(recipeBuilder -> recipeBuilder.duration(400).EUt(16))
            .setSlotOverlay(false, false, GuiTextures.CRUSHED_ORE_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BATH);


    public final static GTRecipeType PACKER_RECIPES = register("packer").setIOSize(1, 2, 1, 2, 0, 0, 0, 0)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(12).duration(10))
            .setSlotOverlay(false, false, true, GuiTextures.BOX_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.BOXED_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_UNPACKER, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ASSEMBLER);


    public final static GTRecipeType POLARIZER_RECIPES = register("polarizer").setIOSize(1, 1, 1, 1, 0, 0, 0, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MAGNET, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ARC);


    public final static GTRecipeType LASER_ENGRAVER_RECIPES = register("laser_engraver").setIOSize(2, 2, 1, 1, 0, 0, 0, 0)
            .setSlotOverlay(false, false, true, GuiTextures.LENS_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ELECTROLYZER);


    public final static GTRecipeType SIFTER_RECIPES = register("sifter").setIOSize(1, 1, 0, 6, 0, 0, 0, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, UP_TO_DOWN)
            .setSound(new ExistingSoundEntry(SoundEvents.SAND_PLACE, SoundSource.BLOCKS));


    public final static GTRecipeType THERMAL_CENTRIFUGE_RECIPES = register("thermal_centrifuge").setIOSize(1, 1, 1, 3, 0, 0, 0, 0)
            .prepareBuilder(recipeBuilder -> recipeBuilder.duration(400).EUt(30))
            .setSlotOverlay(false, false, GuiTextures.CRUSHED_ORE_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);


    public final static GTRecipeType WIREMILL_RECIPES = register("wiremill").setIOSize(1, 1, 1, 1, 0, 0, 0, 0)
            .setSlotOverlay(false, false, GuiTextures.WIREMILL_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_WIREMILL, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MOTOR);

    public final static GTRecipeType CIRCUIT_ASSEMBLER_RECIPES = register("circuit_assembler").setIOSize(1, 6, 1, 1, 0, 1, 0, 0)
            .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ASSEMBLER)
            .onRecipeBuild((recipeBuilder, provider) -> {
                if (recipeBuilder.input.getOrDefault(FluidRecipeCapability.CAP, Collections.emptyList()).isEmpty() && recipeBuilder.tickInput.getOrDefault(FluidRecipeCapability.CAP, Collections.emptyList()).isEmpty()) {
                    recipeBuilder.copy(new ResourceLocation(recipeBuilder.id.toString() +  "_soldering_allory"))
                            .inputFluids(GTMaterials.SolderingAlloy.getFluid(Math.max(1, (GTValues.L / 2) * recipeBuilder.getSolderMultiplier())))
                            .save(provider);

                    // Don't call buildAndRegister as we are mutating the original recipe and already in the middle of a buildAndRegister call.
                    // Adding a second call will result in duplicate recipe generation attempts
                    recipeBuilder.inputFluids(GTMaterials.Tin.getFluid(Math.max(1, GTValues.L * recipeBuilder.getSolderMultiplier())));
                }
            });

    public final static GTRecipeType GAS_COLLECTOR_RECIPES = register("gas_collector").setIOSize(1, 1, 0, 0, 0, 0, 1, 1)
            .setSlotOverlay(false, false, GuiTextures.INT_CIRCUIT_OVERLAY)
            .setSlotOverlay(true, true, GuiTextures.CENTRIFUGE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);

    public final static GTRecipeType ROCK_BREAKER_RECIPES = register("rock_breaker").setIOSize(1, 1, 1, 4, 0, 0, 0, 0)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.CRUSHED_ORE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, LEFT_TO_RIGHT)
            .setSteamProgressBar(GuiTextures.PROGRESS_BAR_MACERATE_STEAM, LEFT_TO_RIGHT)
            .prepareBuilder(recipeBuilder -> recipeBuilder.addCondition(RockBreakerCondition.INSTANCE))
            .setUiBuilder((recipe, widgetGroup) -> {
                var fluidA = Registry.FLUID.get(new ResourceLocation(recipe.data.getString("fluidA")));
                var fluidB = Registry.FLUID.get(new ResourceLocation(recipe.data.getString("fluidB")));
                if (fluidA != Fluids.EMPTY) {
                    widgetGroup.addWidget(new TankWidget(new FluidStorage(FluidStack.create(fluidA, 1000)), widgetGroup.getSize().width - 30, widgetGroup.getSize().height - 30, false, false)
                            .setBackground(GuiTextures.FLUID_SLOT).setShowAmount(false));
                }
                if (fluidB != Fluids.EMPTY) {
                    widgetGroup.addWidget(new TankWidget(new FluidStorage(FluidStack.create(fluidB, 1000)), widgetGroup.getSize().width - 30 - 20, widgetGroup.getSize().height - 30, false, false)
                            .setBackground(GuiTextures.FLUID_SLOT).setShowAmount(false));
                }
            })
            .addDataInfo(tag -> "Place fluids horizontally adjacent")
            .setSound(GTSoundEntries.FIRE);

    //////////////////////////////////////
    //*******     Generator      *******//
    //////////////////////////////////////
    public final static GTRecipeType COMBUSTION_GENERATOR_FUELS = register("combustion_generator").setIOSize(0, 0, 0, 0, 1, 1, 0, 0)
            .setSlotOverlay(false, true, true, GuiTextures.FURNACE_OVERLAY_2)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COMBUSTION);

    public final static GTRecipeType GAS_TURBINE_FUELS = register("gas_turbine").setIOSize(0, 0, 0, 0, 1, 1, 0, 0)
            .setSlotOverlay(false, true, true, GuiTextures.DARK_CANISTER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.TURBINE);

    public final static GTRecipeType STEAM_TURBINE_FUELS = register("steam_turbine").setIOSize(0, 0, 0, 0, 1, 1, 0, 1)
            .setSlotOverlay(false, true, true, GuiTextures.CENTRIFUGE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.TURBINE);

    public final static GTRecipeType SEMI_FLUID_GENERATOR_FUELS = register("semi_fluid_generator").setIOSize(0, 0, 0, 0, 1, 1, 0, 0)
            .setSlotOverlay(false, true, true, GuiTextures.FURNACE_OVERLAY_2)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COMBUSTION);

    public final static GTRecipeType PLASMA_GENERATOR_FUELS = register("plasma_generator").setIOSize(0, 0, 0, 0, 1, 1, 0, 1)
            .setSlotOverlay(false, true, true, GuiTextures.CENTRIFUGE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.TURBINE);

    //////////////////////////////////////
    //*******     Multiblock     *******//
    //////////////////////////////////////
    public final static GTRecipeType LARGE_BOILER_RECIPES = register("large_boiler").setIOSize(0, 1, 0, 0, 0, 1, 0, 1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BOILER_FUEL.get(true), DOWN_TO_UP)
            .setSound(GTSoundEntries.FURNACE);

    public final static GTRecipeType COKE_OVEN_RECIPES = register("coke_oven").setIOSize(1, 1, 0, 1, 0, 0, 0, 1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.FIRE);

    public final static GTRecipeType PRIMITIVE_BLAST_FURNACE_RECIPES = register("primitive_blast_furnace").setIOSize(2, 3, 0, 3, 0, 0, 0, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.FIRE);

    public final static GTRecipeType BLAST_RECIPES = register("electric_blast_furnace").setIOSize(1, 3, 0, 3, 0, 1, 0, 1)
            .addDataInfo(data -> LocalizationUtils.format("gtceu.recipe.temperature", data.getInt("ebf_temp")))
            .setUiBuilder((recipe, widgetGroup) -> {
                int temp = recipe.data.getInt("ebf_temp");
                List<List<ItemStack>> items = new ArrayList<>();
                items.add(Arrays.stream(CoilBlock.CoilType.values()).filter(coil -> coil.getCoilTemperature() >= temp).map(coil -> GTBlocks.ALL_COILS.get(coil).asStack()).toList());
                widgetGroup.addWidget(new SlotWidget(new CycleItemStackHandler(items), 0, widgetGroup.getSize().width - 25, widgetGroup.getSize().height - 25, false, false));
            })
            .setSound(GTSoundEntries.FURNACE);

    // TODO Add small distillery recipes in .onRecipeBuild()
    public final static GTRecipeType DISTILLATION_RECIPES = register("distillation_tower").setIOSize(0, 0, 0, 1, 1, 1, 1, 12)
            .setSound(GTSoundEntries.CHEMICAL_REACTOR);

    public final static GTRecipeType PYROLYSE_RECIPES = register("pyrolyse_oven").setIOSize(2, 2, 0, 1, 0, 1, 0, 1)
            .setSound(GTSoundEntries.FIRE);

    public final static GTRecipeType CRACKING_RECIPES = register("cracker").setIOSize(0, 1, 0, 0, 2, 2, 0, 2)
            .setSlotOverlay(false, true, GuiTextures.CRACKING_OVERLAY_1)
            .setSlotOverlay(true, true, GuiTextures.CRACKING_OVERLAY_2)
            .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_CRACKING, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.FIRE);

    public final static GTRecipeType IMPLOSION_RECIPES = register("implosion_compressor").setIOSize(2, 3, 0, 2, 0, 0, 0, 0)
            .prepareBuilder(recipeBuilder -> recipeBuilder.duration(20).EUt(GTValues.VA[GTValues.LV]))
            .setSlotOverlay(false, false, true, GuiTextures.IMPLOSION_OVERLAY_1)
            .setSlotOverlay(false, false, false, GuiTextures.IMPLOSION_OVERLAY_2)
            .setSlotOverlay(true, false, true, GuiTextures.DUST_OVERLAY)
            .setSound(new ExistingSoundEntry(SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS));

    public final static GTRecipeType VACUUM_RECIPES = register("vacuum_freezer").setIOSize(0, 1, 0, 1, 0, 2, 0, 1)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(GTValues.VA[GTValues.MV]))
            .setSound(GTSoundEntries.COOLING);

    public final static GTRecipeType ASSEMBLY_LINE_RECIPES = register("assembly_line").setIOSize(4, 16, 1, 1, 0, 4, 0, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ASSEMBLER);

    public static final GTRecipeType LARGE_CHEMICAL_RECIPES = register("large_chemical_reactor").setIOSize(0, 3, 0, 3, 0, 5, 0, 4)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(GTValues.VA[GTValues.LV]))
            .setSlotOverlay(false, false, false, GuiTextures.MOLECULAR_OVERLAY_1)
            .setSlotOverlay(false, false, true, GuiTextures.MOLECULAR_OVERLAY_2)
            .setSlotOverlay(false, true, false, GuiTextures.MOLECULAR_OVERLAY_3)
            .setSlotOverlay(false, true, true, GuiTextures.MOLECULAR_OVERLAY_4)
            .setSlotOverlay(true, false, GuiTextures.VIAL_OVERLAY_1)
            .setSlotOverlay(true, true, GuiTextures.VIAL_OVERLAY_2)
            .setSound(GTValues.FOOLS.get() ? GTSoundEntries.SCIENCE : GTSoundEntries.CHEMICAL_REACTOR)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setSmallRecipeMap(CHEMICAL_RECIPES);












    public static GTRecipeType register(String name, RecipeType<?>... proxyRecipes) {
        var recipeType = new GTRecipeType(GTCEu.id(name), proxyRecipes);
        GTRegistries.register(Registry.RECIPE_TYPE, recipeType.registryName, recipeType);
        GTRegistries.RECIPE_TYPES.register(recipeType.registryName, recipeType);
        return recipeType;
    }

    public static void init() {
        GTRegistries.register(Registry.RECIPE_SERIALIZER, GTCEu.id("gt_recipe_serializer"), GTRecipeSerializer.SERIALIZER);
    }

}