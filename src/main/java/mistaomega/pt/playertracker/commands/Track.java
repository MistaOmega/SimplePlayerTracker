package mistaomega.pt.playertracker.commands;

import mistaomega.pt.playertracker.PlayerTracker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class Track implements CommandExecutor
{
    JavaPlugin pl = PlayerTracker.getInstance();
    FileConfiguration conf = pl.getConfig();
    Logger logger = Bukkit.getLogger();
    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(command.getLabel().equalsIgnoreCase("track")){
            if(sender instanceof Player){
                try{
                    final Player commandSenderP = (Player)sender; // These won't change
                    final Player huntedPlayer = Bukkit.getPlayer(args[0]);

                    assert huntedPlayer != null;
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(huntedPlayer.getName());

                    ItemStack item = new ItemStack(Material.COMPASS, 1);
                    ItemMeta meta = item.getItemMeta();
                    assert meta != null;
                    meta.setDisplayName(ChatColor.YELLOW + "PlayerTracker");
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    commandSenderP.getInventory().addItem(item);

                } catch (Exception e)
                {
                    sender.sendMessage("Error in command, format: track <player name>");
                    if(Objects.requireNonNull(conf.getString("debugMode")).equalsIgnoreCase("true")){
                        e.printStackTrace();
                    }
                }

            }
            else{
                sender.sendMessage("Run from a player, not the command prompt");
            }
        }

        return false;
    }
}
