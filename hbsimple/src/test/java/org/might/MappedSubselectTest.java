package org.might;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.might.model.Bid;
import org.might.model.Item;
import org.might.model.ItemBidSummary;

import javax.persistence.Query;
import java.math.BigDecimal;

@Slf4j
public class MappedSubselectTest {

    @Test
    public void loadSubselectEntity() throws Exception {
        SessionFactory sessionFactory = HibUtils.getSessionFactory();
        long ITEM_ID = storeItemAndBids(sessionFactory.getCurrentSession());
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        ItemBidSummary itemBidSummary = session.find(ItemBidSummary.class, ITEM_ID);
        Assertions.assertEquals(itemBidSummary.getName(), "AUCTION: Some item");
        Item item = session.find(Item.class, ITEM_ID);
        item.setName("New name");
        session.getTransaction().commit();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("select ibs from ItemBidSummary ibs where ibs.itemid = :id");
        itemBidSummary = (ItemBidSummary) query.setParameter("id", ITEM_ID).getSingleResult();
        Assertions.assertEquals(itemBidSummary.getName(), "AUCTION: New name");
        session.getTransaction().commit();
    }

    public Long storeItemAndBids(Session session) throws Exception {
        session.beginTransaction();
        Item item = new Item();
        item.setName("Some item");
        item.setDescription("This is some description.");
        session.save(item);
        for (int i = 1; i <= 3; i++) {
            Bid bid = new Bid();
            bid.setAmount(new BigDecimal(10 + i));
            bid.setItem(item);
            session.save(bid);
        }
        session.getTransaction().commit();
        return item.getId();
    }

}
