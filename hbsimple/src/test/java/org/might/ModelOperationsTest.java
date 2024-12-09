package org.might;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.might.model.AuctionType;
import org.might.model.Bid;
import org.might.model.Item;
import org.might.model.MonetaryAmount;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

;

@Slf4j
public class ModelOperationsTest {

    @Test
    public void linkBidAndItem() {
        Item anItem = new Item();
        Bid aBid = new Bid();

        anItem.getBids().add(aBid);
        aBid.setItem(anItem);

        assertEquals(anItem.getBids().size(), 1);
        assertTrue(anItem.getBids().contains(aBid));
        assertEquals(anItem, aBid.getItem());

        Bid secondBid = new Bid();
        anItem.addBid(secondBid);

        assertEquals(2, anItem.getBids().size());
        assertTrue(anItem.getBids().contains(secondBid));
        assertEquals(anItem, secondBid.getItem());
    }

    @Test
    public void validateItem() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionEnd(new Date());
        item.setAuctionType(AuctionType.HIGHEST_BID);
        item.setBuyNowPrice(new MonetaryAmount(new BigDecimal("100.00"), Currency.getInstance(Locale.getDefault())));

        Set<ConstraintViolation<Item>> violations = validator.validate(item);

        assertEquals(1, violations.size());

        ConstraintViolation<Item> violation = violations.iterator().next();
        String failedPropertyName =
                violation.getPropertyPath().iterator().next().getName();

        assertEquals(failedPropertyName, "auctionEnd");

        if (Locale.getDefault().getLanguage().equals("en"))
            assertEquals("must be a future date", violation.getMessage());
    }
}
