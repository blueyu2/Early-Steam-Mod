package com.blueyu2.steamMod.init;

import com.blueyu2.steamMod.SteamMod;
import com.blueyu2.steamMod.item.ItemCrushedOre;
import com.blueyu2.steamMod.item.ItemSM;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Blueyu2 on 8/8/2014.
 */
@GameRegistry.ObjectHolder(SteamMod.modId)
public class ModItems {
    public static final ItemSM crushedOre = new ItemCrushedOre();

    public static void init(){
        GameRegistry.registerItem(crushedOre, ItemCrushedOre.itemName);
    }
}
