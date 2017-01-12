package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestsServlet;

import static java.util.logging.Logger.*;

/**
 * Created by hshvedko on 12.01.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/mirror");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        getGlobal().info("Server started");
        server.join();
    }
}
