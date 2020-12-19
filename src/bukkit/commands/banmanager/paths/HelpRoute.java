package bukkit.commands.banmanager.paths;

import bukkit.commands.CommandRouteController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class HelpRoute extends CommandRouteController {

    public HelpRoute(JavaPlugin plugin, int level) {
        super(plugin, level);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage("bm help - gives you the help page");
        sender.sendMessage("bm off - turns off BanManager");
        sender.sendMessage("bm on - turns on BanManager");

        sender.sendMessage("bm add [x] - add an item based on its name");
        sender.sendMessage("bm remove [x] - remove an item based on the name");
        sender.sendMessage("bm reload - reload the ban registry");

        return true;
    }

    @Override
    public void createRoutes(int level) {}
}
