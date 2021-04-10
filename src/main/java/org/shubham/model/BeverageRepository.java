package org.shubham.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * This will store config related overall initial contents of each ingredient
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BeverageRepository {

    @JsonProperty("hot_water")
    private Integer hotWater = 0;

    @JsonProperty("hot_milk")
    private Integer hotMilk = 0;

    @JsonProperty("sugar_syrup")
    private Integer sugarSyrup = 0;

    @JsonProperty("ginger_syrup")
    private Integer gingerSyrup = 0;

    @JsonProperty("tea_leaves_syrup")
    private Integer teaLeavesSyrup = 0;

    @JsonProperty("green_mixture")
    private Integer greenMixture = 0;

}
