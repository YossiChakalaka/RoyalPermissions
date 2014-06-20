package me.or.perms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class RoyalPlayer {

	File f;
	FileConfiguration dataf;
	Player p;
	Plugin pa;
	List<String> perms;
	public RoyalPlayer(Player p, Plugin pa){
		this.pa = pa;
		this.p = p;
		if (!pa.getDataFolder().exists()){
			pa.getDataFolder().mkdir();
		}
		

		File f = new File(pa.getDataFolder(), p.getUniqueId().toString() +".yml");

		if (!f.exists()){
			try {

				f.createNewFile();
			} catch (IOException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();
			}
		}

			FileConfiguration dataf = YamlConfiguration.loadConfiguration(f);
			this.f = f;

			this.dataf = dataf;

			List<String> perms = new ArrayList<String>();
			List<String> permas = dataf.getStringList("perms");
			this.perms = permas;
			perms.add("example");
			dataf.set("perms", perms);
			
			save();

		
		
	}
	
	
	public List<String> getPermissions(){
		return perms;
	}
	public boolean contains(String perms){
		List<String> permsa = dataf.getStringList("perms");
		return permsa.contains(perms);
	}
	public void reload(){
		dataf = YamlConfiguration.loadConfiguration(f);
	}
	public void save(){
		try {
			dataf.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Plugin getPlugin(){
		return pa;
	}
	public FileConfiguration getPlayerData(){
		return dataf;
	}
	public File getPlayerFile(){
		return f;
	}
	public Player getPlayer(){
		return p;
	}
}
