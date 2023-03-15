package com.mikitellurium.potionsreglint.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.PotionUtil;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PotionItem.class)
public abstract class PotionItemMixin extends Item {

    public PotionItemMixin(Settings settings) {
        super(settings);
    }

    // Override the hasGlint method of Item class in the PotionItem class
    @Override
    public boolean hasGlint(ItemStack stack) {
        return super.hasGlint(stack) || !PotionUtil.getPotionEffects(stack).isEmpty();
    }

}
