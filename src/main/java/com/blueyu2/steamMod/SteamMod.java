package com.blueyu2.steamMod;

import com.blueyu2.steamMod.handler.GuiHandler;
import com.blueyu2.steamMod.init.ModBlocks;
import com.blueyu2.steamMod.init.Recipes;
import com.blueyu2.steamMod.proxy.IProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

/**
 * Created by Blueyu2 on 8/4/2014.
 */
@Mod(modid = SteamMod.modId, name = SteamMod.name, version = SteamMod.version)
public class SteamMod {
    public static final String modId = "steammod";
    public static final String name = "Steam Mod";
    public static final String version = "1.7.10-1.0";
    public static final String clientProxyClass = "com.blueyu2.steamMod.proxy.ClientProxy";
    public static final String serverProxyClass = "com.blueyu2.steamMod.proxy.ServerProxy";
    public static final String resourcePrefix = modId.toLowerCase() + ":";

    @Mod.Instance(SteamMod.modId)
    public static SteamMod instance;

    @SidedProxy(clientSide = clientProxyClass, serverSide = serverProxyClass)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ModBlocks.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        proxy.registerTileEntities();
        Recipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }
}
