
package com.octopod.nixium.nxreality.worldeffects;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class EffectBiome extends NXRWorldEffects{

    private Biome biome; //The biome, if the type is 0
    
    public EffectBiome(Biome biome){
        super();
        this.biome = biome;
    }
    
    public String getBiomeName(){
        return WordUtils.capitalizeFully(biome.name());
    }
    
    @Override
    public int getHealthInterval(){
       try{return getConfig().getInt("biome."+biome.name()+".healthInterval");} catch(NullPointerException e) {return 0;}
    }
    
    @Override
    public int getFoodInterval(){
       try{return getConfig().getInt("biome."+biome.name()+".foodInterval");} catch(NullPointerException e) {return 0;}
    }
    
    @Override
    public String[] getOverrideEquipment(){
        try{return (String[])getConfig().getStringList("biome."+biome.name()+".overrideEquipment").toArray();} catch(NullPointerException e) {return new String[0];}
    }
    
    @Override
    public String getOverridePerm(){
        try{return getConfig().get("biome."+biome.name()+".overridePerm").toString();} catch(NullPointerException e) {return "";}
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