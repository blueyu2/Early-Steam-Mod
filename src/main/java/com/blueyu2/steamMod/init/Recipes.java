package com.blueyu2.steamMod.init;

import com.blueyu2.steamMod.recipe.RecipesPress;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by Blueyu2 on 8/6/2014.
 */
public class Recipes {
    public static void init(){
        //Vanilla Recipes
        GameRegistry.addRecipe(new ItemStack(ModBlocks.pressBlock),"cpc","c c","sfs",'c',new ItemStack(Blocks.cobblestone),'p',new ItemStack(Blocks.piston), 'f', new ItemStack(Blocks.furnace), 's', new ItemStack(Blocks.stone));

        //Press Recipes
        RecipesPress.getInstance().addRecipe(new ItemStack(Items.sugar, 2, 0), new ItemStack(Items.reeds));
    }
}
