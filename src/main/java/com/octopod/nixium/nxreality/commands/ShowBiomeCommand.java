package com.octopod.nixium.nxreality.commands;

import com.octopod.nixium.utils.NXTextF;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.octopod.nixium.nxreality.NXreality;
import com.octopod.nixium.nxreality.worldeffects.EffectBiome;

import org.bukkit.ChatColor;

public class ShowBiomeCommand implements CommandExecutor{

	NXreality plugin;
	
	public ShowBiomeCommand(NXreality plugin) {this.plugin = plugin;}

        @Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) { return false; }

		final Player player = (Player)sender;

		EffectBiome eBiome = new EffectBiome(player.getLocation().getBlock().getBiome());
                player.sendMessage(new String[]{
                    NXTextF.block(ChatColor.GRAY + "You are in the biome " + ChatColor.GOLD + eBiome.getBiomeName(), 320, 2, "-"),
                    ChatColor.RED + "Hurt Interval: " + ChatColor.WHITE + eBiome.getHealthInterval(),
                    ChatColor.RED + "Food Interval: " + ChatColor.WHITE + eBiome.getFoodInterval()
                });
                
		return true;

	}
	

}