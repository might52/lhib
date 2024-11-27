package org.might.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class User implements Serializable {

    protected String username;

    public BigDecimal calcShippingCoasts(Address fromLocation) {
        return null;
    }
}
