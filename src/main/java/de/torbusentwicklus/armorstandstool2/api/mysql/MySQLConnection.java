package de.torbusentwicklus.armorstandstool2.api.mysql;

import lombok.SneakyThrows;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {

    private MySQLCredentials mySQLCredentials;

    private Connection connection;

    public MySQLConnection(MySQLCredentials mySQLCredentials) {
        this.mySQLCredentials = mySQLCredentials;
    }

    @SneakyThrows
    public void connect() {
        if (isConnected()) return;
        connection = DriverManager.getConnection("jdbc:mysql://" + mySQLCredentials.getHost() + ":" +
                        mySQLCredentials.getPort() + "/" +
                        mySQLCredentials.getDatabase(),
                mySQLCredentials.getUsername(),
                mySQLCredentials.getPassword());
        createTables();
    }

    @SneakyThrows
    public void disconnect() {
        if (!isConnected()) return;
        connection.close();
    }

    @SneakyThrows
    private void createTables() {
        performStatement("CREATE TABLE IF NOT EXISTS stands (JSONSTRING VARCHAR(5000), LOCATION VARCHAR(100), NAME VARCHAR(100))");
    }

    public boolean isConnected() {
        return connection != null;
    }

    @SneakyThrows
    private void performStatement(String query) {
        connection.prepareStatement(query).executeUpdate();
    }
}