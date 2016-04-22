package org.martian.helloWorld;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.martian.helloWorld.resource.HelloWorldResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldApplication.class);

    public static final String API_VERSION = "v1";

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        LOGGER.info("initialization starting");
        bootstrap.addBundle(new AssetsBundle("/html/docs", "/docs/", "index.html"));
        LOGGER.info("initialization complete");
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {


        LOGGER.info("Registering resources...");
        final JerseyEnvironment jerseyEnvironment = environment.jersey();

        final HelloWorldResource helloWorldResource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        jerseyEnvironment.register(helloWorldResource);

        //These are needed for swagger
        jerseyEnvironment.register(new ApiListingResource());
        jerseyEnvironment.register(new SwaggerSerializers());

        LOGGER.info("Configuring swagger...");
        BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setVersion(API_VERSION);
        swaggerConfig.setResourcePackage("org.martian.helloWorld");
        swaggerConfig.setScan(true);

        LOGGER.info("run() complete");

    }
}
