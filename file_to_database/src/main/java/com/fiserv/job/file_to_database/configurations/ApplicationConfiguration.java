package com.fiserv.job.file_to_database.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:connection.properties") // Specifies the location of the properties file
public class ApplicationConfiguration {
    @Value("${datasource.driverClassName}")
    private String driverClassName;

    @Value("${datasource.url}")
    private String url;

    @Value("${datasource.username}")
    private String userName;

    @Value("${datasource.pass}")
    private String pass;


    /**
     * Configures and returns the DataSource bean.
     *
     * @return The configured DataSource.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.userName);
        dataSource.setPassword(this.pass);

        return dataSource;
    }
}
