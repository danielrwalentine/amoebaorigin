package me.dekuscrub.amoebaorigin;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class Amoebaorigin implements ModInitializer {

    @Override
    public void onInitialize() {
    }

    public static void bitePlayer(LivingEntity player, Entity target) {
        if (player.isPlayer()) {
            player.kill();
            target.kill();

        }
    }
}
