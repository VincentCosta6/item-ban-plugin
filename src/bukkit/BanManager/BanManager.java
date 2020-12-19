package bukkit.BanManager;

import bukkit.BanManager.registry.DefaultRegistryImpl;
import bukkit.BanManager.registry.RegistryInterface;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class BanManager {
    public static BanManager instance;

    public static BanManager initialize(JavaPlugin plugin) {
        RegistryInterface registry = new DefaultRegistryImpl();
        BanManager manager = new BanManager(registry);

        registry.setManager(manager);

        instance = manager;

        manager.loadRegistry();
        manager.plugin = plugin;

        return manager;
    }

    public static BanManager initialize(JavaPlugin plugin, RegistryInterface registryInterface, boolean opBypass) {
        BanManager manager = new BanManager(registryInterface);

        registryInterface.setManager(manager);

        instance = manager;
        manager.plugin = plugin;
        manager.opBypass = opBypass;

        manager.loadRegistry();

        return manager;
    }

    public static BanManager initialize(JavaPlugin plugin, boolean opBypass) {
        RegistryInterface registry = new DefaultRegistryImpl();
        BanManager manager = new BanManager(registry);

        registry.setManager(manager);

        instance = manager;
        manager.plugin = plugin;
        manager.opBypass = opBypass;

        manager.loadRegistry();

        return manager;
    }

    public JavaPlugin plugin;
    public RegistryInterface registry;
    private boolean active = true;
    public boolean opBypass = false;

    private BanManager(RegistryInterface registry) {
        this.registry = registry;
    }

    private BanManager(RegistryInterface registry, boolean opBypass) {
        this.registry = registry;
        this.opBypass = opBypass;
    }

    public void loadRegistry() {
        try {
            this.registry.initializeRegistry();
            this.plugin.getLogger().info("Block Ban registry has been loaded!");
        } catch (IOException e) {
            this.plugin.getLogger().info("Uncaught error while initializing registry!");
            e.printStackTrace();
        }
    }

    public void unloadRegistry() {
        this.registry.unloadRegistry();
        this.plugin.getLogger().info("Block Ban registry has been unloaded!");
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }
}
