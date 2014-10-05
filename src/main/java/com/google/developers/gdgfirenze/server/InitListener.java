package com.google.developers.gdgfirenze.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;

public class InitListener implements ServletContextListener {

	private Server server = null;

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		server.stop();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		try {
			server = new Server("localhost", 8025, "/", null,ChatWebSocketServer.class);
			server.start();
		} catch (final DeploymentException e1) {
			e1.printStackTrace();
		}

	}
}
