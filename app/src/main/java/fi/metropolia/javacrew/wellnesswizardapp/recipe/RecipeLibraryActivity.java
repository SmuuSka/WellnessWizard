package fi.metropolia.javacrew.wellnesswizardapp.recipe;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import fi.metropolia.javacrew.wellnesswizardapp.MainActivity;
import fi.metropolia.javacrew.wellnesswizardapp.R;
import fi.metropolia.javacrew.wellnesswizardapp.stepCounter.StepCounterActivity;
import fi.metropolia.javacrew.wellnesswizardapp.stepCounter.StepsCounter;
import fi.metropolia.javacrew.wellnesswizardapp.trainingSessions.TrainingSessionsLibraryActivity;

/**
 * @author turovaarti
 * @class this class creates listView from recipeLibrary to user.
 *        Items can be clicked to open single recipeView.
 */
public class RecipeLibraryActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "fi.metropolia.javacrew.wellnesswizardapp.MESSAGE";

    NavigationBarView bottomNav;

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
/**
 * Folowing is needed for bottom navicationbar to work.
 */
        bottomNav = findViewById(R.id.bottomNavID);
        bottomNav.getMenu().getItem(1).setChecked(true);

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()){
                    case R.id.home:
                        intent = new Intent(RecipeLibraryActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.exercise:
                        intent = new Intent(RecipeLibraryActivity.this, TrainingSessionsLibraryActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }

        });

    }

}