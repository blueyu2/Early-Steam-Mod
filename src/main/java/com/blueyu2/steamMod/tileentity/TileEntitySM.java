package com.blueyu2.steamMod.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Blueyu2 on 8/6/2014.
 */
public class TileEntitySM extends TileEntity {
    protected ForgeDirection orientation;
    protected byte state;
    protected String customName;

    public static final String Orientation = "Oreientation";
    public static final String Custom_Name = "CustomName";
    public static final String State = "State";

    public TileEntitySM(){
        orientation = ForgeDirection.SOUTH;
        //state = 0;
        customName = "";
    }

    public ForgeDirection getOrientation(){
        return  orientation;
    }

    public void setOrientation(ForgeDirection orientation){
        this.orientation = orientation;
    }

    public void setOrientation(int orientation){
        this.orientation = ForgeDirection.getOrientation(orientation);
    }

    public short getState(){
        return state;
    }

    public void setState(byte state){
        this.state = state;
    }

    public String getCustomName(){
        return customName;
    }

    public void setCustomName(String customName){
        this.customName = customName;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound){
        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Orientation)){
            this.orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte(Orientation));
        }

        if (nbtTagCompound.hasKey(State)){
            this.state = nbtTagCompound.getByte(State);
        }

        if (nbtTagCompound.hasKey(Custom_Name)){
            this.customName = nbtTagCompound.getString(Custom_Name);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound){
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setByte(Orientation, (byte) orientation.ordinal());
        nbtTagCompound.setByte(State, state);

        if (this.hasCustomName()){
            nbtTagCompound.setString(Custom_Name, customName);
        }
    }

    public boolean hasCustomName(){
        return customName != null && customName.length() > 0;
    }
}
