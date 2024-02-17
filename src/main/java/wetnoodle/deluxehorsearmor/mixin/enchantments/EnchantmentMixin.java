package wetnoodle.deluxehorsearmor.mixin.enchantments;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import wetnoodle.deluxehorsearmor.mixininterfaces.EnchantmentInterface;

import java.util.Map;

@Mixin(Enchantment.class)
public class EnchantmentMixin implements EnchantmentInterface {
    @Override
    public boolean isAcceptable$DeluxeHorseArmor(Item item) {
        return false;
    }

    @ModifyExpressionValue(method = "isAcceptableItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentTarget;isAcceptableItem(Lnet/minecraft/item/Item;)Z"))
    boolean isAcceptableItemMixin(boolean original, ItemStack stack) {
        if (this.isAcceptable$DeluxeHorseArmor(stack.getItem())) {
            return true;
        }
        return original;
    }

    @ModifyReturnValue(method = "getEquipment", at = @At("RETURN"))
    Map<EquipmentSlot, ItemStack> getEquipmentMixin(Map<EquipmentSlot, ItemStack> original, @Local(argsOnly = true) LivingEntity entity) {
        if (!(entity instanceof AbstractHorseEntity horseEntity) ||
                !horseEntity.hasArmorSlot() || original.containsKey(EquipmentSlot.CHEST)) {
            return original;
        }
        ItemStack itemStack = entity.getEquippedStack(EquipmentSlot.CHEST);
        if (!itemStack.isEmpty())
            original.put(EquipmentSlot.CHEST, itemStack);
        return original;
    }
}
