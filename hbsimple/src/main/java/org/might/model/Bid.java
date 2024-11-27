package org.might.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Bid implements Serializable {
    protected BigDecimal amount;
    protected Date createdOn;
    protected Item item;

    public Bid(Item item) {
        this.item = item;
        item.getBids().add(this); // Bidirectional
    }

}