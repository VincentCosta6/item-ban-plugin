package bukkit.entry;

import bukkit.BanManager.BanManager;
import bukkit.commands.banmanager.BanManagerCommandController;
import bukkit.events.BlockEventsListener;
import bukkit.events.CraftingEventsListener;
import bukkit.events.InteractionEventsListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GREEN + "Block Ban plugin has been loaded!");

        BanManager.initialize(this, false);

        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.GREEN + "Block Ban plugin has been unloaded!");

        BanManager.instance.unloadRegistry();
    }

    public void registerCommands() {
        this.getCommand("bm").setExecutor(new BanManagerCommandController(this));
    }
    public void registerListeners() {
        new BlockEventsListener(this);
        new CraftingEventsListener(this);
        new InteractionEventsListener(this);
    }
}
