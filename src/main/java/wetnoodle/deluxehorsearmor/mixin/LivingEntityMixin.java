package wetnoodle.deluxehorsearmor.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract void damageArmor(DamageSource source, float amount);

    @Inject(method = "damageArmor", at = @At("HEAD"))
    void damageArmorMixin(DamageSource source, float amount, CallbackInfo ci) {

    }
}
