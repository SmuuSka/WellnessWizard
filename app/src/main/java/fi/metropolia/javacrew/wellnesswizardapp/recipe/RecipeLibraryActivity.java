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
import fi.metropolia.javacrew.wellnesswizardapp.stepCounter.StepCounterActivity;
import fi.metropolia.javacrew.wellnesswizardapp.stepCounter.TestCounter;

public class RecipeLibraryActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "fi.metropolia.javacrew.wellnesswizardapp.MESSAGE";

    //stepcounterille

    private TextView stepsTextView;
    private  boolean moving = false;
    private SensorManager sensorManager;
    private Sensor stepSensor;

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_library);



        ListView listViewRecipes = findViewById(R.id.listView_Recipes);
        listViewRecipes.setAdapter(new ArrayAdapter<Recipe>(this, R.layout.recipe_item, RecipeInfoHolder.getInstance().getResipes()));

        stepsTextView = findViewById(R.id.textView_StepCounterAmount);
        float steps = TestCounter.getInstance().getSteps();

        stepsTextView.setText(Float.toString(steps));


        listViewRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent nextActivity = new Intent(RecipeLibraryActivity.this, RecipeDetailActivity.class);
                nextActivity.putExtra(EXTRA_RECIPE, i);
                startActivity(nextActivity);


            }
        });

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACTIVITY_RECOGNITION) ==
                PackageManager.PERMISSION_DENIED) {
            requestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION);
        }


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            moving = true;
            System.out.println("SENSORI LÖYDETTY!!");
        }else {
            System.out.println("KAIKKI PASKANA!");
            moving = false;
        }

    }

    private List<Recipe> recipes(){
        List<Recipe> recipes = new ArrayList<>();



        return recipes;

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(stepSensor != null){
            TestCounter.getInstance().setStepSensor(stepSensor);
            sensorManager.registerListener(TestCounter.getInstance(), stepSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }

    }

    public void showSteps(View view) {

        float currentSteps = TestCounter.getInstance().getSteps();
        stepsTextView = findViewById(R.id.textView_StepCounterAmount);
        stepsTextView.setText(Float.toString(currentSteps));

    }

    public void resetSteps(View view) {
        //float currentSteps = StepCounterActivity.getInstance().resetSteps();
        //StepCounterActivity.getInstance().StartCounting();
        stepsTextView = findViewById(R.id.textView_StepCounterAmount);
        stepsTextView.setText(Float.toString(TestCounter.getInstance().resetSteps()));
    }
}