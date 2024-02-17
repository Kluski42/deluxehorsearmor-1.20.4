package wetnoodle.deluxehorsearmor.mixin.enchantments;

import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ThornsEnchantment.class)
public class ThornsEnchantmentMixin extends EnchantmentMixin {
    @Override
    public boolean isAcceptable$DeluxeHorseArmor(Item item) {
        return item instanceof HorseArmorItem;
    }
}
