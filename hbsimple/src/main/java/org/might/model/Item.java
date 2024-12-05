package org.might.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.might.Constants;

import javax.persistence.Access;
import javax.persistence.AccessType;
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

@NoArgsConstructor
@Entity
public class Item implements Serializable {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255, message = "Name is required, maximum 255 characters.")
    @Access(AccessType.PROPERTY)
    @Column(name = "ITEM_NAME")
    private String name;
    @Formula("substr(DESCRIPTION, 1, 12) || '...'")
    private String shortDescription;
    @Formula("(select avg(b.AMOUNT) from BID b where b.ITEM_ID = ID)")
    private BigDecimal averageBidAmount;

    private String description;
    private Date createdOn;
    private Boolean verified;
    private AuctionType auctionType;
    private BigDecimal initialPrice;
    private Date auctionStart;
    @Future
    private Date auctionEnd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = !name.startsWith("AUCTION: ") ? "AUCTION: " + name : name;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public AuctionType getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(AuctionType auctionType) {
        this.auctionType = auctionType;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Date getAuctionStart() {
        return auctionStart;
    }

    public void setAuctionStart(Date auctionStart) {
        this.auctionStart = auctionStart;
    }

    public @Future Date getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(@Future Date auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public BigDecimal getAverageBidAmount() {
        return averageBidAmount;
    }

    public void setAverageBidAmount(BigDecimal averageBidAmount) {
        this.averageBidAmount = averageBidAmount;
    }

    public Set<Bid> getBids() {
        return bids;
    }

}
