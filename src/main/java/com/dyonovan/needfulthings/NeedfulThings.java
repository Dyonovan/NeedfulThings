package com.dyonovan.needfulthings;

import com.dyonovan.needfulthings.enchantments.EnchantmentBeheading;
import com.dyonovan.needfulthings.lib.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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

@Mod(modid          = Reference.MOD_ID,
        name           = Reference.MOD_NAME,
        version        = Reference.VERSION,
        dependencies   = Reference.DEPENDENCIES,
        updateJSON     = Reference.UPDATE_JSON)
public class NeedfulThings {

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public static void posInit(FMLPostInitializationEvent event) {
        EnchantmentBeheading.create();
    }
}
