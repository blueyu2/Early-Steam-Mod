package com.blueyu2.steamMod.utility;

import com.blueyu2.steamMod.SteamMod;
import com.blueyu2.steamMod.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Blueyu2 on 8/6/2014.
 */
public class CreativeTab {
    public static final CreativeTabs SteamModTab = new CreativeTabs(SteamMod.modId.toLowerCase()) {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(ModBlocks.pressBlock);
        }
    };
}
