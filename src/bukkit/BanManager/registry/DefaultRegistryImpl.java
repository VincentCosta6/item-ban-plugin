package bukkit.BanManager.registry;

import bukkit.BanManager.BanManager;
import org.bukkit.ChatColor;

import java.io.*;
import java.util.HashSet;

public class DefaultRegistryImpl implements RegistryInterface {
    public static final String filePath = "./BannedItems.txt";

    private BanManager manager;
    private final HashSet<String> bannedNames = new HashSet();

    public DefaultRegistryImpl() {}

    @Override
    public void initializeRegistry() throws IOException {
        File tempFile = new File(filePath);
        boolean exists = tempFile.exists();

        if (!exists) {
            manager.plugin.getLogger().info(ChatColor.YELLOW + "Registry file does not exist! Creating...");

            if (tempFile.createNewFile()) {
                manager.plugin.getLogger().info(ChatColor.GREEN + "Registry file created!");
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            while (line != null) {
                line = line.replaceAll("\n", "").trim();
                bannedNames.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            manager.plugin.getLogger().info(ChatColor.RED + "Error, could not read registry after creating it!");
        } catch (IOException e) {
            manager.plugin.getLogger().info(ChatColor.RED + "An unknown error occurred while reading the registry file");
            e.printStackTrace();
        }
    }

    @Override
    public void unloadRegistry() {
        this.bannedNames.clear();
    }

    @Override
    public boolean reloadRegistry() {
        try {
            this.bannedNames.clear();
            this.initializeRegistry();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void setManager(BanManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean isStringBanned(String string) {
        return this.bannedNames.contains(string);
    }

    @Override
    public void addString(String string) throws FileNotFoundException {
        this.bannedNames.add(string);
        PrintWriter pw = new PrintWriter(filePath);
        pw.println(string);
        pw.close();
    }

    @Override
    public void removeString(String string) {
        this.bannedNames.remove(string);
    }
}
