package fi.metropolia.javacrew.wellnesswizardapp.trainingSessions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;

import java.util.Scanner;

import fi.metropolia.javacrew.wellnesswizardapp.MainActivity;
import fi.metropolia.javacrew.wellnesswizardapp.R;
import fi.metropolia.javacrew.wellnesswizardapp.recipe.Recipe;
import fi.metropolia.javacrew.wellnesswizardapp.recipe.RecipeInfoHolder;
import fi.metropolia.javacrew.wellnesswizardapp.recipe.RecipeLibraryActivity;
import fi.metropolia.javacrew.wellnesswizardapp.stepCounter.StepsCounter;

/**
 * @author turovaarti
 */
public class TrainingDetailActivity extends AppCompatActivity {

    private float trainingDistance;
    private NavigationBarView bottomNav;
    private EditText distanceAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(fi.metropolia.javacrew.wellnesswizardapp.R.layout.activity_training_detail);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(TrainingSessionsLibraryActivity.EXTRA_TRAINING, 0);

        TrainingSession trainingSession = TrainingSessionHolder.getInstance().getTraining().get(i);

        ((TextView) findViewById(R.id.textView_NameOfTraining)).setText(trainingSession.getName());
        ((TextView) findViewById(R.id.textView_TrainingCalorieAmount)).setText("Insert session distance");

/**
 * Folowing is needed for bottom navicationbar to work.
 */
        bottomNav = findViewById(R.id.bottomNavID);
        bottomNav.getMenu().getItem(1).setChecked(true);

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.exercise:
                        intent = new Intent(TrainingDetailActivity.this, TrainingSessionsLibraryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.receipt:
                        intent = new Intent(TrainingDetailActivity.this, RecipeLibraryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.home:
                        intent = new Intent(TrainingDetailActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }

        });

    }

    /**
     * @function Sends training session distanceAmount to TrainingSessionHolder where it can be fetch for later use.
     *           try/catch statement checks if input values are in right format.
     */
    public void SendDistance(View view) {
        try {
            trainingDistance = Float.parseFloat(((EditText) findViewById(R.id.editTextNumber_caloriesInput)).getText().toString());
            TrainingSessionHolder.getInstance().SetTrainingDistance(trainingDistance);
            float compensationSteps = trainingDistance * 2;
            StepsCounter.getInstance().setSteps(compensationSteps);
        } catch (NumberFormatException ex) {
            System.out.println("SYÖTE VÄÄRÄSSÄ FORMAATISSA!");
        }

    }
}