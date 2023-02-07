package xyz.daarkii.school.common.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.NotNull;
import xyz.daarkii.school.common.document.Document;

import javax.sql.DataSource;

public class MySQLStorage {

    private final String host;
    private final String port;
    private final String database;
    private final String user;
    private final String password;

    private final HikariConfig config;
    private HikariDataSource dataSource;

    public MySQLStorage(@NotNull Document settings) {
        this.host = settings.getString("mysql.host");
        this.port = settings.getString("mysql.port");
        this.database = settings.getString("mysql.database");
        this.user = settings.getString("mysql.user");
        this.password = settings.getString("mysql.password");

        this.config = new HikariConfig();
        this.connect();
    }

    private void connect() {
        config.setJdbcUrl("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database);
        config.setDriverClassName("com.mysql.jdbc.Driver");

        config.setUsername(this.user);
        config.setPassword(this.password);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        config.setMaximumPoolSize(10);
        this.dataSource = new HikariDataSource(config);
    }

    public void close() {
        this.dataSource.close();
    }

    @NotNull
    public DataSource dataSource() {
        return this.dataSource;
    }

}
