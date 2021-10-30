package me.dekuscrub.amoebaorigin;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.apace100.origins.component.OriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import io.github.apace100.origins.registry.ModComponents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class Amoebaorigin implements ModInitializer {

    public static final PowerType<Power> MANIFEST = new PowerTypeReference<Power>(new Identifier("amoeba_origin", "manifest"));

    @Override
    public void onInitialize() {
    }

    public static void bitePlayer(ServerPlayerEntity player, Entity target) {
        if (Math.random() <= 0.2) {
            if (player.isPlayer()) {
                if (MANIFEST.isActive(player)) {
                    if (target.isPlayer()) {
                        OriginLayer layer = OriginLayers.getLayer(Identifier.tryParse("origins:origin"));
                        Origin origin = ModComponents.ORIGIN.get(target).getOrigin(layer);
                        OriginComponent component = ModComponents.ORIGIN.get(player);
                        component.setOrigin(layer, origin);
                        OriginComponent.sync(player);
                    } else {
                        target.kill();
                    }
                }
            }
        }
    }
}
