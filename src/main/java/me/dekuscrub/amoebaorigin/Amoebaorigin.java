package me.dekuscrub.amoebaorigin;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.apace100.origins.component.OriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import io.github.apace100.origins.origin.OriginRegistry;
import io.github.apace100.origins.registry.ModComponents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class Amoebaorigin implements ModInitializer {

    public static final PowerType<Power> MANIFEST = new PowerTypeReference<Power>(new Identifier("amoeba_origin", "manifest"));

    @Override
    public void onInitialize() {
    }

    public static void bitePlayer(ServerPlayerEntity player, Entity target) {
        OriginLayer layer = OriginLayers.getLayer(Identifier.tryParse("origins:origin"));
        OriginComponent component = ModComponents.ORIGIN.get(player);

        if (player.isPlayer()) {
            if (MANIFEST.isActive(player)) {
                if (player.getHungerManager().isNotFull()) {
                    if (player.getInventory().getMainHandStack() == ItemStack.EMPTY) {
                        player.getHungerManager().add(1, 0);
                        if (Math.random() <= 0.2) {
                            if (target.isPlayer()) {
                                Origin origin = ModComponents.ORIGIN.get(target).getOrigin(layer);
                                component.setOrigin(layer, origin);
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Phantom")) {
                                if (Math.random() <= 0.5) {
                                    component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("origins:phantom")));
                                    OriginComponent.sync(player);
                                } else {
                                    component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("origins:elytrian")));
                                    OriginComponent.sync(player);
                                }
                            } else if (target.toString().contains("Spider")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("origins:arachnid")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Blaze")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("origins:blazeborn")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Shulker")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("origins:shulk")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Enderman")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("origins:enderian")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Chicken")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("origins:avian")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Cat")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("origins:feline")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Ocelot")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("origins:feline")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Dolphin")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("origins:merling")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Turtle")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("origins:merling")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("villager")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("origins:human")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Bat")) {
                                if (Math.random() <= 0.1) {
                                    component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("origins:elytrian")));
                                    OriginComponent.sync(player);
                                }
                            } else if (target.toString().contains("Pig")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("amoeba_origin:pig")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Cow")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("amoeba_origin:cow")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("MooShroom")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("amoeba_origin:mooshroom")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Creeper")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("amoeba_origin:creeper")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Wither_Skeleton")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("amoeba_origin:wither_skeleton")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Skeleton")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("amoeba_origin:skeleton")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Zombie")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("amoeba_origin:zombie")));
                                OriginComponent.sync(player);
                            } else if (target.toString().contains("Ghast")) {
                                component.setOrigin(layer, OriginRegistry.get(Identifier.tryParse("amoeba_origin:ghast")));
                                OriginComponent.sync(player);
                            }
                        }
                    }
                }
            }
        }
    }
}