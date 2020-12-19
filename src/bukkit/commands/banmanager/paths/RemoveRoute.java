package bukkit.commands.banmanager.paths;

import bukkit.BanManager.BanManager;
import bukkit.commands.CommandRouteController;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class RemoveRoute extends CommandRouteController {
    public RemoveRoute(JavaPlugin plugin, int level, String description) {
        super(plugin, level, description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length < this.level + 1) {
            sender.sendMessage(ChatColor.RED + "You must enter a string to unban. " + ChatColor.DARK_RED + "Ex: bm add LOG");
            return true;
        }

        // args starts at zero and is offset by one compared to level
        String removeString = args[this.level];

        if (!BanManager.instance.registry.isStringBanned(removeString)) {
            sender.sendMessage(ChatColor.RED + "This item is not currently banned!");
            return true;
        }

        try {
            BanManager.instance.registry.removeString(removeString);
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Error removing string from registry. Check the server logs");
            return true;
        }

        sender.sendMessage(ChatColor.GREEN + "Item: " + ChatColor.WHITE + removeString + ChatColor.GREEN + " has been removed from the banned registry");
        return true;
    }

    @Override
    public void createRoutes(int level) {}
}
