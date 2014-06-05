package com.estafet.induction.ev.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 * Returns configured DataSource object using configuration in 
 * /config/application.properties
 * 
 * @version 1.0
 * @author Marin Yakimov
 */
@Configuration
public class DataSourceConfiguration {
    @Autowired Environment env;

    /**
     * Returns newly configured DataSource instance.
     * 
     * @return newly configured DataSource
     */
    //TODO : Add connection pooling
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.apache.derby.jdbc.ClientDriver.class);
        dataSource.setUsername(env.getProperty("datasource.username"));
        dataSource.setUrl(env.getProperty("datasource.url"));
        dataSource.setPassword(env.getProperty("datasource.password"));
        
        return dataSource;
    }
}
