package org.shubham.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/*
 * This will store configs for different machine part. Like outlet (for number of outlets),
 * beverageRepository for overall initial quantity of each ingredient and beverages for
 * configuration of each beverage which we need to make
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Machine {

    @JsonProperty("outlets")
    OutletStores outletStores;

    @JsonProperty("total_items_quantity")
    BeverageRepository beverageRepository;

    @JsonProperty("beverages")
    Map<String, Object> beverages;

}
