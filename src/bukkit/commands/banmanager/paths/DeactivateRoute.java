package bukkit.commands.banmanager.paths;

import bukkit.BanManager.BanManager;
import bukkit.commands.CommandRouteController;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class DeactivateRoute extends CommandRouteController {
    public DeactivateRoute(JavaPlugin plugin, int level, String description) {
        super(plugin, level, description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!BanManager.instance.getActive()) {
            sender.sendMessage(ChatColor.RED + "Banmanager is already inactive!");
            return true;
        }

        BanManager.instance.setActive(false);
        sender.sendMessage(ChatColor.GREEN + "Banmanager has been deactivated!");
        return true;
    }

    @Override
    public void createRoutes(int level) {}
}
