package org.shubham.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/*
 * This will store configs related to outlets
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutletStores {

    @JsonProperty("count_n")
    Integer count;
}
