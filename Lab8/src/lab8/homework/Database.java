package lab8.compulsory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class Database {

    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    private static final String URL = "jdbc:mysql://localhost:3306/artists";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;
    private static HikariDataSource dataSource;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Failed to create database connection", e);
            throw new RuntimeException(e);
        }
    }

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/artists");
        config.setUsername("root");
        config.setPassword("");
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(20);
        dataSource = new HikariDataSource(config);
    }

    private Database() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Failed to create database connection", e);
            System.err.println(e);
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Failed to close database connection", e);
            System.err.println(e);
        }
    }

    public static void rollback() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error("Failed to rollback transaction", e);
            System.err.println(e);
        }
    }
}
