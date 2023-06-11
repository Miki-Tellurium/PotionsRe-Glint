package com.mikitellurium.potionsreglint.mixin;

import com.mikitellurium.potionsreglint.config.Configuration;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PotionItem.class)
public class PotionItemMixin extends Item {

    public PotionItemMixin(Properties properties) {
        super(properties);
    }

    // Override the isFoil method of Item class in the PotionItem class
    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        if (Configuration.ENABLE_POTION_GLINT.get()) {
            return super.isFoil(stack) || PotionUtils.getPotion(stack).isFoil(stack);
        } else {
            return false;
        }
    }

}
