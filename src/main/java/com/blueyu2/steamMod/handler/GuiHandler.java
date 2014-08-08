package com.blueyu2.steamMod.handler;

import com.blueyu2.steamMod.client.gui.inventory.GuiPress;
import com.blueyu2.steamMod.container.ContainerPress;
import com.blueyu2.steamMod.tileentity.TileEntityPress;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Blueyu2 on 8/6/2014.
 */
public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiPress.guiId){
            TileEntityPress tileEntityPress = (TileEntityPress) world.getTileEntity(x, y, z);
            return new ContainerPress(player.inventory, tileEntityPress);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiPress.guiId){
            TileEntityPress tileEntityPress = (TileEntityPress) world.getTileEntity(x, y, z);
            return new GuiPress(player.inventory, tileEntityPress);
        }
        return null;
    }
}
