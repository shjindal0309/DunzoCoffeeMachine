package org.shubham;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.shubham.model.BeverageRepository;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredients {
    private Integer hotWater;
    private Integer hotMilk;
    private Integer sugarSyrup;
    private Integer gingerSyrup;
    private Integer teaLeavesSyrup;
    private Integer greenMixture;
    private BeverageRepository beverageRepository;

    public Ingredients(BeverageRepository beverageRepository) {
        this.beverageRepository = beverageRepository;
        initialize();
    }

    private void initialize() {
        hotWater = beverageRepository.getHotWater();
        hotMilk = beverageRepository.getHotMilk();
        sugarSyrup = beverageRepository.getSugarSyrup();
        gingerSyrup = beverageRepository.getGingerSyrup();
        teaLeavesSyrup = beverageRepository.getTeaLeavesSyrup();
        greenMixture = beverageRepository.getGreenMixture();
    }
}
