package com.octopod.nixium.nxreality.worldeffects;

import java.util.HashMap;

import com.octopod.nixium.nxreality.NXreality;
import com.octopod.nixium.nxreality.Config;
import com.octopod.nixium.utils.PlayerUtils;
import com.octopod.nixium.utils.TaskUtils;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class NXRWorldEffects {
    
		static private HashMap<Player, Integer> health_interval = new HashMap<>();
        static private HashMap<Player, Integer> food_interval = new HashMap<>();
        static private BukkitTask timer;

        private YamlConfiguration config; //The biome, if the type is 0
        
        public NXRWorldEffects(){this.config = Config.getConfig();}
        
        public YamlConfiguration getConfig(){return this.config;}
        
        abstract public int getHealthInterval();
        abstract public int getFoodInterval();
        abstract public boolean playerCanOverride(Player player);
        abstract public String getOverridePerm();
        abstract public String[] getOverrideEquipment();
        
        //Get total applied health intervals.
        static private int getTotalHealthInterval(Player player){
            int total = 0;
            boolean inWater = (player.getEyeLocation().getBlock().getType() == Material.WATER || player.getEyeLocation().getBlock().getType() == Material.STATIONARY_WATER);

            //ProtectedRegion region = getPrioritizedRegion(player.getLocation());
            Biome biome = player.getLocation().getBlock().getBiome();
            
            EffectBiome eBiome = new EffectBiome(biome);
            EffectWater eWater = new EffectWater();
            //EffectWG eWG = new EffectWG(region);
            
            if(!eBiome.playerCanOverride(player)) {total += eBiome.getHealthInterval();}
            if(!eWater.playerCanOverride(player) && inWater) {total += eWater.getHealthInterval();}
            //if(!eWG.playerCanOverride(player)) {total += eWG.getHealthInterval();}

            return total;
        }
        
        //Get total applied food intervals.
        static private int getTotalFoodInterval(Player player){
            double total = 1;
            boolean inWater = (player.getEyeLocation().getBlock().getType() == Material.WATER || player.getEyeLocation().getBlock().getType() == Material.STATIONARY_WATER);

            //ProtectedRegion region = getPrioritizedRegion(player.getLocation());
            Biome biome = player.getLocation().getBlock().getBiome();
            
            EffectBiome eBiome = new EffectBiome(biome);
            EffectWater eWater = new EffectWater();
            //EffectWG eWG = new EffectWG(region);
            
            if(!eBiome.playerCanOverride(player)) {total = eBiome.getFoodInterval() / total;}
            if(!eWater.playerCanOverride(player) && inWater) {total = eWater.getFoodInterval() / total;}
            //if(!eWG.playerCanOverride(player)) {total += eWG.getFoodInterval();}

            return (int)Math.floor(total);
        }
 
        //Start (or restart) the main timer.
        static public void startTimer(){
            
            if(timer != null){timer.cancel();}

            Runnable task = new Runnable(){
                NXreality plugin = NXreality.getInstance();
                int healthInterval;
                int foodInterval;
                public void run(){
                    
                    for(Player player:plugin.getServer().getOnlinePlayers()){
                        
                        PlayerUtils p = new PlayerUtils(player);
                        if(health_interval.get(player) <= 0 || !health_interval.containsKey(player)) {
                        	
                            healthInterval = getTotalHealthInterval(player);
                            health_interval.put(player, healthInterval); 
                            if(healthInterval > 0) {p.hurt(1);}
                            
                        }else{
                            healthInterval = health_interval.get(player);
                        }

                        if(food_interval.get(player) <= 0 || !food_interval.containsKey(player)) {
                        	
                            foodInterval = getTotalFoodInterval(player);
                            food_interval.put(player, foodInterval); 
                            if(foodInterval > 0) {p.hurtFood(1);}
                            
                        }else{
                            foodInterval = food_interval.get(player);
                        }

                        if(healthInterval > 0 || foodInterval > 0){
                            health_interval.put(player, healthInterval - 1);
                            food_interval.put(player, foodInterval - 1);
                        }

                    }
                    
                }
            };
            
            TaskUtils.runInterval(20, task);
            
        }

        //Gets a biome from a string name.
	public Biome getBiome(String string){
		Biome biome;
		try{biome = Biome.valueOf(string);}catch(Exception e){biome = null;}
		return biome;
	}

        //Gets a biome at a location.
	public Biome getBiome(Block location){

		return location.getBiome();

	}
       	
}
