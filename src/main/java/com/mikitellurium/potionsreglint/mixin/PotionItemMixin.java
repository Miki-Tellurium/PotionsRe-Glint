package com.mikitellurium.potionsreglint.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PotionItem.class)
public class PotionItemMixin extends Item {

    public PotionItemMixin(Properties p_41383_) {
        super(p_41383_);
    }



}
