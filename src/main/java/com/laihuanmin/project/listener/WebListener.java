package com.laihuanmin.project.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Web Container Initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Web Container Destroyed");
    }
}
