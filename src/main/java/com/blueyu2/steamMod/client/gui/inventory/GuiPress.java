package com.blueyu2.steamMod.client.gui.inventory;

import com.blueyu2.steamMod.container.ContainerPress;
import com.blueyu2.steamMod.container.ContainerSM;
import com.blueyu2.steamMod.tileentity.TileEntityPress;
import com.blueyu2.steamMod.utility.ResourceLocationHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Created by Blueyu2 on 8/6/2014.
 */
public class GuiPress extends GuiContainer {
    private TileEntityPress tileEntityPress;
    public static final ResourceLocation pressGui = ResourceLocationHelper.getResourceLocation(GuiSM.GUI_SHEET_LOCATION + "press.png");
    public static final int guiId = 0;

    public GuiPress(InventoryPlayer inventoryPlayer, TileEntityPress tileEntityPress) {
        super(new ContainerPress(inventoryPlayer, tileEntityPress));
        this.tileEntityPress = tileEntityPress;
        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y){
        String containerName = StatCollector.translateToLocal(tileEntityPress.getInventoryName());
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal(ContainerSM.vanillaInventory), 8, ySize - 93, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(this.pressGui);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        int scaleAdjustment;
        if (this.tileEntityPress.deviceCookTime > 0){
            scaleAdjustment = this.tileEntityPress.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(xStart + 56, yStart + 49 - scaleAdjustment, 176, 12 - scaleAdjustment, 14, scaleAdjustment + 2);
        }

        scaleAdjustment = this.tileEntityPress.getProcessProgressScaled(24);
        this.drawTexturedModalRect(xStart + 79, yStart + 34, 176, 14, scaleAdjustment + 1, 16);
    }
}
