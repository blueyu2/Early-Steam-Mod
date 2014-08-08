package com.blueyu2.steamMod.item.crafting;

import net.minecraft.item.ItemStack;

/**
 * Created by Blueyu2 on 8/6/2014.
 */
public class RecipePress {
    private ItemStack recipeOutput;
    private ItemStack recipeInput;

    public RecipePress(ItemStack recipeOutput, ItemStack recipeInput){
        this.recipeOutput = recipeOutput;
        this.recipeInput = recipeInput;
    }

    public ItemStack getRecipeInput(){
        return this.recipeInput;
    }

    public ItemStack getRecipeOutput(){
        return this.recipeOutput;
    }
}
