package mistaomega.pt.playertracker.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeath implements Listener
{
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        e.getEntity().sendMessage("Rip u x");
    }
}
