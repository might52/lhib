package org.might;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class HelloJpaTest {


    @Test
    public void testHelloJpa() {
        log.info("Hello and welcome to unused approach at all!");
    }

//    private static SessionFactory initSession() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");
//        UserTransaction tx = ;
//        Configuration configuration = getConfiguration();
//        // Add annotated class
//        // configuration.addAnnotatedClass(RandomNumberPOJO.class);
//
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//        log.info("ServiceRegistry created successfully");
//        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//        log.info("SessionFactory created successfully");
//
//        log.info("Hibernate SessionFactory Configured successfully");
//        return sessionFactory;
//    }

//    private static Configuration getConfiguration() {
//        Configuration configuration = new Configuration();
//        configuration.setProperty("hibernate.connection.provider_class", "com.zaxxer.hikari.hibernate.HikariConnectionProvider");
//        configuration.setProperty("hibernate.hikari.minimumIdle", "5");
//        configuration.setProperty("hibernate.hikari.maximumPoolSize", "10");
//        configuration.setProperty("hibernate.hikari.idleTimeout", "30000");
//        configuration.setProperty("hibernate.hikari.dataSourceClassName", "org.postgresql.Driver");
//        configuration.setProperty("hibernate.hikari.dataSource.url", "jdbc:postgresql://localhost:5432/testDB");
//        configuration.setProperty("hibernate.hikari.dataSource.user", "postgres");
//        configuration.setProperty("hibernate.hikari.dataSource.password", "postgres");
//        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        return configuration.configure();
//    }
}
