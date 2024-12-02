package org.might.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.might.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
public class Address implements Serializable {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String street;
    private String city;
    private String zipcode;
}
