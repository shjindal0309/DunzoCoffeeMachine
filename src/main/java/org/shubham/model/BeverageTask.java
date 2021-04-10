package org.shubham.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * This will store configuration of each beverage which we need to make
 */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BeverageTask {

    private String name;

    @JsonProperty("hot_water")
    private Integer hotWater;

    @JsonProperty("hot_milk")
    private Integer hotMilk;

    @JsonProperty("sugar_syrup")
    private Integer sugarSyrup;

    @JsonProperty("ginger_syrup")
    private Integer gingerSyrup;

    @JsonProperty("tea_leaves_syrup")
    private Integer teaLeavesSyrup;

    @JsonProperty("green_mixture")
    private Integer greenMixture;
}