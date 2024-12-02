package org.might;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.might.model.Message;

import java.util.List;

@Slf4j
public class HelloHibernateTest {

    @Test
    public void testHelloHibernate() {
        log.info("Hello and welcome!");
        try (SessionFactory sessionFactory = HibUtils.getSessionFactory()) {
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
