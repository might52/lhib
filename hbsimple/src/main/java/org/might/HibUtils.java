package org.might;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@Slf4j
public class HibUtils {

    public static SessionFactory getSessionFactory() {
        Configuration configuration = getConfiguration();
        // Add annotated class
        // configuration.addAnnotatedClass(RandomNumberPOJO.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        MetadataSources sources = new MetadataSources(serviceRegistry);
        sources.addAnnotatedClass(org.might.model.Address.class);
        sources.addAnnotatedClass(org.might.model.Bid.class);
        sources.addAnnotatedClass(org.might.model.Category.class);
        sources.addAnnotatedClass(org.might.model.Item.class);
        sources.addAnnotatedClass(org.might.model.ItemBidSummary.class);
        sources.addAnnotatedClass(org.might.model.Message.class);
        sources.addAnnotatedClass(org.might.model.User.class);
        Metadata metadata = sources.getMetadataBuilder().build();
        log.info("ServiceRegistry created successfully");
        SessionFactory sessionFactory = metadata.buildSessionFactory();
        log.info("SessionFactory created successfully");
        log.info("Hibernate SessionFactory Configured successfully");
        return sessionFactory;
    }

    public static Configuration getConfiguration() {
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
        configuration.setProperty(AvailableSettings.HBM2DDL_AUTO, "create-drop");
        configuration.setProperty(AvailableSettings.FORMAT_SQL, "true");
        configuration.setProperty(AvailableSettings.USE_SQL_COMMENTS, "true");
//        configuration.setProperty(AvailableSettings.GLOBALLY_QUOTED_IDENTIFIERS, "true");
//        configuration.setProperty(AvailableSettings.PHYSICAL_NAMING_STRATEGY, "org.might.model.CustomNamingStrategy");
        return configuration;
    }
}
