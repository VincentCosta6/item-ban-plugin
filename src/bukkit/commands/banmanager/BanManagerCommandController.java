package bukkit.commands.banmanager;

import bukkit.commands.CommandRouteController;
import bukkit.commands.banmanager.paths.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BanManagerCommandController extends CommandRouteController {
    public BanManagerCommandController(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.isOp()) {
                player.sendMessage(ChatColor.RED + "You do not have permission to use the BanManager commands");
                return true;
            }
        }

        return super.callNext(sender, cmd, label, args);
    }

    @Override
    public void createRoutes(int level) {
        this.addRoute("on", new ActivateRoute(this.plugin, level, "Turn on Banmanager"));
        this.addRoute("off", new DeactivateRoute(this.plugin, level, "Turn off Banmanager"));

        this.addRoute("add", new AddRoute(this.plugin, level, "Add an item to the banned list"));
        this.addRoute("remove", new RemoveRoute(this.plugin, level, "Remove an item from the banned list"));

        this.addRoute("reload", new ReloadRoute(this.plugin, level, "Reload Banmanager's registry"));

        this.addRoute("test", (CommandSender sender, Command cmd, String label, String[] args) -> {
            sender.sendMessage("This is really cool");
            return true;
        });

        this.addRoute("default", new HelpRoute(this.plugin, level));
    }
}
