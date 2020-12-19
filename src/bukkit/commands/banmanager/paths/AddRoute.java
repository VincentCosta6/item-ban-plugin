package bukkit.commands.banmanager.paths;

import bukkit.BanManager.BanManager;
import bukkit.commands.CommandRouteController;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class AddRoute extends CommandRouteController {
    public AddRoute(JavaPlugin plugin, int level, String description) {
        super(plugin, level, description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(args.length + ":" + this.level);

        // We want 1 extra param after 'add'
        if (args.length < this.level + 1) {
            sender.sendMessage(ChatColor.RED + "You must enter a string to ban. " + ChatColor.DARK_RED + "Ex: bm add LOG");
            return true;
        }

        // array starts at 0 so level is already offset by 1 compared to args
        String addString = args[this.level];

        if (BanManager.instance.registry.isStringBanned(addString)) {
            sender.sendMessage(ChatColor.RED + "This item is already banned!");
            return true;
        }

        try {
            BanManager.instance.registry.addString(addString);
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Error adding string to registry. Check the server logs");
            return true;
        }

        sender.sendMessage(ChatColor.GREEN + "Item: " + ChatColor.WHITE + addString + ChatColor.GREEN + " has been added to the banned registry");
        return true;
    }

    @Override
    public void createRoutes(int level) {}
}
