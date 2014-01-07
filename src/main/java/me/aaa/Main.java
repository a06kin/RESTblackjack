package me.aaa;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.net.UnknownHostException;

import com.mongodb.*;

public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:7777/";

    public static Datastore ds;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in me.aaa package
        final ResourceConfig rc = new ResourceConfig().packages("me.aaa");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {

        configDB();

        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
//        server.stop();
        server.shutdown();
    }

    private static void configDB(){
        //TODO: check DB if needed create
        try {
            Morphia morphia = new Morphia();
            Mongo mongo = new Mongo();
            ds = morphia.createDatastore(mongo, "blackjack");

            ds.ensureIndexes(); //TODO: find what to do. Taken from example
            ds.ensureCaps();    //TODO: find what to do. Taken from example

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

