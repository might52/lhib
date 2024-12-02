package org.might.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Immutable
@Subselect(value =
        "select i.id as itemid, i.item_name as name, count(b.id) as numberofbids " +
                "from item i left outer join bid b on i.id = b.item_id group by i.id, i.item_name"
)
@Synchronize({"Item", "Bid"})
@NoArgsConstructor
public class ItemBidSummary {
    @Id
    private Long itemid;
    private String name;
    private long numberOfBids;
}
