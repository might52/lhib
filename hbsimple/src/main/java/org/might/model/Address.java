package org.might.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Getter
@Setter
public class Address implements Serializable {
    protected String street;
    protected String city;
    protected String zipcode;
}
