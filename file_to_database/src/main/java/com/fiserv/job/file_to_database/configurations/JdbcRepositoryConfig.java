package com.fiserv.job.file_to_database.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories(basePackages = "com.fiserv.job.retrieving_from_bd.repositories.jdbc")
public class JdbcRepositoryConfig {
    // This class had to be created to specify the script about the use of jdbc in the repositories classes.
}
