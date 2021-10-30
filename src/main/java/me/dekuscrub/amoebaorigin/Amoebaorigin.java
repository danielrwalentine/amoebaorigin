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
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class Amoebaorigin implements ModInitializer {

    public static final PowerType<Power> BITE = new PowerTypeReference<Power>(new Identifier("amoeba_origin", "bite"));

    @Override
    public void onInitialize() {
    }

    public static void bitePlayer(ServerPlayerEntity player, Entity target) {
        if (player.isPlayer()) {
            if (BITE.isActive(player)) {
                if (target.isPlayer()) {
                    OriginLayer layer = OriginLayers.getLayer(Identifier.tryParse("origins:origin"));
                    Origin origin = ModComponents.ORIGIN.get(target).getOrigin(layer);
                    origin.add(BITE);
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
