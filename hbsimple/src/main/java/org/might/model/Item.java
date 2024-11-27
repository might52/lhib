package org.might.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Item implements Serializable {
    protected String name;
    protected String description;
    protected Date createdOn;
    protected Boolean verified;
    protected AuctionType auctionType;
    protected BigDecimal initialPrice;
    protected Date auctionStartDate;
    protected Date auctionEndDate;
    @Transient
    protected Set<Bid> bids = new HashSet<Bid>();

    public void addBid(Bid bid) {
        if (bids == null) {
            throw new NullPointerException("Can't add null bids");
        }
        if (bid.getItem() != null) {
            throw new IllegalArgumentException("Bid is already assigned to the item");
        }
        getBids().add(bid);
        bid.setItem(this);
    }
}
