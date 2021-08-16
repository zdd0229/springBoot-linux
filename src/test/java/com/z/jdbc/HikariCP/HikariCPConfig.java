package com.z.jdbc.HikariCP;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPConfig {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private static final String url = "jdbc:mysql://101.132.128.127:3307/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "root";

    static {
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    private HikariCPConfig() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
