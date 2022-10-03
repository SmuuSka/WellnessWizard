package fi.metropolia.javacrew.wellnesswizardapp.recipe;

/**
 * @author turovaarti
 */
public class Recipe {

    private  String nameOfDish;
    private  String ingredientsList;
    private int caloryAmount;

    public  Recipe(String name, String ingredients, int calories){

        this.nameOfDish = name;
        this.ingredientsList = ingredients;
        this.caloryAmount = calories;

    }

    public String getNameOfDish() {
        return nameOfDish;
    }

    public String getIngredientsList() {
        return ingredientsList;
    }

    public int getCaloryAmount() {
        return caloryAmount;
    }

    public String toString(){
        return this.nameOfDish;
    }
}
