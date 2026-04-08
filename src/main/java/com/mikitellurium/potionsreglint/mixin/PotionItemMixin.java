package com.mikitellurium.potionsreglint.mixin;

import com.mikitellurium.potionsreglint.config.Configuration;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PotionItem.class)
public abstract class PotionItemMixin extends Item {

    public PotionItemMixin(Item.Properties properties) {
        super(properties);
    }

    // Override the hasGlint method of Item class in the PotionItem class
    @Override
    public boolean isFoil(ItemStack itemStack) {
        if (Configuration.ENABLE_POTION_GLINT.getValue()) {
            PotionContents contents = itemStack.get(DataComponents.POTION_CONTENTS);
            return super.isFoil(itemStack) || (contents != null && contents.hasEffects());
        } else {
            return false;
        }
    }
}
