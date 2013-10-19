
package com.octopod.nixium.nxreality.worldeffects;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class EffectAltitude extends NXRWorldEffects{
    
    private int altitude;

    public EffectAltitude(int altitude){
        super();
        this.altitude = altitude;
    }
    
    @Override
    public int getHealthInterval(){
       try{return getConfig().getInt("water.healthInterval");} catch(NullPointerException e) {return 0;}
    }
    
    @Override
    public int getFoodInterval(){
       try{return getConfig().getInt("water.foodInterval");} catch(NullPointerException e) {return 0;}
    }
    
    @Override
    public String[] getOverrideEquipment(){
        try{return (String[])getConfig().getStringList("water.overrideEquipment").toArray();} catch(NullPointerException e) {return new String[0];}
    }
    
    @Override
    public String getOverridePerm(){
        try{return getConfig().get("water.overridePerm").toString();} catch(NullPointerException e) {return "";}
    }

    @Override
    public boolean playerCanOverride(Player player){
        boolean override = true;

        String node = getOverridePerm();
        if(node.equals("") || !player.hasPermission(node)) {override = false;}
        else{
            PlayerInventory inv = player.getInventory();
            
            String helmetDisplay = ChatColor.stripColor(inv.getHelmet().getItemMeta().getDisplayName()).toLowerCase();
            String chestDisplay = ChatColor.stripColor(inv.getChestplate().getItemMeta().getDisplayName()).toLowerCase();
            String legDisplay = ChatColor.stripColor(inv.getLeggings().getItemMeta().getDisplayName()).toLowerCase();
            String bootDisplay = ChatColor.stripColor(inv.getBoots().getItemMeta().getDisplayName()).toLowerCase();

            for(String equipment:getOverrideEquipment()) {
                if(
                    !helmetDisplay.equals(equipment.toLowerCase()) &&
                    !chestDisplay.equals(equipment.toLowerCase()) && 
                    !legDisplay.equals(equipment.toLowerCase()) &&
                    !bootDisplay.equals(equipment.toLowerCase())
                ) {
                    override = false; 
                    break;
                }
            }
        }
        return override;
    }
    
}
