package com.oec.publishing;

import com.oec.publishing.core.Part;
import com.oec.publishing.db.PartDAO;
import com.oec.publishing.resources.PublishingResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import javax.ws.rs.client.Client;


public class PublishingApplication extends Application<PublishingServiceConfiguration> {

    /**
     * Hibernate bundle.
     */
    private final HibernateBundle<PublishingServiceConfiguration> hibernateBundle = new HibernateBundle<PublishingServiceConfiguration>(Part.class) {
		@Override
		public DataSourceFactory getDataSourceFactory(PublishingServiceConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};

    /**
     * The main method of the application.
     *
     * @param args command-line arguments
     * @throws Exception any exception while executing the main() method.
     */
    public static void main(final String[] args) throws Exception {
        new PublishingApplication().run(args);
    }

    @Override
    public String getName() {
        return "Publishing";
    }

    @Override
    public void initialize(final Bootstrap<PublishingServiceConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final PublishingServiceConfiguration configuration, final Environment environment) {
        //Create Part DAO.
        final PartDAO partDAO = new PartDAO(hibernateBundle.getSessionFactory());
        //Create Jersey client.
        final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration()).build(getName());
        //Register a database-backed resource.
        environment.jersey().register(new PublishingResource(partDAO));
        //Register a resource using Jersey client.
        //environment.jersey().register(new ConverterResource(client,configuration.getApiURL(),configuration.getApiKey()));
    }

}
