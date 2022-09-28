package fi.metropolia.javacrew.wellnesswizardapp.recipe;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fi.metropolia.javacrew.wellnesswizardapp.R;
import fi.metropolia.javacrew.wellnesswizardapp.stepCounter.StepsCounter;

public class RecipeLibraryActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "fi.metropolia.javacrew.wellnesswizardapp.MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_library);



        ListView listViewRecipes = findViewById(R.id.listView_Recipes);
        listViewRecipes.setAdapter(new ArrayAdapter<Recipe>(this, R.layout.recipe_item, RecipeInfoHolder.getInstance().getResipes()));




        listViewRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent nextActivity = new Intent(RecipeLibraryActivity.this, RecipeDetailActivity.class);
                nextActivity.putExtra(EXTRA_RECIPE, i);
                startActivity(nextActivity);


            }
        });

    }

    private List<Recipe> recipes(){
        List<Recipe> recipes = new ArrayList<>();



        return recipes;

    }

    public void showSteps(View view) {



    }

    public void resetSteps(View view) {

    }
}