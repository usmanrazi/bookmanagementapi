package com.bookmanagementapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:external.properties")
public class ExternalConfig {
    @Value("${PORT}")
    private int serverPort;

    @Value("${DB_URL}")
    private String databaseUrl;

    @Value("${DB_USERNAME}")
    private String databaseUsername;

    @Value("${DB_PASSWORD}")
    private String databasePassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

    public int getServerPort() {
        return serverPort;
    }
}
