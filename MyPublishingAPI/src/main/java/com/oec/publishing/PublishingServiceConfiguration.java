package com.oec.publishing;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.NotEmpty;

public class PublishingServiceConfiguration extends Configuration {


    
	
	/**
     * A getter for the database factory.
     *
     * @return An instance of database factory deserialized from the
     * configuration file passed as a command-line argument to the application.
     */
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }
	
	/**
     * A factory used to connect to a relational database management system.
     * Factories are used by Dropwizard to group together related configuration
     * parameters such as database connection driver, URI, password etc.
     */
    @NotNull
    @Valid
    private DataSourceFactory dataSourceFactory
            = new DataSourceFactory();

    /**
     * Jersey client default configuration.
     */
    @Valid
    @NotNull
    private JerseyClientConfiguration jerseyClientConfiguration
            = new JerseyClientConfiguration();

    /**
     * 
     * @return Jersey Client 
     */
    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClientConfiguration;
    }
	
}