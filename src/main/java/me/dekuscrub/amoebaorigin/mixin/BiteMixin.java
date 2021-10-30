package me.dekuscrub.amoebaorigin.mixin;

import me.dekuscrub.amoebaorigin.Amoebaorigin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(LivingEntity.class)
public class BiteMixin {

    @Inject(method = "onAttacking", at = @At("HEAD"))
    private void onAttacking(Entity target, CallbackInfo ci) {
        Amoebaorigin.bitePlayer(Objects.requireNonNull((LivingEntity)(Object)this), target);
    }
}
