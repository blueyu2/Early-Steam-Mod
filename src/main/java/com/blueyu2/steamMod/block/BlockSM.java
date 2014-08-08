package com.blueyu2.steamMod.block;

import com.blueyu2.steamMod.SteamMod;
import com.blueyu2.steamMod.tileentity.TileEntitySM;
import com.blueyu2.steamMod.utility.CreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

/**
 * Created by Blueyu2 on 8/5/2014.
 */
public class BlockSM extends Block {
    public int texturesNeeded;
    public IIcon[] iconArray;
    public static String blockName;

    public BlockSM(){
        this(Material.rock);
    }

    public BlockSM(Material material) {
        super(material);
        this.setCreativeTab(CreativeTab.SteamModTab);
        this.setHardness(1.5F);
        this.setStepSound(soundTypeStone);
    }

    @Override
    public String getUnlocalizedName(){
        return String.format("tile.%s%s", SteamMod.resourcePrefix, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        if(texturesNeeded == 0){
            blockIcon = iconRegister.registerIcon(getUnwrappedUnlocalizedName(this.getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
            return;
        }
        iconArray=new IIcon[texturesNeeded];
        for(int i=0;i<texturesNeeded;i++){
            iconArray[i] = iconRegister.registerIcon(String.format("%s_%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()),i));
        }
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName){
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public Block setBlockName(String string){
        super.setBlockName(string);
        BlockSM.blockName = string;
        return this;
    }

    public void getTexturesNeeded(int number){
        texturesNeeded = number;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta){
        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack){
        if (world.getTileEntity(x, y, z) instanceof TileEntitySM)
        {
            int direction = 0;
            int facing = MathHelper.floor_double(entityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

            if (facing == 0)
            {
                direction = ForgeDirection.NORTH.ordinal();
            }
            else if (facing == 1)
            {
                direction = ForgeDirection.EAST.ordinal();
            }
            else if (facing == 2)
            {
                direction = ForgeDirection.SOUTH.ordinal();
            }
            else if (facing == 3)
            {
                direction = ForgeDirection.WEST.ordinal();
            }

            if (itemStack.hasDisplayName())
            {
                ((TileEntitySM) world.getTileEntity(x, y, z)).setCustomName(itemStack.getDisplayName());
            }

            ((TileEntitySM) world.getTileEntity(x, y, z)).setOrientation(direction);
            world.setBlockMetadataWithNotify(x, y, z, direction, 2);
        }
    }

    public void dropInventory(World world, int x, int y, int z){
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory)){
            return;
        }

        IInventory inventory = (IInventory) tileEntity;

        for (int i=0; i<inventory.getSizeInventory(); i++){
            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0){
                Random random = new Random();

                float dX = random.nextFloat() * 0.8F + 0.1F;
                float dY = random.nextFloat() * 0.8F + 0.1F;
                float dZ = random.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, itemStack.copy());

                if (itemStack.hasTagCompound()){
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = random.nextGaussian() * factor;
                entityItem.motionY = random.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = random.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }
}
