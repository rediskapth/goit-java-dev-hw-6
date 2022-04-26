package ua.goit.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresHikariProvider implements DatabaseManager {

    private final HikariDataSource dataSource;

    public PostgresHikariProvider(String hostname, int port, String databaseName, String username, String password, String jdbcDriver) {
        HikariConfig config = new HikariConfig();
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading postgres driver", e);
        }

        config.setJdbcUrl(String.format("jdbc:postgresql://%s:%d/%s", hostname, port, databaseName));
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(100);
        this.dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
