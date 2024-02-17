package wetnoodle.deluxehorsearmor.mixin.enchantments;

import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ProtectionEnchantment.class)
public class ProtectionEnchantmentMixin extends EnchantmentMixin {
    @Override
    public boolean isAcceptable$DeluxeHorseArmor(Item item) {
        return item instanceof HorseArmorItem;
    }
}
