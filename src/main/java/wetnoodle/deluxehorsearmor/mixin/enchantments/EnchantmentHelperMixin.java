package wetnoodle.deluxehorsearmor.mixin.enchantments;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import wetnoodle.deluxehorsearmor.mixininterfaces.EnchantmentInterface;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @ModifyExpressionValue(method = "getPossibleEntries", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentTarget;isAcceptableItem(Lnet/minecraft/item/Item;)Z"))
    private static boolean isAcceptableItemMixin(boolean original, @Local Enchantment enchantment, @Local Item item) {
        if (((EnchantmentInterface) enchantment).isAcceptable$DeluxeHorseArmor(item))
            return true;
        return original;
    }
}
