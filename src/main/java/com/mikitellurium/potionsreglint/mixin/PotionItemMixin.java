package com.mikitellurium.potionsreglint.mixin;

import com.mikitellurium.potionsreglint.config.Configuration;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PotionItem.class)
public abstract class PotionItemMixin extends Item {

    public PotionItemMixin(Settings settings) {
        super(settings);
    }

    // Override the hasGlint method of Item class in the PotionItem class
    @Override
    public boolean hasGlint(ItemStack stack) {
        if (Configuration.ENABLE_POTION_GLINT.getValue()) {
            PotionContentsComponent potionContents = stack.get(DataComponentTypes.POTION_CONTENTS);
            return super.hasGlint(stack) || (potionContents != null && potionContents.hasEffects());
        } else {
            return false;
        }
    }

}
