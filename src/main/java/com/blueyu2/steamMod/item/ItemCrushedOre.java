package com.blueyu2.steamMod.item;

import com.blueyu2.steamMod.SteamMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import java.util.List;

/**
 * Created by Blueyu2 on 8/8/2014.
 */
public class ItemCrushedOre extends ItemSM {
    public static final String[] crushedOreNames = {"crushedCoal", "crushedDiamond", "crushedEmerald", "crushedGold", "crushedIron", "crushedLapis", "crushedQuartz", "crushedRedstone"};

    public ItemCrushedOre(){
        super();
        this.setHasSubtypes(true);
        this.setUnlocalizedName("crushedOre");
        this.getTexturesNeeded(8);
    }

    /*@Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", SteamMod.resourcePrefix, ItemCrushedOre.itemName);
    }*/

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s.%s", SteamMod.resourcePrefix, ItemCrushedOre.itemName, crushedOreNames[MathHelper.clamp_int(itemStack.getItemDamage(), 0, crushedOreNames.length - 1)]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < crushedOreNames.length; meta++)
        {
            list.add(new ItemStack(this, 1, meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        return iconArray[MathHelper.clamp_int(meta, 0, crushedOreNames.length - 1)];
    }
}
