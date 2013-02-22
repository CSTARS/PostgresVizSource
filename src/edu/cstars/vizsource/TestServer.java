package edu.cstars.vizsource;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class TestServer {
	public static void main(String[] args) throws Exception {
        Server server = new Server(8282);
 
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
 
        context.addServlet(new ServletHolder(new DataSource()),"/rest");

        server.start();
        server.join();
    }
}
