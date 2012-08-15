package net.tigerclan.KingLinkTiger.BadNameKick;

import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BadNameKick extends JavaPlugin{
	
	public static BadNameKick plugin;
	
	public final Logger logger = Logger.getLogger("Minecraft");	
	
	@Override
	public void onDisable(){
		this.logger.info("BadNameKick is now disabled");
	}
	
	@Override
    public void onEnable() {      
    	getServer().getPluginManager().registerEvents(new Listener(){
    		@EventHandler
    		public void onJoin(PlayerLoginEvent event){
    			if(!bypass(event.getPlayer().getName())){
	    			if(!checkname(event.getPlayer().getName())){
	    				String 	message = "You have been denied access to the server because your name\n\ncontains an illegal character.";
	    				org.bukkit.event.player.PlayerLoginEvent.Result result = org.bukkit.event.player.PlayerLoginEvent.Result.KICK_OTHER;
	    				event.disallow(result, message);
	    				System.out.println(event.getPlayer().getName().toString() + " was denied access to the server due to an illegal character in their name.");
	    			}
    			}
    		}
    		
    		public boolean checkname(String name){
    			String player = name;
    			boolean found = false;
    			Pattern p = Pattern.compile("^[a-zA-Z0-9]+$");
    			Matcher m = p.matcher(player);
    			if(m.find()){
    			    found = true;
    			}
    			return found;
    		}
    	
    		
    	}, this);

	    this.getConfig().options().copyDefaults(true);
	    this.saveConfig();
        this.logger.info("BadNameKick is now enabled");
    }//End onEnable
    
	public boolean bypass(String name){
		reloadConfig();
		List<String> bypass = getConfig().getStringList("bypass");
		for(String n : bypass){
			System.out.println(n);
			if(n.equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}

}
