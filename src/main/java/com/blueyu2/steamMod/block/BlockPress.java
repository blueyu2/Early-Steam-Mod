package com.blueyu2.steamMod.block;

import com.blueyu2.steamMod.SteamMod;
import com.blueyu2.steamMod.client.gui.inventory.GuiPress;
import com.blueyu2.steamMod.tileentity.TileEntityPress;
import com.blueyu2.steamMod.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Blueyu2 on 8/6/2014.
 */
public class BlockPress extends BlockSM implements ITileEntityProvider {

    public BlockPress(){
        super();
        this.setBlockName("basicPress");
        this.getTexturesNeeded(6);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
        //North
        if (meta == 2){
            return side == 0 ? iconArray[5] : side == 1 ? iconArray[0] : side == 2 ? iconArray[3] : iconArray[1];
        }
        //South
        else if (meta == 3){
            return side == 0 ? iconArray[5] : side == 1 ? iconArray[0] : side == 3 ? iconArray[3] : iconArray[1];
        }
        //West
        else if (meta == 4){
            return side == 0 ? iconArray[5] : side == 1 ? iconArray[0] : side == 4 ? iconArray[3] : iconArray[1];
        }
        //East
        else if (meta == 5){
            return side == 0 ? iconArray[5] : side == 1 ? iconArray[0] : side == 5 ? iconArray[3] : iconArray[1];
        }
        //North (Active)
        else if (meta == 6){
            return side == 0 ? iconArray[5] : side == 1 ? iconArray[0] : side == 2 ? iconArray[4] : iconArray[2];
        }
        //South (Active)
        else if (meta == 7){
            return side == 0 ? iconArray[5] : side == 1 ? iconArray[0] : side == 3 ? iconArray[4] : iconArray[2];
        }
        //West (Active)
        else if (meta == 8){
            return side == 0 ? iconArray[5] : side == 1 ? iconArray[0] : side == 4 ? iconArray[4] : iconArray[2];
        }
        //East (Active)
        else if (meta == 9){
            return side == 0 ? iconArray[5] : side == 1 ? iconArray[0] : side == 5 ? iconArray[4] : iconArray[2];
        }
        //Error
        return side == 0 ? iconArray[5] : side == 1 ? iconArray[0] : side == 3 ? iconArray[3] : iconArray[1];
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata){
        return new TileEntityPress();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
        if (player.isSneaking()){
            return true;
        }

        else if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityPress){
            player.openGui(SteamMod.instance, GuiPress.guiId, world, x, y, z);
        }
        return true;
    }

    public static void updateBlock(int state,  World world, int x, int y, int z){
        int meta = world.getBlockMetadata(x, y, z);
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityPress) {
            if (state == 1 && meta < 6) {
                world.setBlockMetadataWithNotify(x, y, z, meta + 4, 2);
            } else if (state == 0 && meta > 5) {
                world.setBlockMetadataWithNotify(x, y, z, meta - 4, 2);
            }
        }
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random){
        int meta = world.getBlockMetadata(x, y, z);
        if (meta > 5){
            float f = (float)x + 0.5F;
            float f1 = (float)y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)z + 0.5F;
            float f3 = 0.52F;
            float f4 = random.nextFloat() * 0.6F - 0.3F;

            //North
            if (meta == 6){
                world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            }
            //South
            else if (meta == 7){
                world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            }
            //West
            else if (meta == 8){
                world.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            //East
            else if (meta == 9){
                world.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
