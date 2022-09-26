package fi.metropolia.javacrew.wellnesswizardapp.recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeInfoHolder {

    private  static final RecipeInfoHolder instance = new RecipeInfoHolder();

    private List<Recipe> recipes = new ArrayList<>();

    public static RecipeInfoHolder getInstance(){
        return instance;
    }

    private  RecipeInfoHolder(){
        recipes.add(new Recipe("Bolognese", "200g jauheliha \n 1 sipuli \n 1 valkosipuli \n 1dl valkoviini \n 1tlk tomaattimurska \n \n bvlvbhfjlkavbhfjavbhfsalvbhsfalvbhafslbvhsfalbvhsfajlvbshfajlbvashjlvbashjlvbhsajlbvhajl", 345));
        recipes.add(new Recipe("Lihapullat", "jotain jotain jotain", 3000));

    }
    public List<Recipe> getResipes(){
        return recipes;
    }
}
