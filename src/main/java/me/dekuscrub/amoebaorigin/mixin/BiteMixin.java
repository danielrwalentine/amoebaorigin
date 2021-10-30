package me.dekuscrub.amoebaorigin.mixin;

import me.dekuscrub.amoebaorigin.Amoebaorigin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class BiteMixin {

    @Inject(method = "damage", at = @At("RETURN"))
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Amoebaorigin.bitePlayer(source.getSource(), (Entity)(Object)this);
    }
}
