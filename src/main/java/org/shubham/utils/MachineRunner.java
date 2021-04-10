package org.shubham.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.shubham.Ingredients;
import org.shubham.Outlet;
import org.shubham.model.BeverageMaker;
import org.shubham.model.BeverageRepository;
import org.shubham.model.BeverageTask;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class MachineRunner {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static List<BeverageRepository> beverages = new ArrayList<>();

    // this will read json input from file and convert it to beverageMaker class
    public static BeverageMaker getBeverageMaker(File file) throws IOException {
        BeverageMaker beverageMaker = objectMapper.readValue(file, BeverageMaker.class);
        return beverageMaker;
    }

    // this will return all the beverages we need to make
    public static List<BeverageTask> getBeverageTasks(Map<String, Object> beverageObjects) {
        List<BeverageTask> beverageTasks = new ArrayList<>();

        for(Map.Entry<String,Object> mapE : beverageObjects.entrySet()) {
            BeverageTask beverageTask = objectMapper.convertValue(mapE.getValue(), BeverageTask.class);
            beverageTask.setName(mapE.getKey());
            beverageTasks.add(beverageTask);
        }
        return beverageTasks;
    }

    // run machine will create threadPool and then run the given beverageTasks on that threadPool.
    public static Set<String> runMachine(BeverageMaker beverageMaker, List<BeverageTask> beverageTasks, Ingredients ingredients) {
        ExecutorService executorService = Executors.newFixedThreadPool(beverageMaker.getMachine().getOutletStores().getCount()+1);

        List<Future<String>> outletFutures = new ArrayList<>();

        for(BeverageTask beverageTask : beverageTasks) {
            Callable<String> outletCallable = new Outlet(ingredients, beverageTask);
            Future<String> future = executorService.submit(outletCallable);
            outletFutures.add(future);
        }
        Set<String> beverageMessages = getBeverageMessages(outletFutures);

        executorService.shutdown();
        return beverageMessages;
    }

    // this will fill initial total quantity of each ingredient in Ingredient class
    public static Ingredients fillIngredients(BeverageRepository beverageRepository) {
        return new Ingredients(beverageRepository);
    }


    // this will fill and return output of each beverageTaskThread in beverageMessages set
    public static Set<String> getBeverageMessages(List<Future<String>> outletFutures) {
        Set<String> beverageMessages = new HashSet<>();

        Integer taskCount=outletFutures.size();
        while(beverageMessages.size() < taskCount) {
            outletFutures.forEach(outletFuture -> {
                if(outletFuture.isDone()) {
                    try {
                        beverageMessages.add(outletFuture.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return beverageMessages;
    }

}
