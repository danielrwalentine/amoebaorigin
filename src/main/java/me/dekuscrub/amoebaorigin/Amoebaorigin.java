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
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.wallentines.midnightcore.api.text.MStyle;
import org.wallentines.midnightcore.api.text.MTextComponent;
import org.wallentines.midnightcore.api.text.MTranslateComponent;
import org.wallentines.midnightcore.fabric.player.FabricPlayer;
import org.wallentines.midnightlib.config.FileConfig;
import org.wallentines.midnightlib.math.Color;

import java.io.File;

public class Amoebaorigin implements ModInitializer {

    private static final FileConfig config = FileConfig.findOrCreate("amoeba_origin", new File("config"));
    public static final PowerType<Power> MANIFEST = new PowerTypeReference<>(new Identifier("amoeba_origin", "manifest"));
    public static boolean manifest = false;

    @Override
    public void onInitialize() {
    }

    public static void setConfigValue(String key, String value) {
        config.getRoot().set(key, value);
        config.save();
    }

    public static void resetOrigin(ServerPlayerEntity player) {
        if (MANIFEST.isActive(player)) {
            ModComponents.ORIGIN.get(player).setOrigin(OriginLayers.getLayer(Identifier.tryParse("origins:origin")), OriginRegistry.get(Identifier.tryParse("origins:human")));
            OriginComponent.sync(player);
            manifest = false;
        }
    }

    public static void updateOrigin(ServerPlayerEntity player) {
        if (manifest) {
            if (!player.hasStatusEffect(StatusEffects.GLOWING)) {
                Amoebaorigin.resetOrigin(player);
            }
        }
    }

    public static void bitePlayer(ServerPlayerEntity player, Entity target) {
        OriginLayer layer = OriginLayers.getLayer(Identifier.tryParse("origins:origin"));
        OriginComponent component = ModComponents.ORIGIN.get(player);

        if (player.isPlayer()) {
            if (MANIFEST.isActive(player)) {
                if (player.getHungerManager().getFoodLevel() > 0) {
                    if (player.getInventory().getMainHandStack() == ItemStack.EMPTY) {
                        player.getHungerManager().add(-1, 0);
                        if (Math.random() <= 0.2) {
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 30000));
                            Origin origin;
                            if (target.isPlayer()) {
                                origin = ModComponents.ORIGIN.get(target).getOrigin(layer);
                            } else {
                                origin = OriginRegistry.get(Identifier.tryParse(config.getRoot().getString(Registry.ENTITY_TYPE.getId(target.getType()).toString())));
                            }
                            component.setOrigin(layer, origin);
                            FabricPlayer fp = FabricPlayer.wrap(player);
                            MTranslateComponent name = new MTranslateComponent(origin.getName().getKey());
                            fp.sendActionBar(new MTextComponent("Manifest set to ").withChild(name).withChild(new MTextComponent(", you have 25 minutes")).withStyle(new MStyle().withColor(Color.fromRGBI(6))));

                            OriginComponent.sync(player);
                            manifest = true;
                        }
                    }
                }
            }
        }
    }
}