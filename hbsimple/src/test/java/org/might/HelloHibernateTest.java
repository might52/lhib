package org.might;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.might.model.Message;

import java.util.List;

@Slf4j
public class HelloHibernateTest {

    private static SessionFactory getSessionFactory() {
        Configuration configuration = getConfiguration();
        // Add annotated class
        // configuration.addAnnotatedClass(RandomNumberPOJO.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        MetadataSources sources = new MetadataSources(serviceRegistry);
        sources.addAnnotatedClass(org.might.model.Message.class);
        Metadata metadata = sources.getMetadataBuilder().build();
        Assertions.assertEquals(1, metadata.getEntityBindings().size());
        log.info("ServiceRegistry created successfully");
        SessionFactory sessionFactory = metadata.buildSessionFactory();
        log.info("SessionFactory created successfully");
        log.info("Hibernate SessionFactory Configured successfully");
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.hikari.minimumIdle", "5");
        configuration.setProperty("hibernate.hikari.maximumPoolSize", "1");
        configuration.setProperty("hibernate.hikari.idleTimeout", "30000");
        configuration.setProperty(AvailableSettings.URL, "jdbc:postgresql://localhost:5432/testdb");
        configuration.setProperty(AvailableSettings.USER, "postgres");
        configuration.setProperty(AvailableSettings.PASS, "postgres");
        configuration.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        configuration.setProperty(AvailableSettings.JPA_VALIDATION_MODE, "NONE");
        configuration.setProperty(AvailableSettings.GLOBALLY_QUOTED_IDENTIFIERS, "true");
        configuration.setProperty(AvailableSettings.HBM2DDL_AUTO, "create-drop");
        configuration.setProperty(AvailableSettings.FORMAT_SQL, "true");
        configuration.setProperty(AvailableSettings.USE_SQL_COMMENTS, "true");
        return configuration;
    }

    @Test
    public void testHelloHibernate() {
        log.info("Hello and welcome!");
        try (SessionFactory sessionFactory = getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            Transaction tx = session.beginTransaction();
            Message message = new Message();
            message.setText("Hello World!");
            session.save(message);
            tx.commit();
            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            List<Message> messages = session.createQuery("from Message").list();
            Assertions.assertEquals(1, messages.size());
            Assertions.assertEquals("Hello World!", messages.get(0).getText());
            tx.commit();
        }
    }
}
