package com.dyonovan.needfulthings.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

/**
 * This file was created for NeedfulThings
 * <p>
 * NeedfulThings is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 2/28/2017
 */
public class EnchantmentBeheading extends Enchantment {

    private static final @Nonnull String NAME = "beheading";

    public static EnchantmentBeheading create() {
        EnchantmentBeheading enc = new EnchantmentBeheading();
        GameRegistry.register(enc);
        MinecraftForge.EVENT_BUS.register(enc);
        return enc;
    }

    private EnchantmentBeheading() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
        setName(NAME);
        setRegistryName(NAME);
    }

    @Override
    public int getMaxEnchantability(int level) {
        return 60;
    }

    @Override
    public int getMinEnchantability(int level) {
        return 16;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    //Capture event for Drops
    @SubscribeEvent
    public void onLivingDropsEvent(LivingDropsEvent event) {
        
    }
}
