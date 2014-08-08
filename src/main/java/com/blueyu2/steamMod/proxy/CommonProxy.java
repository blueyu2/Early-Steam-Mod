package com.blueyu2.steamMod.proxy;

import com.blueyu2.steamMod.block.BlockPress;
import com.blueyu2.steamMod.tileentity.TileEntityPress;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Blueyu2 on 6/30/2014.
 */
public abstract class CommonProxy implements IProxy {
    public void registerTileEntities(){
        GameRegistry.registerTileEntity(TileEntityPress.class, "tile." + BlockPress.blockName);
    }
}
