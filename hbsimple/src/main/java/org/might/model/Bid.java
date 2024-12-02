package org.might.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.might.Constants;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Entity
@Immutable
@Data
public class Bid implements Serializable {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private BigDecimal amount;
    private Date createdOn;
    @ManyToOne(optional = false, fetch = FetchType.LAZY) // NOT NULL
    @JoinColumn(name = "ITEM_ID") // Actually the default name
    private Item item;

    public Bid(Item item) {
        this.item = item;
        item.getBids().add(this); // Bidirectional
    }

}
