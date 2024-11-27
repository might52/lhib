package org.might.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.might.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Item implements Serializable {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    protected Long id;

    @NotNull
    @Size(min = 1, max = 255, message = "Name is required, maximum 255 characters.")
    protected String name;
    protected String description;
    protected Date createdOn;
    protected Boolean verified;
    protected AuctionType auctionType;
    protected BigDecimal initialPrice;
    protected Date auctionStart;
    @Future
    protected Date auctionEnd;
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
