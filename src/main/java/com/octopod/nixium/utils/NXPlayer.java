package com.octopod.nixium.utils;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Player;

public class NXPlayer{
    
        private Player player;
	
        public NXPlayer(Player target){player = target;}
        
	public void hurt(int amount){
		
		double hp = player.getHealth();
                double resultant = Math.max(0, hp - amount);
                
                player.playEffect(EntityEffect.HURT);
                player.setHealth(resultant);
		
	}
        
	public void hurtFood(int amount){
		
		int food = player.getFoodLevel();
                
                if(food == 0){return;}
                
                int resultant = Math.max(0, food - amount);

                player.setFoodLevel(resultant);
		
	}

}