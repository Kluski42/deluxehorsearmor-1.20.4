package wetnoodle.deluxehorsearmor.mixininterfaces;

import net.minecraft.item.Item;

public interface EnchantmentInterface {
    default boolean isAcceptable$DeluxeHorseArmor(Item item) {
        return false;
    }
}
