package mistaomega.pt.playertracker;

import mistaomega.pt.playertracker.commands.Track;
import mistaomega.pt.playertracker.events.trackerEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class PlayerTracker extends JavaPlugin
{
    private static PlayerTracker instance;
    public PlayerTracker(){
        if(PlayerTracker.instance!= null){
            throw new Error("Plugin already initialised!");
        }
        PlayerTracker.instance = this;
    }

    public static PlayerTracker getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        getLogger().info("I can smell you");
        saveDefaultConfig();
        Objects.requireNonNull(getCommand("track")).setExecutor(new Track());
        getServer().getPluginManager().registerEvents(new trackerEvent(), this);
    }

    @Override
    public void onDisable()
    {
        getLogger().info("In a bit");
    }



}
