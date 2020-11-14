package de.torbusentwicklus.armorstandstool2.api.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MySQLCredentials {

    private String host;
    private String database;
    private String username;
    private String password;
    private int port;

}
