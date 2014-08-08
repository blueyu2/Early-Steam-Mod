package com.blueyu2.steamMod.container;

import com.blueyu2.steamMod.block.BlockPress;
import com.blueyu2.steamMod.tileentity.TileEntityPress;
import com.blueyu2.steamMod.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * Created by Blueyu2 on 8/6/2014.
 */
public class ContainerPress extends Container {
    private TileEntityPress tileEntityPress;
    private int lastdeviceCookTime;
    private int lastBurnTime;
    private int lastProcessTime;
    public static final String name = "container.steammod:" + BlockPress.blockName;

    public ContainerPress(InventoryPlayer inventoryPlayer, TileEntityPress tileEntityPress){
        this.tileEntityPress = tileEntityPress;

        this.addSlotToContainer(new Slot(tileEntityPress, tileEntityPress.inputIndex, 56, 17));
        this.addSlotToContainer(new Slot(tileEntityPress, tileEntityPress.fuelIndex, 56, 53));
        this.addSlotToContainer(new SlotOutput(tileEntityPress, TileEntityPress.outputIndex, 116, 35));

        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; inventoryRowIndex++){
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; inventoryColumnIndex++){
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 84 + inventoryRowIndex * 18));
            }
        }

        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; actionBarSlotIndex++){
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 142));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting){
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityPress.deviceCookTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityPress.fuelBurnTime);
        iCrafting.sendProgressBarUpdate(this, 2, this.tileEntityPress.processTime);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public void detectAndSendChanges(){
        super.detectAndSendChanges();

        for (Object crafter : this.crafters){
            ICrafting iCrafting = (ICrafting) crafter;

            if (this.lastdeviceCookTime != this.tileEntityPress.deviceCookTime){
                iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityPress.deviceCookTime);
            }

            if (this.lastBurnTime != this.tileEntityPress.fuelBurnTime){
                iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityPress.fuelBurnTime);
            }

            if (this.lastProcessTime != this.tileEntityPress.processTime){
                iCrafting.sendProgressBarUpdate(this, 2, this.tileEntityPress.processTime);
            }
        }

        this.lastdeviceCookTime = this.tileEntityPress.deviceCookTime;
        this.lastBurnTime = this.tileEntityPress.fuelBurnTime;
        this.lastProcessTime = this.tileEntityPress.processTime;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex){
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()){
            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            if (slotIndex < TileEntityPress.inventorySize){
                if (!this.mergeItemStack(slotItemStack, TileEntityPress.inventorySize, inventorySlots.size(), false)){
                    return null;
                }
            }
            else{
                if (TileEntityFurnace.isItemFuel(slotItemStack)){
                    if (!this.mergeItemStack(slotItemStack, TileEntityPress.fuelIndex, TileEntityPress.outputIndex, false)){
                        return  null;
                    }
                }
                else if (!this.mergeItemStack(slotItemStack, TileEntityPress.inputIndex, TileEntityPress.outputIndex, false)){
                    return null;
                }
            }

            if(slotItemStack.stackSize == 0){
                slot.putStack(null);
            }
            else{
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue){
        if (valueType == 0){
            this.tileEntityPress.deviceCookTime = updatedValue;
        }

        if (valueType == 1){
            this.tileEntityPress.fuelBurnTime = updatedValue;
        }

        if (valueType == 2){
            this.tileEntityPress.processTime = updatedValue;
        }
    }
}
