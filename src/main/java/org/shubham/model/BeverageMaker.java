package org.shubham.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* This will store configs for the overall machine
*/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BeverageMaker {

    @JsonProperty("machine")
    Machine machine;
}
