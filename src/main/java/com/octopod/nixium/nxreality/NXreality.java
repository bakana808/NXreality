package com.octopod.nixium.nxreality;

import com.octopod.nixium.nxreality.commands.FormatTextCommand;
import com.octopod.nixium.nxreality.worldeffects.NXRWorldEffects;
import org.bukkit.plugin.java.JavaPlugin;

import com.octopod.nixium.nxreality.commands.ShowBiomeCommand;

public class NXreality extends JavaPlugin{
	
    @Override
    public void onEnable(){
        NXRPlugin.setPlugin(this);
        new NXRConfig(this);
    	getCommand("biome").setExecutor(new ShowBiomeCommand(this));
        getCommand("textf").setExecutor(new FormatTextCommand(this));
        NXRWorldEffects.startTimer();
    }
 
    @Override
    public void onDisable() {}	

}