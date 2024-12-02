package org.might.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.might.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Message {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String text;
}
