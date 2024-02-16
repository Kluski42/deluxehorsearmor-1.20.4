package wetnoodle.deluxehorsearmor.mixin;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wetnoodle.deluxehorsearmor.DeluxeHorseArmor;
import wetnoodle.deluxehorsearmor.mixininterfaces.HorseArmorItemInterface;

@Mixin(HorseArmorItem.class)
public abstract class HorseArmorItemMixin extends Item implements HorseArmorItemInterface {
    public HorseArmorItemMixin(Settings settings) {
        super(settings);
    }

    @Unique
    protected ArmorMaterial material;
    @Unique
    private float toughness;
    @Unique
    protected float knockbackResistance;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void initMixin(int bonus, String name, Settings settings, CallbackInfo ci) {
        DeluxeHorseArmor.LOGGER.info(name);
        material = getMaterial(name);
//        if (material == null) {
//            DeluxeHorseArmor.LOGGER.warn("Horse armor material type not found!");
//        }
        this.toughness = material.getToughness();
        this.knockbackResistance = material.getKnockbackResistance();
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier("Armor toughness", this.toughness, EntityAttributeModifier.Operation.ADDITION));
        if (material == ArmorMaterials.NETHERITE) {
            builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier("Armor knockback resistance", this.knockbackResistance, EntityAttributeModifier.Operation.ADDITION));
        }
    }

    @ModifyVariable(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;<init>(Lnet/minecraft/item/Item$Settings;)V"), argsOnly = true)
    private static Settings initModifyArg(Settings original, int bonus, String name, Item.Settings settings) {
        return original.maxDamageIfAbsent(getMaterial(name).getDurability(ArmorItem.Type.CHESTPLATE));
    }

    @Override
    public int getEnchantability() {
        return this.material.getEnchantability();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return this.material.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }

    @Unique
    public float getToughness() {
        return this.toughness;
    }

    @Unique
    private static ArmorMaterial getMaterial(String name) {
        return ArmorMaterials.valueOf(name);
    }
}
