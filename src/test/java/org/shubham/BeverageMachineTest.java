package org.shubham;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.shubham.model.BeverageMaker;
import org.shubham.model.BeverageRepository;
import org.shubham.model.BeverageTask;
import org.shubham.utils.MachineRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BeverageMachineTest {

    MachineRunner machineRunner;
    BeverageMaker beverageMaker;
    List<BeverageRepository> beverages;

    @Before
    public void setup() {
        machineRunner = new MachineRunner();
        beverageMaker = new BeverageMaker();
        beverages = new ArrayList<>();
    }

    @Test
    public void testJsonReaderBeverageTasks() throws IOException {

        // Read Json file for machine configs and read
        File file = new File("src/main/resources/machineConfig.json");
        beverageMaker = MachineRunner.getBeverageMaker(file);
        Assert.assertNotNull(beverageMaker);

        // get list of beverages which we need to make
        List<BeverageTask> beverageTasks = MachineRunner.getBeverageTasks(beverageMaker.getMachine().getBeverages());
        Assert.assertNotNull(beverageTasks);

        // fill initial overall ingredients quantities in Ingredient class
        Ingredients ingredients = MachineRunner.fillIngredients(beverageMaker.getMachine().getBeverageRepository());
        Assert.assertNotNull(ingredients);

        // get messages which will state whether beverages can be made or not.
        Set<String> beverageMessages = MachineRunner.runMachine(beverageMaker, beverageTasks, ingredients);
        Assert.assertNotNull(beverageMessages);
        for(String beverageMessage : beverageMessages) {
            System.out.println(beverageMessage);
        }
    }
}
