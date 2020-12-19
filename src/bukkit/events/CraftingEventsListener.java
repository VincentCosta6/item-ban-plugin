package bukkit.events;

import bukkit.BanManager.BanManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftingEventsListener extends EventsListener {
    public CraftingEventsListener(JavaPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onCraftEvent(CraftItemEvent event) {
        HumanEntity entity = event.getWhoClicked();

        Player player = this.plugin.getServer().getPlayer(entity.getUniqueId());

        player.sendMessage(event.getCurrentItem().getType().toString());

        if (player.isOp() && BanManager.instance.opBypass) return;

        boolean isBanned = BanManager.instance.registry.isStringBanned(event.getCurrentItem().getType().toString());
        if (isBanned) {
            player.sendMessage(ChatColor.RED + "You placed an illegal block");
            BanManager.instance.plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "Player: " + ChatColor.WHITE + player.getName() + ChatColor.DARK_RED +
                    " tried to place: " + ChatColor.WHITE + event.getCurrentItem().getType().toString());
            event.setCancelled(true);
        }
    }
}
