package com.octopod.nixium.utils;

import com.octopod.nixium.nxreality.NXRPlugin;
import com.octopod.nixium.nxreality.NXreality;
import org.bukkit.scheduler.BukkitTask;

public class NXTimer {
	
	public static BukkitTask runTimeout(long delay, Runnable code) {
		
		NXreality plugin = NXRPlugin.getPlugin();
		BukkitTask ID = plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, code, delay);
		return ID;
		
	}
	
	public static BukkitTask runInterval(long delay, Runnable code) {
		
		NXreality plugin = NXRPlugin.getPlugin();
		BukkitTask ID = plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, code, delay, delay);
		return ID;
		
	}
	
}
