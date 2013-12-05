package com.octopod.nixium.nxreality;

import com.octopod.nixium.nxreality.commands.FormatTextCommand;
import com.octopod.nixium.nxreality.worldeffects.NXRWorldEffects;
import org.bukkit.plugin.java.JavaPlugin;

import com.octopod.nixium.nxreality.commands.ShowBiomeCommand;

public class NXreality extends JavaPlugin{
	
	private static NXreality plugin;
	public static NXreality getInstance() {return plugin;}
	public static void log(String msg) {plugin.getLogger().info(msg);}
	
    @Override
    public void onEnable(){
        plugin = this;
        new Config(this);
    	getCommand("biome").setExecutor(new ShowBiomeCommand(this));
        getCommand("textf").setExecutor(new FormatTextCommand(this));
        NXRWorldEffects.startTimer();
    }
 
    @Override
    public void onDisable() {}	

}