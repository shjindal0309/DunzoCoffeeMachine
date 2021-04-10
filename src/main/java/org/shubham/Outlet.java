package org.shubham;

import org.shubham.model.BeverageTask;

import java.util.*;
import java.util.concurrent.Callable;

public class Outlet implements Callable {

    private Ingredients ingredients;
    private BeverageTask beverageTask;
    private Map<String,Integer> beverageItems;

    public Outlet (Ingredients ingredients, BeverageTask beverageTask) {
        this.ingredients = ingredients;
        this.beverageTask = beverageTask;
        initializeBeverageItems(beverageTask);
    }

    public String call() {
        return makeBeverage();
    }

    /* if all ingredients required to make a beverage are not present in sufficient quantity
    * it will return a message saying beverage can't be made, else it will make beverage, consumers
    * ingredients and decrease the overall corresponding beverage quantities and return the message
    */
    public String makeBeverage() {
        StringBuilder beverageMessageBuilder = new StringBuilder(beverageTask.getName());
        List<String> notAvailableIngredients;

        synchronized (ingredients) {
            notAvailableIngredients = getNotAvailableIngredients();
            if (!notAvailableIngredients.isEmpty()) {
                beverageMessageBuilder.append(" cannot be prepared because ");
                notAvailableIngredients.stream().forEach(notAvailableIngredient -> {
                    beverageMessageBuilder.append(notAvailableIngredient + " is not sufficient ");
                    beverageMessageBuilder.append("and ");
                });
                beverageMessageBuilder.delete(beverageMessageBuilder.length()-4, beverageMessageBuilder.length());
            } else {
                updateIngredients();
                beverageMessageBuilder.append(" is prepared");
            }
        }
        return beverageMessageBuilder.toString();
    }

    // map of initial overall ingredient quantity for each ingredient
    private void initializeBeverageItems(BeverageTask beverageTask) {
        beverageItems = new HashMap<>();
        beverageItems.put("hotWater", Objects.nonNull(beverageTask.getHotWater()) ? beverageTask.getHotWater() : 0);
        beverageItems.put("hotMilk", Objects.nonNull(beverageTask.getHotMilk()) ? beverageTask.getHotMilk() : 0);
        beverageItems.put("greenMixture", Objects.nonNull(beverageTask.getGreenMixture()) ? beverageTask.getGreenMixture() : 0);
        beverageItems.put("gingerSyrup", Objects.nonNull(beverageTask.getGingerSyrup()) ? beverageTask.getGingerSyrup() : 0);
        beverageItems.put("sugarSyrup", Objects.nonNull(beverageTask.getSugarSyrup()) ? beverageTask.getSugarSyrup() : 0);
        beverageItems.put("teaLeavesSyrup", Objects.nonNull(beverageTask.getTeaLeavesSyrup()) ? beverageTask.getTeaLeavesSyrup() : 0);
    }


    private void updateIngredients() {
        ingredients.setHotWater(ingredients.getHotWater() - beverageItems.get("hotWater"));
        ingredients.setHotMilk(ingredients.getHotMilk() - beverageItems.get("hotMilk"));
        ingredients.setGreenMixture(ingredients.getGreenMixture() - beverageItems.get("greenMixture"));
        ingredients.setGingerSyrup(ingredients.getGingerSyrup() - beverageItems.get("gingerSyrup"));
        ingredients.setSugarSyrup(ingredients.getSugarSyrup() - beverageItems.get("sugarSyrup"));
        ingredients.setTeaLeavesSyrup(ingredients.getTeaLeavesSyrup() - beverageItems.get("teaLeavesSyrup"));
    }

    // fetch all ingredients which are not in sufficient quantity for this beverage
    List<String> getNotAvailableIngredients() {
        List<String> notAvailableIngredients = new ArrayList<>();
        beverageItems.entrySet().stream().forEach(entry -> {
            String ingredientName = entry.getKey();
            if(ingredientName.equals("hotWater") && beverageItems.get(ingredientName) > ingredients.getHotWater()) {
                notAvailableIngredients.add("hot water");
            }
            if(ingredientName.equals("hotMilk") && beverageItems.get(ingredientName) > ingredients.getHotMilk()) {
                notAvailableIngredients.add("hot milk");
            }
            if(ingredientName.equals("greenMixture") && beverageItems.get(ingredientName) > ingredients.getGreenMixture()) {
                notAvailableIngredients.add("green mixture");
            }
            if(ingredientName.equals("gingerSyrup") && beverageItems.get(ingredientName) > ingredients.getGingerSyrup()) {
                notAvailableIngredients.add("ginger syrup");
            }
            if(ingredientName.equals("sugarSyrup") && beverageItems.get(ingredientName) > ingredients.getSugarSyrup()) {
                notAvailableIngredients.add("sugar syrup");
            }
            if(ingredientName.equals("teaLeavesSyrup") && beverageItems.get(ingredientName) > ingredients.getTeaLeavesSyrup()) {
                notAvailableIngredients.add("tea leaves syrup");
            }

        });
        return notAvailableIngredients;
    }
}
