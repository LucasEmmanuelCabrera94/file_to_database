package com.fiserv.job.file_to_database.configurations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(locations = "classpath:connection.properties")
@ComponentScan(basePackages = "com.fiserv.job.database_to_file.configurations")
@DisplayName("Tests for ApplicationConfiguration class")
public class ApplicationConfigurationTests {
    @Value("${datasource.driverClassName}")
    private String driverClassName;

    @Value("${datasource.url}")
    private String url;

    @Value("${datasource.username}")
    private String userName;

    @Value("${datasource.pass}")
    private String pass;

    @Autowired
    private DataSource dataSource;

    @Test
    @DisplayName("Test DataSource Properties")
    public void testDataSourceProperties() {
        assertNotNull(dataSource);

        assertNotNull(((DriverManagerDataSource) dataSource).getUrl());
        assertNotNull(((DriverManagerDataSource) dataSource).getUsername());
        assertNotNull(((DriverManagerDataSource) dataSource).getPassword());
    }

    @Test
    @DisplayName("Test Property Values")
    public void testPropertyValues() {
        assertNotNull(driverClassName);
        assertNotNull(url);
        assertNotNull(userName);
        assertNotNull(pass);
    }

    @Test
    @DisplayName("Test Missing or Incorrect Properties File")
    public void test_missing_or_incorrect_properties_file() {
        ApplicationConfiguration configuration = new ApplicationConfiguration();

        assertThrows(Exception.class, () -> {
            configuration.dataSource();
        });
    }
}
