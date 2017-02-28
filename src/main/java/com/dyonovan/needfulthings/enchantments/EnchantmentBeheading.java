package com.dyonovan.needfulthings.enchantments;

import com.dyonovan.needfulthings.lib.Reference;
import crazypants.enderio.machine.enchanter.EnchanterRecipeManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

import static crazypants.enderio.ModObject.blockEndermanSkull;

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
@Optional.InterfaceList({
@Optional.Interface(iface = "crazypants.enderio.ModObject.blockEndermanSkull", modid = "EnderIO"),
@Optional.Interface(iface = "crazypants.enderio.machine.enchanter.EnchanterRecipeManager", modid = "EnderIO")})
public class EnchantmentBeheading extends Enchantment {

    private static final @Nonnull String NAME = "beheading";

    public static EnchantmentBeheading create() {
        EnchantmentBeheading enc = new EnchantmentBeheading();
        GameRegistry.register(enc);
        MinecraftForge.EVENT_BUS.register(enc);
        if (Loader.isModLoaded("EnderIO")) {
            String xml = "<enchantment name=\"" + Reference.MOD_ID + ":" + NAME + "\" costPerLevel=\"10\" >\n" +
                    "    <itemStack modID=\"minecraft\" itemName=\"skull\" itemMeta=\"1\" />\n" +
                    "  </enchantment>";
            EnchanterRecipeManager.getInstance().addCustomRecipes(xml);
        }
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

    @Override
    public boolean canApplyTogether(Enchantment ench) { return true; }

    //Capture event for Drops
    @SubscribeEvent
    public void onLivingDropsEvent(LivingDropsEvent event) {
        DamageSource source = event.getSource();
        if (source.getDamageType().equalsIgnoreCase("player")) {
            EntityPlayer player = (EntityPlayer) event.getSource().getSourceOfDamage();
            int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByLocation(Reference.MOD_ID + ":" + NAME), player.getHeldItemMainhand());
            if (level > 0) {
                ItemStack skull = getSkullForEntity((EntityLivingBase) event.getEntity());
                if (skull != null) {
                    event.getDrops().add(new EntityItem(player.getEntityWorld(), player.posX, player.posY, player.posZ, skull));
                }
            }
        }
    }

    //Code Taken From EnderIO - https://github.com/SleepyTrousers/EnderIO
    private ItemStack getSkullForEntity(EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntitySkeleton) {
            if (((EntitySkeleton) entityLiving).getSkeletonType() == SkeletonType.WITHER) {
                return new ItemStack(Items.SKULL, 1, 1);
            } else {
                return new ItemStack(Items.SKULL, 1, 0);
            }
        } else if (entityLiving instanceof EntityZombie) {
            return new ItemStack(Items.SKULL, 1, 2);
        } else if (entityLiving instanceof EntityCreeper) {
            return new ItemStack(Items.SKULL, 1, 4);
        } else if (entityLiving instanceof EntityEnderman && Loader.isModLoaded("EnderIO")) {
                return enderioEnderSkull();
        }
        return null;
    }

    @Optional.Method(modid = "EnderIO")
    private ItemStack enderioEnderSkull() {
        return new ItemStack(blockEndermanSkull.getBlock());
    }
}
