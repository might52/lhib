@org.hibernate.annotations.GenericGenerator(
        name = "ID_GENERATOR",
        strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = "sequence_name",
                        value = "MIGHT_SEQUENCE"
                ),
                @org.hibernate.annotations.Parameter(
                        name = "initial_value",
                        value = "1000"
                )
        })
package org.might;