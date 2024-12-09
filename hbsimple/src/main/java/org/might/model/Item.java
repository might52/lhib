package org.might.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;
import org.might.Constants;
import org.might.model.converter.MonetaryAmountConverter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @Column(name = "IMPERIALWEIGHT")
    @ColumnTransformer(
            read = "IMPERIALWEIGHT / 2.20462",
            write = "? * 2.20462"
    )
    protected double metricWeight;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    protected byte[] image;
    @Type(type = "yes_no")
    private Boolean verified;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false)
    @Generated(GenerationTime.ALWAYS)
    protected Date lastModified;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuctionType auctionType;

    @Column(insertable = false)
    @ColumnDefault("1.00")
    @Generated(GenerationTime.INSERT)
    private BigDecimal initialPrice;

    @NotNull
    @Convert(converter = MonetaryAmountConverter.class)
    @Column(name = "PRICE", length = 63)
    private MonetaryAmount buyNowPrice;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public MonetaryAmount getBuyNowPrice() {
        return buyNowPrice;
    }

    public void setBuyNowPrice(MonetaryAmount buyNowPrice) {
        this.buyNowPrice = buyNowPrice;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
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

    public double getMetricWeight() {
        return metricWeight;
    }

    public void setMetricWeight(double metricWeight) {
        this.metricWeight = metricWeight;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
