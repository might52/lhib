package org.might.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.might.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Bid implements Serializable {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    protected Long id;

    protected BigDecimal amount;
    protected Date createdOn;
    protected Item item;

    public Bid(Item item) {
        this.item = item;
        item.getBids().add(this); // Bidirectional
    }

}
