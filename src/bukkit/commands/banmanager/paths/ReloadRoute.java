package bukkit.commands.banmanager.paths;

import bukkit.BanManager.BanManager;
import bukkit.commands.CommandRouteController;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ReloadRoute extends CommandRouteController {

    public ReloadRoute(JavaPlugin plugin, int level, String description) {
        super(plugin, level, description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        boolean reloadSuccess = BanManager.instance.registry.reloadRegistry();

        if (reloadSuccess) {
            sender.sendMessage(ChatColor.GREEN + "Banmanager registry has been reloaded!");
        } else {
            sender.sendMessage(ChatColor.RED + "Banmanager failed to reload, check the server logs");
        }

        return true;
    }

    @Override
    public void createRoutes(int level) {}
}
