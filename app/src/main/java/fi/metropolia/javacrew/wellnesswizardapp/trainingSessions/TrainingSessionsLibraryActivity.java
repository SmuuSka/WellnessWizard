package fi.metropolia.javacrew.wellnesswizardapp.trainingSessions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationBarView;

import fi.metropolia.javacrew.wellnesswizardapp.MainActivity;
import fi.metropolia.javacrew.wellnesswizardapp.R;
import fi.metropolia.javacrew.wellnesswizardapp.recipe.Recipe;
import fi.metropolia.javacrew.wellnesswizardapp.recipe.RecipeDetailActivity;
import fi.metropolia.javacrew.wellnesswizardapp.recipe.RecipeInfoHolder;
import fi.metropolia.javacrew.wellnesswizardapp.recipe.RecipeLibraryActivity;
import fi.metropolia.javacrew.wellnesswizardapp.stepCounter.StepCounterActivity;

/**
 * @author turovaarti
 */
public class TrainingSessionsLibraryActivity extends AppCompatActivity {

    public static final String EXTRA_TRAINING = "fi.metropolia.javacrew.wellnesswizardapp.MESSAGE";

    NavigationBarView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_sessions_library);

        ListView listViewRecipes = findViewById(R.id.listView_trainingSessions);

        listViewRecipes.setAdapter(new ArrayAdapter<TrainingSession>(this, R.layout.training_item, TrainingSessionHolder.getInstance().getTraining()));

        listViewRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent nextActivity = new Intent(TrainingSessionsLibraryActivity.this, TrainingDetailActivity.class);
                nextActivity.putExtra(EXTRA_TRAINING, i);
                startActivity(nextActivity);
            }


        });

        bottomNav = findViewById(R.id.bottomNavID);
        bottomNav.getMenu().getItem(1).setChecked(true);

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()){
                    case R.id.home:
                        intent = new Intent(TrainingSessionsLibraryActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.receipt:
                        intent = new Intent(TrainingSessionsLibraryActivity.this, RecipeLibraryActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }

        });

    }


}