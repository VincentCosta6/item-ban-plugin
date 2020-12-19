package bukkit.BanManager.registry;

import bukkit.BanManager.BanManager;

import java.io.IOException;

public interface RegistryInterface {
    void initializeRegistry() throws IOException;
    void unloadRegistry();
    boolean reloadRegistry();

    void setManager(BanManager manager);

    boolean isStringBanned(String string);

    void addString(String string) throws IOException;

    void removeString(String string);
}
