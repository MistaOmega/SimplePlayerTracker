package mistaomega.pt.playertracker.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.Timer;
import java.util.logging.Logger;

public class trackerEvent implements Listener
{
    boolean isEnabled = false;
    Timer time;

    @EventHandler(priority= EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event){
        Action a = event.getAction();
        ItemStack is = event.getItem();
        isEnabled = !isEnabled; // invert the flag
        Player p = event.getPlayer();

        if(a == Action.PHYSICAL || is == null) // Ignore left clicks and if no items
            return;
        if(is.getType() == Material.COMPASS){
            try{
                if(Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equals(ChatColor.YELLOW + "PlayerTracker"))
                {
                    if (p.getInventory().getItemInMainHand().getItemMeta().getLore() != null)
                    {
                        System.out.println(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0));
                        Player hunted = Bukkit.getPlayer(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0));
                        if (hunted != null){
                            doTracking(p, hunted);
                        }
                        else{
                            p.sendMessage("Player not found");
                        }
                    }
                }
            } catch (Exception ignored)
            {
                p.sendMessage("Error");
            }
        }

    }


    public void doTracking(Player sender, Player hunted){
        Location loc = hunted.getLocation();
        sender.setCompassTarget(loc);
        sender.sendMessage("Tracking "+hunted.getName()+ " Last known location: " + loc.getBlockX() + " " + loc.getBlockZ() + " World: " + Objects.requireNonNull(loc.getWorld()).getName());
    }
}
