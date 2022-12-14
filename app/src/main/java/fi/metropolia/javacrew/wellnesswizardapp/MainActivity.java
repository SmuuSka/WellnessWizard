package fi.metropolia.javacrew.wellnesswizardapp;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import fi.metropolia.javacrew.wellnesswizardapp.recipe.RecipeLibraryActivity;
import fi.metropolia.javacrew.wellnesswizardapp.stepCounter.StepsCounter;
import fi.metropolia.javacrew.wellnesswizardapp.trainingSessions.TrainingSessionsLibraryActivity;

/**
 * Main Activity represents application frontpage
 * and hold methods to show user progress in UI
 * @author Samu
 */

public class MainActivity extends AppCompatActivity {

    private float kcalBurnPerMeter, totalBurnedKilocalories;
    private NavigationBarView bottomNav;
    private TextView eatenKcalText, usernameTextView, eatenKcalSummaryTxt, burnKcalSummaryTxt, burnedKcalTextView, dailySteps;
    private ProgressBar burnKilocaloriesAmount, eatenKilocaloriesAmount;
    private SensorManager sensorManager;
    private Sensor stepSensor;
    private String basicText;
    private  boolean moving = false;
    private CountDownTimer timer;
    private Henkilo currentPerson;



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



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent serviceIntent = new Intent(this,ResetProgress.class);
        startService(serviceIntent);



        burnKilocaloriesAmount = findViewById(R.id.burnKilocalorieProgressBar);
        eatenKilocaloriesAmount = findViewById(R.id.eatenKilocaloriesProgressBar);

        eatenKcalText = findViewById(R.id.eatKcalNumberTxt);
        eatenKcalSummaryTxt = findViewById(R.id.kilocaloriesEatenSummaryText);

        burnedKcalTextView = findViewById(R.id.burnKcalNumberTxt);
        burnKcalSummaryTxt = findViewById(R.id.kilocaloriesBurnSummaryText);

        dailySteps = findViewById(R.id.dailyStepsTextView);
        basicText = "\n";
        basicText += getResources().getString(R.string.dailySteps);
        dailySteps.setText(basicText);

        currentPerson = Henkilo.getInstance();
        checkIfUserExists();
        timerStart();
        kcalBurnPerMeter = (float) (0.001 * (float) Henkilo.getInstance().getPaino());

        usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText("READY TO START NEW LIFE\n\n" + Henkilo.getInstance().getNimi());



        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACTIVITY_RECOGNITION) ==
                PackageManager.PERMISSION_DENIED) {
            requestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION);
        }

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            moving = true;
            System.out.println("Sensor found!!");
        }else {
            System.out.println("Sensor not found!");
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

    @Override
    protected void onResume() {
        super.onResume();
        if(stepSensor != null){
            StepsCounter.getInstance().setStepSensor(stepSensor);
            sensorManager.registerListener(StepsCounter.getInstance(), stepSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }

    }

    /**
     * This one is needed for showing dailySteps for user at real time.
     *
     */

    private void showDailySteps(){
        int dailyStepsToInt = (Math.round(Henkilo.getInstance().getSteps()));
        String dailyStepsToString = Integer.toString(dailyStepsToInt);
        dailySteps.setText(dailyStepsToString + basicText);
    }

    /**
     * compensationStepsToBurnedKcal method get step from user instance
     * convert steps to travelled meters and multiply it by avg kcal burn rate
     * @return burned kilocalories for UI-elements
     */

    private float stepsToBurnedKcal() {

        float currentSteps = Henkilo.getInstance().getSteps();
        float stepsToMeter = (currentSteps * 0.75f);
        float burnedKilocalories = (stepsToMeter * kcalBurnPerMeter);
        return burnedKilocalories;
    }

    /**
     * compensationStepsToBurnedKcal method get step from user instance
     * convert steps to travelled meters and multiply it by avg kcal burn rate
     * @return burned kilocalories for UI-elements
     */
    private float compensationStepsToBurnedKcal(){
        float currentSteps = Henkilo.getInstance().getCompensationSteps();
        float stepsToMeter = (currentSteps * 0.75f);
        float burnedKilocalories = (stepsToMeter * kcalBurnPerMeter);
        return burnedKilocalories;
    }

    /**
     * currentEatenKcal method get eaten kilocalories from user instance
     * and show current eaten kilocalories
     */

    private void currentEatenKcal(){

        if (Henkilo.getInstance().getSy??dytKalorit() == 0){
            eatenKcalText.setText(Henkilo.getInstance().getSy??dytKalorit() +" "+
                                         getResources().getString(R.string.kcal));
            eatenKilocaloriesAmount.setProgress(Henkilo.getInstance().getSy??dytKalorit());
        }else{
            eatenKcalText.setText(Henkilo.getInstance().getSy??dytKalorit() +" "+
                                         getResources().getString(R.string.kcal));
            eatenKilocaloriesAmount.setProgress(Henkilo.getInstance().getSy??dytKalorit());
        }


    }

    /**
     * timerStart method creating a new timer for updating main activity UI-elements
     */
    private void timerStart(){
        timer = new CountDownTimer(300000,10000) {

            public void onTick(long millisUntilFinished) {
                totalBurnedKilocalories = stepsToBurnedKcal() + compensationStepsToBurnedKcal();
                showDailySteps();
                burnKilocaloriesAmount.setProgress(Math.round(totalBurnedKilocalories));
                burnedKcalTextView.setText(Math.round(totalBurnedKilocalories) + " " + getResources().getString(R.string.kcal));
                //burnedKcalTextView.setText(Float.toString( totalBurnedKilocalories) +" "+
                 //                           getResources().getString(R.string.kcal));
                currentEatenKcal();
            }
            public void onFinish() {
            }
        }.start();
    }

    /**
     * checkIfUserExists method check if user exists
     * if not
     * @throws NullPointerException
     *else
     * set person to instance
     * set right text for the UI-elements
     *
     */
    private void checkIfUserExists(){
        try {
            if(currentPerson != null){
                Henkilo.setInstance(currentPerson);
                if(currentPerson.getSukupuoli().matches("Male")){
                    eatenKilocaloriesAmount.setMax(2000);
                    burnKilocaloriesAmount.setMax(6500);
                    eatenKcalSummaryTxt.setText(R.string.caloriesEatenMaleTxt);
                    burnKcalSummaryTxt.setText(R.string.caloriesBurnMaleTxt);
                }else{
                    eatenKilocaloriesAmount.setMax(2000);
                    burnKilocaloriesAmount.setMax(5500);
                    eatenKcalSummaryTxt.setText(R.string.caloriesEatenFemaleTxt);
                    burnKcalSummaryTxt.setText(R.string.caloriesBurnFemaleTxt);
                }

            }
        }catch (Exception e){
            throw new NullPointerException();
        }
    }

    /**
     * timerStop method stops the timer
     */
    private void timerStop(){
        timer.cancel();
    }

    /**
     * Back-button disabled in main activity
     */
    @Override
    public void onBackPressed() {

    }


    @Override
    protected void onPause() {
        super.onPause();
        timerStop();
        saveData(Henkilo.getInstance());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        timerStart();
    }

    /**
     * Defined in Henkilo-class
     *
     */
    private void saveData(Henkilo henkilo) {
        //Might be for another acivity.
        SharedPreferences prefPut = getSharedPreferences("Henkilo", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        Gson gson = new Gson();
        String json = gson.toJson(henkilo);
        prefEditor.putString("Henkilo", json);
        prefEditor.apply();
    }
}