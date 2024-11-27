package org.might.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.might.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    protected Long id;

}
