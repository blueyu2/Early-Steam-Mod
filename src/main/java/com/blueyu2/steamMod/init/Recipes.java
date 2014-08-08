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

        GameRegistry.addSmelting(new ItemStack(ModItems.crushedOre, 1, 0), new ItemStack(Items.coal), 0.1F);
        GameRegistry.addSmelting(new ItemStack(ModItems.crushedOre, 1, 1), new ItemStack(Items.diamond), 1.0F);
        GameRegistry.addSmelting(new ItemStack(ModItems.crushedOre, 1, 2), new ItemStack(Items.emerald), 1.0F);
        GameRegistry.addSmelting(new ItemStack(ModItems.crushedOre, 1, 3), new ItemStack(Items.gold_ingot), 1.0F);
        GameRegistry.addSmelting(new ItemStack(ModItems.crushedOre, 1, 4), new ItemStack(Items.iron_ingot), 0.7F);
        GameRegistry.addSmelting(new ItemStack(ModItems.crushedOre, 1, 5), new ItemStack(Items.dye, 1, 4), 0.2F);
        GameRegistry.addSmelting(new ItemStack(ModItems.crushedOre, 1, 6), new ItemStack(Items.quartz), 0.2F);
        GameRegistry.addSmelting(new ItemStack(ModItems.crushedOre, 1, 7), new ItemStack(Items.redstone), 0.7F);


        //Press Recipes
        RecipesPress.getInstance().addRecipe(new ItemStack(Items.sugar, 2, 0), new ItemStack(Items.reeds));
        RecipesPress.getInstance().addRecipe(new ItemStack(Items.blaze_powder, 3, 0), new ItemStack(Items.blaze_rod));
        RecipesPress.getInstance().addRecipe(new ItemStack(Items.dye, 4, 15), new ItemStack(Items.bone));

        RecipesPress.getInstance().addRecipe(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.stone));
        RecipesPress.getInstance().addRecipe(new ItemStack(Blocks.gravel), new ItemStack(Blocks.cobblestone));
        RecipesPress.getInstance().addRecipe(new ItemStack(Blocks.sand), new ItemStack(Blocks.gravel));

        RecipesPress.getInstance().addRecipe(new ItemStack(ModItems.crushedOre, 2, 0), new ItemStack(Blocks.coal_ore));
        RecipesPress.getInstance().addRecipe(new ItemStack(ModItems.crushedOre, 2, 1), new ItemStack(Blocks.diamond_ore));
        RecipesPress.getInstance().addRecipe(new ItemStack(ModItems.crushedOre, 2, 2), new ItemStack(Blocks.emerald_ore));
        RecipesPress.getInstance().addRecipe(new ItemStack(ModItems.crushedOre, 2, 3), new ItemStack(Blocks.gold_ore));
        RecipesPress.getInstance().addRecipe(new ItemStack(ModItems.crushedOre, 2, 4), new ItemStack(Blocks.iron_ore));
        RecipesPress.getInstance().addRecipe(new ItemStack(ModItems.crushedOre, 2, 5), new ItemStack(Blocks.lapis_ore));
        RecipesPress.getInstance().addRecipe(new ItemStack(ModItems.crushedOre, 2, 6), new ItemStack(Blocks.quartz_ore));
        RecipesPress.getInstance().addRecipe(new ItemStack(ModItems.crushedOre, 2, 7), new ItemStack(Blocks.redstone_ore));

    }
}
