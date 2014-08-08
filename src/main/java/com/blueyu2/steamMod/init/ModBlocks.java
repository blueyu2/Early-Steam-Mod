package com.blueyu2.steamMod.init;

import com.blueyu2.steamMod.SteamMod;
import com.blueyu2.steamMod.block.BlockPress;
import com.blueyu2.steamMod.block.BlockSM;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Blueyu2 on 8/6/2014.
 */
@GameRegistry.ObjectHolder(SteamMod.modId)
public class ModBlocks {
    public static final BlockSM pressBlock = new BlockPress();

    public static void init(){
        GameRegistry.registerBlock(pressBlock, BlockPress.blockName);
    }
}
