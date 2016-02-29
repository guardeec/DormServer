package services;

import threads.PingListner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Guardeec on 29.02.16.
 */
public class ApplicationInitializer implements ServletContextListener {
    private Thread thread = new Thread(new PingListner());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.thread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        this.thread.interrupt();
    }
}
