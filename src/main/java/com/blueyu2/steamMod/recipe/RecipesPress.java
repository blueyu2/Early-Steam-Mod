package com.blueyu2.steamMod.recipe;

import com.blueyu2.steamMod.item.crafting.RecipePress;
import com.blueyu2.steamMod.utility.LogHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blueyu2 on 8/6/2014.
 */
public class RecipesPress {
    private static RecipesPress pressRegistry = null;

    private List<RecipePress> pressRecipes;

    private RecipesPress(){
        pressRecipes = new ArrayList<RecipePress>();
    }

    public static RecipesPress getInstance(){
        if (pressRegistry == null){
            pressRegistry = new RecipesPress();
        }
        return pressRegistry;
    }

    public void addRecipe (ItemStack recipeOutput, ItemStack recipeInput){
        addRecipe(new RecipePress(recipeOutput, recipeInput));
    }

    public void addRecipe (RecipePress recipePress){
        if (!pressRecipes.contains(recipePress)){
            pressRecipes.add(recipePress);
        }
    }

    public ItemStack getResult(ItemStack recipeInput){
        for (RecipePress recipePress : pressRecipes){
            if (recipeInput.getItem().getIdFromItem(recipeInput.getItem()) == recipePress.getRecipeInput().getItem().getIdFromItem(recipePress.getRecipeInput().getItem())){
                if (recipeInput.getItemDamage() == recipePress.getRecipeInput().getItemDamage() || recipeInput.getItemDamage() == OreDictionary.WILDCARD_VALUE || recipePress.getRecipeInput().getItemDamage() == OreDictionary.WILDCARD_VALUE){
                    if (recipeInput.hasTagCompound() && recipePress.getRecipeInput().hasTagCompound()){
                        if (recipeInput.getTagCompound().hashCode() == recipePress.getRecipeInput().getTagCompound().hashCode()){
                            if (recipePress.getRecipeInput().stackSize <= recipeInput.stackSize){
                                return recipePress.getRecipeOutput();
                            }
                        }
                    }
                    else if (!recipeInput.hasTagCompound() && !recipePress.getRecipeInput().hasTagCompound()){
                        if (recipePress.getRecipeInput().stackSize <= recipeInput.stackSize){
                            return recipePress.getRecipeOutput();
                        }
                    }
                }
            }
        }
        return null;
    }

    public RecipePress getRecipe(ItemStack recipeInput){
        for (RecipePress recipePress : pressRecipes){
            if (recipeInput.getItem().getIdFromItem(recipeInput.getItem()) == recipePress.getRecipeInput().getItem().getIdFromItem(recipePress.getRecipeInput().getItem())){
                if (recipeInput.getItemDamage() == recipePress.getRecipeInput().getItemDamage() || recipeInput.getItemDamage() == OreDictionary.WILDCARD_VALUE || recipePress.getRecipeInput().getItemDamage() == OreDictionary.WILDCARD_VALUE){
                    if (recipeInput.hasTagCompound() && recipePress.getRecipeInput().hasTagCompound()){
                        if (recipeInput.getTagCompound().hashCode() == recipePress.getRecipeInput().getTagCompound().hashCode()){
                            if (recipePress.getRecipeInput().stackSize <= recipeInput.stackSize){
                                return recipePress;
                            }
                        }
                    }
                    else if (!recipeInput.hasTagCompound() && !recipePress.getRecipeInput().hasTagCompound()){
                        if (recipePress.getRecipeInput().stackSize <= recipeInput.stackSize){
                            return recipePress;
                        }
                    }
                }
            }
        }
        return null;
    }
}
