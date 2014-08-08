package com.blueyu2.steamMod.tileentity;

import com.blueyu2.steamMod.block.BlockPress;
import com.blueyu2.steamMod.container.ContainerPress;
import com.blueyu2.steamMod.recipe.RecipesPress;
import com.blueyu2.steamMod.item.crafting.RecipePress;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Blueyu2 on 8/6/2014.
 */
public class TileEntityPress extends TileEntitySM implements ISidedInventory {

    public static final int inventorySize = 3;
    public static final int inputIndex = 0;
    public static final int fuelIndex = 1;
    public static final int outputIndex = 2;
    public int deviceCookTime;
    public int fuelBurnTime;
    public int processTime;

    private ItemStack[] inventory;

    public TileEntityPress(){
        inventory = new ItemStack[inventorySize];
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == ForgeDirection.DOWN.ordinal() ? new int[]{outputIndex} : new int[]{inputIndex};
    }

    @Override
    public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side) {
        return isItemValidForSlot(slotIndex, itemStack);
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side) {
        return slotIndex == outputIndex;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound){
        super.readFromNBT(nbtTagCompound);

        NBTTagList tagList = nbtTagCompound.getTagList("Items", 10);
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); i++){
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length){
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }

        deviceCookTime = nbtTagCompound.getInteger("deviceCookTime");
        fuelBurnTime = nbtTagCompound.getInteger("fuelBurnTime");
        processTime = nbtTagCompound.getInteger("processTime");
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex) {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null){
            if (itemStack.stackSize <= decrementAmount){
                setInventorySlotContents(slotIndex, null);
            }
            else{
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0){
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }
        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex) {
        ItemStack itemStack = getStackInSlot(slotIndex);

        if (itemStack != null){
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
        inventory[slotIndex] = itemStack;

        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()){
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomName() ? this.getCustomName() : ContainerPress.name;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.hasCustomName();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack) {
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound){
        super.writeToNBT(nbtTagCompound);

        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; currentIndex++){
            if (inventory[currentIndex] != null){
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Items", tagList);
        nbtTagCompound.setInteger("deviceCookTime", deviceCookTime);
        nbtTagCompound.setInteger("fuelBurnTime", fuelBurnTime);
        nbtTagCompound.setInteger("processTime", processTime);
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int scale){
        if (this.fuelBurnTime > 0){
            return this.deviceCookTime * scale / this.fuelBurnTime;
        }
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public int getProcessProgressScaled(int scale){
        return this.processTime * scale / 200;
    }

    @Override
    public void updateEntity(){
        boolean isBurning = this.deviceCookTime > 0;
        boolean sendUpdate = false;

        if (this.deviceCookTime > 0){
            this.deviceCookTime--;
        }

        if (!this.worldObj.isRemote){
            if (this.deviceCookTime == 0 && this.canProcess()){
                this.fuelBurnTime = this.deviceCookTime = TileEntityFurnace.getItemBurnTime(this.inventory[fuelIndex]);

                if (this.deviceCookTime > 0){
                    sendUpdate = true;

                    if (this.inventory[fuelIndex] != null){
                        --this.inventory[fuelIndex].stackSize;

                        if (this.inventory[fuelIndex].stackSize == 0){
                            this.inventory[fuelIndex] = this.inventory[fuelIndex].getItem().getContainerItem(inventory[fuelIndex]);
                        }
                    }
                }
            }

            if (this.deviceCookTime > 0 && this.canProcess()){
                this.processTime++;

                if (this.processTime == 200){
                    this.processTime = 0;
                    this.processItem();
                    sendUpdate = true;
                }
            }
            else{
                this.processTime = 0;
            }

            if (isBurning != this.deviceCookTime > 0){
                sendUpdate = true;
                this.setState((byte)1);
                BlockPress.updateBlock(1, this.worldObj, xCoord, yCoord, zCoord);
            }
            else if (this.deviceCookTime == 0 && this.getState() == 1){
                this.setState((byte)0);
                BlockPress.updateBlock(0, this.worldObj, xCoord, yCoord, zCoord);
            }
        }
        if (sendUpdate){
            this.markDirty();
        }
    }

    private boolean canProcess(){
        if (inventory[inputIndex] == null){
            return false;
        }
        else{
            ItemStack itemStack = RecipesPress.getInstance().getResult(inventory[inputIndex]);
            if (itemStack == null){
                return false;
            }

            if (this.inventory[outputIndex] == null){
                return true;
            }
            else{
                boolean outputEquals = this.inventory[outputIndex].isItemEqual(itemStack);
                int mergedOutputStackSize = this.inventory[outputIndex].stackSize + itemStack.stackSize;

                if (outputEquals){
                    return mergedOutputStackSize <= getInventoryStackLimit() && mergedOutputStackSize <= itemStack.getMaxStackSize();
                }
            }
        }
        return false;
    }

    public void processItem(){
        if (this.canProcess()){
            RecipePress recipe = RecipesPress.getInstance().getRecipe(inventory[inputIndex]);

            if (this.inventory[outputIndex] == null){
                this.inventory[outputIndex] = recipe.getRecipeOutput().copy();
            }
            else if (this.inventory[outputIndex].isItemEqual(recipe.getRecipeOutput())){
                inventory[outputIndex].stackSize += recipe.getRecipeOutput().stackSize;
            }
            decrStackSize(inputIndex, recipe.getRecipeInput().stackSize);
        }

    }
}
