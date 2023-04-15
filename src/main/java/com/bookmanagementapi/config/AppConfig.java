package com.bookmanagementapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class AppConfig {
    @Autowired
    private ExternalConfig externalConfig;

    public int getServerPort() {
        return externalConfig.getServerPort();
    }

}
