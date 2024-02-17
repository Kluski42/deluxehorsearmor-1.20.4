package wetnoodle.deluxehorsearmor.mixin.enchantments;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import wetnoodle.deluxehorsearmor.mixininterfaces.EnchantmentInterface;

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
}
