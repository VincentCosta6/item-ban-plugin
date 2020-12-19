package bukkit.commands.banmanager.paths;

import bukkit.BanManager.BanManager;
import bukkit.commands.CommandRouteController;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ActivateRoute extends CommandRouteController {
    public ActivateRoute(JavaPlugin plugin, int level, String description) {
        super(plugin, level, description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (BanManager.instance.getActive()) {
            sender.sendMessage(ChatColor.RED + "Banmanager is already active!");
            return true;
        }

        BanManager.instance.setActive(true);
        sender.sendMessage(ChatColor.GREEN + "Banmanager has been activated!");
        return true;
    }

    @Override
    public void createRoutes(int level) {}
}
