package wetnoodle.deluxehorsearmor.mixin.enchantments;

import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FrostWalkerEnchantment.class)
public class FrostWalkerMixin extends EnchantmentMixin {
    @Override
    public boolean isAcceptable$DeluxeHorseArmor(Item item) {
        return item instanceof HorseArmorItem;
    }
}
