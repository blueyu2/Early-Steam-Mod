package com.blueyu2.steamMod.item;

import com.blueyu2.steamMod.SteamMod;
import com.blueyu2.steamMod.utility.CreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Created by Blueyu2 on 8/7/2014.
 */
public class ItemSM extends Item {
    public int texturesNeeded;
    public IIcon[] iconArray;
    public static String itemName;

    public ItemSM(){
        super();
        this.setCreativeTab(CreativeTab.SteamModTab);
    }

    @Override
    public String getUnlocalizedName(){
        return String.format("item.%s%s", SteamMod.resourcePrefix, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", SteamMod.resourcePrefix, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        if(texturesNeeded == 0){
            itemIcon = iconRegister.registerIcon(getUnwrappedUnlocalizedName(this.getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
            return;
        }
        iconArray=new IIcon[texturesNeeded];
        for(int i=0;i<texturesNeeded;i++){
            iconArray[i] = iconRegister.registerIcon(String.format("%s_%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()),i));
        }
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public Item setUnlocalizedName(String string){
        super.setUnlocalizedName(string);
        ItemSM.itemName = string;
        return this;
    }

    public void getTexturesNeeded(int number){
        texturesNeeded = number;
    }
}
