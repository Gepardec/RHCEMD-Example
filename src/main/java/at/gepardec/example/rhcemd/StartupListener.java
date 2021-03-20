package at.gepardec.example.rhcemd;

import at.gepardec.example.rhcemd.mp.config.ApplicationConfig;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener
public class StartupListener implements ServletContextListener {

    @Inject
    private ApplicationConfig appConfig;

    @Inject
    private Logger log;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Application started with configuration: " + appConfig.toString());
    }
}
