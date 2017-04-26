package com.splitemapp.service.backendrest.services;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletContext;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.HttpStatus;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public abstract class BaseServiceTest {

	public static final String SERVICE_TEST_BASE_PATH = "http://localhost:9999/splitemapp-backend-rest";
	public static final String TOKEN = "554686b8-7646-402e-9911-f28b8b417d46";

	private static final String JETTY_CONTEXT_FILE = "jetty.xml";
	private static final String JETTY_SERVER_BEAN = "jettyServer";

	private static boolean serverIsRunning = false;

	@BeforeClass
	public static void setUp() throws Exception {
		if(!serverIsRunning){
			// load the Jetty configuration XML file
			AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(JETTY_CONTEXT_FILE);
			// register to cleanup everything before shutting down server
			ctx.registerShutdownHook();

			// get the server bean instance defined in XML file
			Server server = (Server) ctx.getBean(JETTY_SERVER_BEAN);

			// get the servlet context
			ServletContext servletContext = null;

			for (Handler handler : server.getHandlers()) {
				if (handler instanceof Context) {
					Context context = (Context) handler;
					servletContext = context.getServletContext();
				}
			}

			// set the context attributes for web application
			XmlWebApplicationContext wctx = new XmlWebApplicationContext();
			wctx.setParent(ctx);
			wctx.setConfigLocation("");
			wctx.setServletContext(servletContext);
			wctx.refresh();

			servletContext.setAttribute(
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
					wctx);

			serverIsRunning = true;
		}
	}

	@Test
	public void serviceAvailabilityTest() {
		// Test GET
		HttpURLConnection http;
		try {
			for(String servicePath:getServicesPathList()){
				http = (HttpURLConnection)new URL(SERVICE_TEST_BASE_PATH +servicePath).openConnection();
				http.connect();
				assertEquals(HttpStatus.ORDINAL_200_OK, http.getResponseCode());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns a list of the relative path for all services involved in this test
	 * @return
	 */
	public abstract List<String> getServicesPathList();
}
