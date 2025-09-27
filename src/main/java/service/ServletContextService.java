package service;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

@WebListener
public class ServletContextService implements ServletContextListener {
    private SessionFactory sessionFactory;


    @Override
    public void contextInitialized(ServletContextEvent context) {
        sessionFactory = HibernateUtils.getSessionFactory();
        context.getServletContext().setAttribute("sessionFactory", sessionFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtils.shutDown();
    }
}