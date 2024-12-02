package org.might.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.might.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Item implements Serializable {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255, message = "Name is required, maximum 255 characters.")
    @Column(name = "ITEM_NAME") // Mappings are still expected here!
    private String name;
    private String description;
    private Date createdOn;
    private Boolean verified;
    private AuctionType auctionType;
    private BigDecimal initialPrice;
    private Date auctionStart;
    @Future
    private Date auctionEnd;

    @Transient
    private Set<Bid> bids = new HashSet<Bid>();

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
