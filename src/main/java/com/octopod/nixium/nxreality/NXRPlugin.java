package com.octopod.nixium.nxreality;

import com.octopod.nixium.nxreality.NXreality;

public class NXRPlugin{
	
	private static NXreality plugin;

	public static void setPlugin(NXreality plugin){NXRPlugin.plugin = plugin;}
	public static NXreality getPlugin(){return plugin;}
	public static void console(String message){plugin.getLogger().info(message);}

}