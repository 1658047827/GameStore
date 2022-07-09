package com.fishandchips.listener;

import com.fishandchips.utils.DataSourceUtils;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class MyListener implements HttpSessionListener, ServletRequestListener, ServletContextListener,
        ServletContextAttributeListener,
        ServletRequestAttributeListener, HttpSessionAttributeListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
//        System.out.println("request initialize");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
//        System.out.println("request destroy");

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("Servlet initialize");
        } catch (Exception e) {
            System.out.println("Error initializing Servlet");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d = null;
        while (drivers.hasMoreElements()) {
            try {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
                System.out.printf("ContextFinalizer:Driver %s deregistered \n", d);
            } catch (SQLException ex) {
                System.out.println(String.format("ContextFinalizer:Error deregistering driver %s", d) + ":" + ex);
            }
        }
        DataSourceUtils.getDataSource().close();  //!!!significant
        AbandonedConnectionCleanupThread.uncheckedShutdown();
    }
}