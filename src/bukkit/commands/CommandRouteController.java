package bukkit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public abstract class CommandRouteController implements CommandExecutor {
    public int level = 0;
    public String description = "";
    public JavaPlugin plugin;
    public HashMap<String, CommandExecutor> routes;

    public CommandRouteController(JavaPlugin plugin) {
        this.plugin = plugin;
        this.routes = new HashMap<>();

        this.createRoutes(1);
    }

    public CommandRouteController(JavaPlugin plugin, int level) {
        this.plugin = plugin;
        this.level = level;
        this.routes = new HashMap<>();

        this.createRoutes(this.level + 1);
    }

    public CommandRouteController(JavaPlugin plugin, int level, String description) {
        this.plugin = plugin;
        this.level = level;
        this.description = description;
        this.routes = new HashMap<>();

        this.createRoutes(this.level + 1);
    }

    public abstract void createRoutes(int level);

    public boolean callRoute(String path, CommandSender sender, Command cmd, String label, String[] args) {
        CommandExecutor executor = this.routes.get(path);

        if (executor == null) {
            sender.sendMessage(ChatColor.YELLOW + "404: The command path " + ChatColor.WHITE + path + " does not exist");
            for(Map.Entry<String, CommandExecutor> route: this.routes.entrySet()) {
                String message = route.getKey() + ": ";

                if (route instanceof CommandRouteController) {
                    message += ((CommandRouteController) route).description;
                }

                sender.sendMessage(message);
            }

            return true;
        }

        return executor.onCommand(sender, cmd, label, args);
    }

    public boolean willCallDefault(String[] args) {
        return args.length == this.level;
    }

    public boolean callNext(CommandSender sender, Command cmd, String label, String[] args) {
        String newPath = willCallDefault(args) ? "default" : args[this.level];

        return this.callRoute(newPath, sender, cmd, label, args);
    }

    public void addRoute(String path, CommandExecutor executor) {
        this.routes.put(path, executor);
    }
}
