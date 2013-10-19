package com.octopod.nixium.nxreality.commands;

import com.octopod.nixium.utils.NXTextF;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.octopod.nixium.nxreality.NXreality;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FormatTextCommand implements CommandExecutor{

    NXreality plugin;

    public FormatTextCommand(NXreality plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length < 3) {return false;}
        int width = Integer.parseInt(args[0]);
        int alignment = Integer.parseInt(args[1]);
        String text = StringUtils.join(args, " ", 2, args.length);
        Player player = (Player)sender;
        player.sendMessage(NXTextF.block(ChatColor.translateAlternateColorCodes('&', text), width, alignment));

        return true;

    }
	
}