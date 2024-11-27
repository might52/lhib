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

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Getter
@Setter
@Entity
public class Address implements Serializable {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    protected Long id;

    protected String street;
    protected String city;
    protected String zipcode;
}
