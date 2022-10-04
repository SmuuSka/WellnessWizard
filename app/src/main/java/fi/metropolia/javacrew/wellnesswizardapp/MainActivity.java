package fi.metropolia.javacrew.wellnesswizardapp;

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
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;

import fi.metropolia.javacrew.wellnesswizardapp.recipe.RecipeLibraryActivity;
import fi.metropolia.javacrew.wellnesswizardapp.stepCounter.StepsCounter;
import fi.metropolia.javacrew.wellnesswizardapp.trainingSessions.TrainingSessionsLibraryActivity;

public class MainActivity extends AppCompatActivity {

    private NavigationBarView bottomNav;
    private int kilocaloriesDaily = 2500;
    private TextView eatenKcalText, usernameTextView;
    private ProgressBar burnKilocaloriesAmount, eatenKilocaloriesAmount;
    private SensorManager sensorManager;
    private Sensor stepSensor;
    private  boolean moving = false;
    private TextView burnedKcalTextView;

    //60kg human burns 60kcal per kilometer
    private final float kcalBurnPerMeter = 0.06f;

    /**
     * This is needed for stepCounter to work. Asks user permission to use Sensors. turo
     */
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
        setContentView(R.layout.activity_main);

        burnKilocaloriesAmount = findViewById(R.id.burnKilocalorieProgressBar);
        eatenKilocaloriesAmount = findViewById(R.id.eatenKilocaloriesProgressBar);

        burnKilocaloriesAmount.setMax(2500);
        eatenKilocaloriesAmount.setMax(2500);


        usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(Henkilo.getInstance().getNimi());

        new CountDownTimer(300000,5000) {

            public void onTick(long millisUntilFinished) {

                currentBurnedKcal();
                currentEatenKcal();
            }

            public void onFinish() {

            }
        }.start();

        /**
         * Is needed for Sensor usage -> checks user permission status turo
         */
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


        bottomNav = findViewById(R.id.bottomNavID);
        bottomNav.getMenu().getItem(1).setChecked(true);

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()){
                    case R.id.exercise:
                        intent = new Intent(MainActivity.this, TrainingSessionsLibraryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.receipt:
                        intent = new Intent(MainActivity.this, RecipeLibraryActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }

        });
    }

    /**
     * Is needed for stepCounter. -> register sensor listener when returned to app. turo
     */
    @Override
    protected void onResume() {
        super.onResume();
        if(stepSensor != null){
            StepsCounter.getInstance().setStepSensor(stepSensor);
            sensorManager.registerListener(StepsCounter.getInstance(), stepSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }

    }

    /**
     * Remove this funcion before final build . turo
     * @param view
     */
    public void ResetAmount(View view) {

        //float currentSteps = StepCounterActivity.getInstance().resetSteps();
        //StepCounterActivity.getInstance().StartCounting();
    }

    /**
     * This one is needed for showing dailySteps for user at real time.
     * @param
     */
    public void currentBurnedKcal() {

        //float currentSteps = StepsCounter.getInstance().getSteps();
        int currentSteps = 10000;
        float meterTravelled = (currentSteps * 0.75f);
        float burnedKilocalories = (meterTravelled * kcalBurnPerMeter);
        burnedKcalTextView = findViewById(R.id.burnKcalNumberTxt);
        burnedKcalTextView.setText(Double.toString(burnedKilocalories) + " Kcal");
        int roundKilocalories = Math.round(burnedKilocalories);
        burnKilocaloriesAmount.setProgress(roundKilocalories);
    }

    public void currentEatenKcal(){
        eatenKcalText = findViewById(R.id.eatKcalNumberTxt);
        eatenKcalText.setText(Double.toString(1500) + " Kcal");
        eatenKilocaloriesAmount.setProgress(1500);
    }

    @Override
    public void onBackPressed() {

    }
}