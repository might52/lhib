package org.might.model;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@Embeddable
public class Address implements Serializable {

    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @AttributeOverrides(
            @AttributeOverride(
                    name = "name",
                    column = @Column(name = "CITY", nullable = false)
            )
    )
    private City city;

    public @NotNull City getCity() {
        return city;
    }

    public void setCity(@NotNull City city) {
        this.city = city;
    }
}
