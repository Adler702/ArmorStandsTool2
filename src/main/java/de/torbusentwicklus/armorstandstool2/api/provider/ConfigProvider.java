package de.torbusentwicklus.armorstandstool2.api.provider;

import de.torbusentwicklus.armorstandstool2.api.mysql.MySQLCredentials;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

@Getter
@AllArgsConstructor
public class ConfigProvider {

    public FileConfiguration fileConfiguration;

    public MySQLCredentials getMySqlCredentialsFromFile() {
        setConfigDefaults();
        return readFromFile();
    }

    private void setConfigDefaults() {
        fileConfiguration.addDefault("mysql.host", "host");
        fileConfiguration.addDefault("mysql.database", "database");
        fileConfiguration.addDefault("mysql.username", "username");
        fileConfiguration.addDefault("mysql.password", "password");
        fileConfiguration.addDefault("mysql.port", "port");

    }

    private MySQLCredentials readFromFile() {
        return new MySQLCredentials(fileConfiguration.getString("mysql.host"),
                fileConfiguration.getString("mysql.database"),
                fileConfiguration.getString("mysql.username"),
                fileConfiguration.getString("mysql.password"),
                fileConfiguration.getInt("mysql.port"));
    }
}
