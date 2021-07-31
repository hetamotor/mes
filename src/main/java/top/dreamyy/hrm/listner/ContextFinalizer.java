package top.dreamyy.hrm.listner;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.mysql.cj.jdbc.Driver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class ContextFinalizer implements ServletContextListener {
    private static final Log logger = LogFactory.getLog(ContextFinalizer.class);

    public void contextInitialized(ServletContextEvent sce) {
    }

    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration drivers = DriverManager.getDrivers();

        Driver d = null;

        while (drivers.hasMoreElements()) {
            try {
                d = (Driver) drivers.nextElement();
                DriverManager.deregisterDriver(d);
                logger.warn(String.format("Driver %s deregistered", d));
            } catch (SQLException ex) {
                logger.warn(String.format("Error deregistering driver %s", d), ex);
            }
        }
        AbandonedConnectionCleanupThread.uncheckedShutdown();
    }
}

