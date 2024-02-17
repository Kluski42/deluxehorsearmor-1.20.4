package wetnoodle.deluxehorsearmor.mixin.horses;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract void damageArmor(DamageSource source, float amount);

    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot var1);

    @Inject(method = "damageArmor", at = @At("HEAD"))
    void damageArmorMixin(DamageSource source, float amount, CallbackInfo ci) {

    }

    @ModifyExpressionValue(method = "addSoulSpeedBoostIfNeeded", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"))
    ItemStack soulSpeedBoostInject(ItemStack original) {
        if (((LivingEntity) (Object) this) instanceof AbstractHorseEntity)
            return getEquippedStack(EquipmentSlot.CHEST);
        return original;
    }

    @ModifyArg(method = "method_26084", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;sendEquipmentBreakStatus(Lnet/minecraft/entity/EquipmentSlot;)V"))
    private static EquipmentSlot soulSpeedDamageTarget(EquipmentSlot original, @Local(argsOnly = true) LivingEntity livingEntity) {
        if (livingEntity instanceof AbstractHorseEntity)
            return EquipmentSlot.CHEST;
        return original;
    }
}
