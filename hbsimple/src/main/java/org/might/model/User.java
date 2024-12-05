package org.might.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.might.Constants;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity(name = "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String username;

    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "BILLING_STEET")),
            @AttributeOverride(name = "city.country", column = @Column(name = "BILLING_COUNTRY")),
            @AttributeOverride(name = "city.zipcode", column = @Column(name = "BILLING_ZIPCODE")),
            @AttributeOverride(name = "city.name", column = @Column(name = "BILLING_CITY"))
    })
    private Address billingAddress;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address address) {
        this.homeAddress = address;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
}
