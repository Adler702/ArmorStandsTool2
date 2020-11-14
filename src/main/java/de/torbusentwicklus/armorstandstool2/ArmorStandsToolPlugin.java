package de.torbusentwicklus.armorstandstool2;

import de.torbusentwicklus.armorstandstool2.api.mysql.MySQLConnection;
import de.torbusentwicklus.armorstandstool2.api.provider.ArmorStandsToolProvider;
import de.torbusentwicklus.armorstandstool2.api.provider.ConfigProvider;
import de.torbusentwicklus.armorstandstool2.commands.editmode.ArmorStandsEditModeCommandExecutor;
import de.torbusentwicklus.armorstandstool2.commands.editmode.ArmorStandsEditModeCommandImpl;
import de.torbusentwicklus.armorstandstool2.commands.setname.SetNameCommandExecutor;
import de.torbusentwicklus.armorstandstool2.commands.setname.SetNameCommandImpl;
import de.torbusentwicklus.armorstandstool2.listener.InventoryClickEventListener;
import de.torbusentwicklus.armorstandstool2.listener.PlayerInteractAtEntityEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;

public class ArmorStandsToolPlugin extends JavaPlugin {

    private  final ConfigProvider configProvider = new ConfigProvider(getConfig());
    private final MySQLConnection mySQLConnection = new MySQLConnection(configProvider.getMySqlCredentialsFromFile());
    private final ArmorStandsToolProvider armorStandsToolProvider = new ArmorStandsToolProvider(mySQLConnection, new HashMap<>());

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        Bukkit.getPluginManager().registerEvents(new PlayerInteractAtEntityEventListener(armorStandsToolProvider), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickEventListener(armorStandsToolProvider), this);
        mySQLConnection.connect();
        getCommand("armorstandseditmode").setExecutor(new ArmorStandsEditModeCommandExecutor(new ArmorStandsEditModeCommandImpl(armorStandsToolProvider)));
        getCommand("setname").setExecutor(new SetNameCommandExecutor(new SetNameCommandImpl(armorStandsToolProvider)));
    }

    @Override
    public void onDisable() {
        reloadConfig();
        mySQLConnection.disconnect();
    }
}
