package fi.metropolia.javacrew.wellnesswizardapp.recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author turovaarti
 */
public class RecipeInfoHolder {

    private  static final RecipeInfoHolder instance = new RecipeInfoHolder();
    private int caloriesIntakeAmount;

    private List<Recipe> recipes = new ArrayList<>();

    public static RecipeInfoHolder getInstance(){
        return instance;
    }

    private  RecipeInfoHolder(){

        recipes.add(new Recipe("Aamupuuro", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Bolognese", "200g jauheliha \n 1 sipuli \n 1 valkosipuli \n 1dl valkoviini \n 1tlk tomaattimurska \n \n bvlvbhfjlkavbhfjavbhfsalvbhsfalvbhafslbvhsfalbvhsfajlvbshfajlbvashjlvbashjlvbhsajlbvhajl", 345));
        recipes.add(new Recipe("Curry Kanaa", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Donitsi", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Eggs Benedict", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Fetapiiras", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Gratinoitu kukkakaali", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Hernekeitto", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Inkiväärikalaa", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Juustokeitto", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Kalakeitto", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Lihapullakastike", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Melanzane", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Nokkosletut", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Omeletti", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Paistetut silakat", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Quiche Lorainne", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Ragu", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Salmiakkijäätelö", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Taatelikakku", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Uunilohi", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Vispipuuro", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Xante päärynät", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Yrttimarinoitua kanaa", "jotain jotain jotain", 3000));
        recipes.add(new Recipe("Zuccini Fritters", "jotain jotain jotain", 3000));


    }
    public List<Recipe> getResipes(){
        return recipes;
    }

    public void SetIntakeCaloriesAmount(int calories){
        caloriesIntakeAmount = calories;
    }
    public int GetIntakeCaloriesAmount(){
        return caloriesIntakeAmount;
    }
}
