package com.vkl.hblw.setting.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AppContextListener implements ServletContextListener {

	private Logger log = Logger.getLogger(AppContextListener.class);

	public void contextInitialized(ServletContextEvent sce) {

		try {
			log.info("");
			log.info("|======== VKL_HBLW AppContext is initialized. ========|");
			log.info("|");

			ApplicationContext context = WebApplicationContextUtils
					.getWebApplicationContext(sce.getServletContext());

			String webAppRootKey = sce.getServletContext().getRealPath("/");
			System.setProperty("hblw.root", webAppRootKey);
			String path = System.getProperty("hblw.root");

			
			
			log.info("|");
			log.info("|======== VKL_HBLW AppContext is finished.    ========|");
			log.info("|" + path);

		} catch (Exception e) {
			log.fatal("fatal error when app startup listen", e);
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		log.info("VKL_HBLW ServletContext is destroyed.");
	}

}