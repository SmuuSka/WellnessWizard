package fi.metropolia.javacrew.wellnesswizardapp.recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author turovaarti
 * @class is singleton, contains all recipe data that is needed to count users energyIntake in MainActivity
 */
public class RecipeInfoHolder {

    private  static final RecipeInfoHolder instance = new RecipeInfoHolder();

    private List<Recipe> recipes = new ArrayList<>();

    public static RecipeInfoHolder getInstance(){
        return instance;
    }

    private  RecipeInfoHolder(){

        recipes.add(new Recipe("Aamupuuro", "50g kaurapuuro \n 200g vesi \n 2,5g suola \n \n Kiehauta vesi. Lisää suola ja kaurapuuro hiutaleet. Keitä 5 minuuttia ja anna hautua kannen alla 15 minuuttia. \n SYÖ", 120));
        recipes.add(new Recipe("Bolognese", "200g jauheliha \n 1 sipuli \n 1 valkosipuli \n 1dl valkoviini \n 1tlk tomaattimurska \n suola, pippuri \n \n Paista jauheliha. Pilko sipulit ja lisää pannulle. Lisää valkoviini ja tomaattimurska. Hauduta noin 1 tunti. Mausta ja nauti esimerkiksi keitetyn pastan kanssa.", 150));
        recipes.add(new Recipe("Curry Kanaa", "150g kananFilettä \n 1 pieni sipuli \n 1 valkosipulin kynsi \n 1dl kermaa \n suola, pippuri, curryjauhe \n \n Pilko kana ja paista pannulla. pilko sipulit ja lisää pannulle. Lisää kerma ja mausteet. Hauduta matalalla lämmöllä Tarjoile esimerkiksi riisin kanssa.", 399));
        recipes.add(new Recipe("Donitsi (täytetty)", "Nauti esimerkiksi kahvikupin kanssa", 415));
        recipes.add(new Recipe("Eggs Benedict", "1 muna \n 1 siivu kinkkua \n 1 siivu leipää \n 50g valmista hollandaise kastiketta \n \n Valmista uppomuna reilussa vedessä. Paahda leipä rapeaksi. Lisää leivälle kinkkusiivu ja uppomuna. lämmitä kastike ja annostele leivän päälle. Mausta tarvittaessa suolalla ja pippurilla.", 1190));
        recipes.add(new Recipe("Fetapiiras", "jotain jotain jotain", 302));
        recipes.add(new Recipe("Gratinoitu kukkakaali", "jotain jotain jotain", 200));
        recipes.add(new Recipe("Hernekeitto", "100g kuivattuja herneitä \n 50g savukinkkua \n vesi \n suolaa, pippuria, meiramia \n \n Liota herneitä vedessä yön yli. Kaada vesi pois ja lisää tuoretta vettä sen verran että herneet peittyvät hyvin. Hauduta matalalla lämmöllä useampi tunti. Lisää savukinkku sopivina paloina ja mausta.",  61));
        recipes.add(new Recipe("Inkiväärikalaa", "jotain jotain jotain", 669));
        recipes.add(new Recipe("Juustokeitto", "jotain jotain jotain", 212));
        recipes.add(new Recipe("Kalakeitto", "jotain jotain jotain", 404));
        recipes.add(new Recipe("Lihapullakastike", "jotain jotain jotain", 256));
        recipes.add(new Recipe("Melanzane", "jotain jotain jotain", 380));
        recipes.add(new Recipe("Nokkosletut", "jotain jotain jotain", 136));
        recipes.add(new Recipe("Omeletti", "jotain jotain jotain", 251));
        recipes.add(new Recipe("Paistetut silakat", "jotain jotain jotain", 194));
        recipes.add(new Recipe("Quiche Lorainne", "jotain jotain jotain", 512));
        recipes.add(new Recipe("Ragu", "jotain jotain jotain", 253));
        recipes.add(new Recipe("Salmiakkijäätelö", "jotain jotain jotain", 466));
        recipes.add(new Recipe("Taatelikakku", "jotain jotain jotain", 221));
        recipes.add(new Recipe("Uunilohi", "jotain jotain jotain", 227));
        recipes.add(new Recipe("Vispipuuro", "jotain jotain jotain", 107));
        recipes.add(new Recipe("Xante päärynät", "jotain jotain jotain", 197));
        recipes.add(new Recipe("Yrttimarinoitua kanaa", "jotain jotain jotain", 296));
        recipes.add(new Recipe("Zuccini Fritters", "jotain jotain jotain", 86));


    }
    public List<Recipe> getResipes(){
        return recipes;
    }

}
