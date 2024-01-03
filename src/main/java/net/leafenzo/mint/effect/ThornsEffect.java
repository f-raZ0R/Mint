// SOURCES:
// marcus8448 - https://github.com/marcus8448/MotherlodeCore/blob/5721f584c4bb6776a052b8bc11df93fffd376c6f/motherlode-potions/src/main/java/motherlode/potions/ThornsEffect.java#L9

package net.leafenzo.mint.effect;

import net.leafenzo.mint.util.ModUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;

public class ThornsEffect extends StatusEffect {
    protected ThornsEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public static void apply(Entity user, LivingEntity attacker) {
        Random random = attacker.getRandom();
        int amplifier = ((LivingEntity) user).getStatusEffect(ModEffects.THORNS).getAmplifier();
        int damage;

        if(!shouldDamageAttacker(amplifier, random)) { return; }

        if (amplifier == 0) { damage = 0; }
        else if (amplifier == 1) { damage = random.nextBetween(1, 5); }
        else if (amplifier == 2) { damage = random.nextBetween(1, 8); }
        else { damage = random.nextBetween(1, 5 + ((amplifier-1)*3)); }

        attacker.damage(attacker.getDamageSources().thorns(user), damage);
    }

    private static boolean shouldDamageAttacker(int amplifier, Random random) {
        if (amplifier == 0)      { return true; }
        else if (amplifier == 1) { return random.nextFloat() < 0.5f; }
        else if (amplifier == 2) { return random.nextFloat() < 0.8f; }
        return true;
    }
}