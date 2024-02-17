package wetnoodle.deluxehorsearmor.mixin.enchantments;

import net.minecraft.enchantment.SoulSpeedEnchantment;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SoulSpeedEnchantment.class)
public abstract class SoulSpeedEnchantmentMixin extends EnchantmentMixin {
    @Override
    public boolean isAcceptable$DeluxeHorseArmor(Item item) {
        return item instanceof HorseArmorItem;
    }
}
