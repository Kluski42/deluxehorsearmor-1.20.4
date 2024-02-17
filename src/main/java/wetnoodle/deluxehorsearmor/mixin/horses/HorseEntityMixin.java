package wetnoodle.deluxehorsearmor.mixin.horses;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseEntity.class)
public abstract class HorseEntityMixin extends LivingEntityMixin {
    @Shadow
    public abstract ItemStack getArmorType();

    @Unique
    private static final EquipmentSlot ARMOR = EquipmentSlot.CHEST;

    @Override
    void damageArmorMixin(DamageSource source, float amount, CallbackInfo ci) {
        damageArmor(source, amount);
    }

    @Unique
    public void damageArmor(DamageSource damageSource, float amount) {
        if (amount <= 0.0f) {
            return;
        }
        if ((amount /= 4.0f) < 1.0f) {
            amount = 1.0f;
        }

        ItemStack itemStack = this.getArmorType();
        if (damageSource.isIn(DamageTypeTags.IS_FIRE) && itemStack.getItem().isFireproof() || !(itemStack.getItem() instanceof HorseArmorItem))
            return;
        itemStack.damage((int) amount, (HorseEntity) (Object) this, horse -> horse.sendEquipmentBreakStatus(ARMOR));

    }
}
