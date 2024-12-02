package org.might.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.might.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@DynamicInsert
@DynamicUpdate
public class Category implements Serializable {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

}
