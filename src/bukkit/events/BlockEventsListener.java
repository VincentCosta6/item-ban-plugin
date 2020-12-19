package bukkit.events;

import bukkit.BanManager.BanManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockEventsListener extends EventsListener {
    public BlockEventsListener(JavaPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player p = event.getPlayer();

        p.sendMessage(event.getItemInHand().getType().toString());

        this.plugin.getLogger().info(event.getItemInHand().getType().toString());

        if (p.isOp() && BanManager.instance.opBypass) return;

        boolean isBanned = BanManager.instance.registry.isStringBanned(event.getItemInHand().getType().toString());
        if (isBanned) {
            p.sendMessage(ChatColor.RED + "You placed an illegal block");
            BanManager.instance.plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "Player: " + ChatColor.WHITE + p.getDisplayName() + ChatColor.DARK_RED +
                    " tried to place: " + ChatColor.WHITE + event.getBlock().getType().toString());
            event.setCancelled(true);
        }
    }
}
