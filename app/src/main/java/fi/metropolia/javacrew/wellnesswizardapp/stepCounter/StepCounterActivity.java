package fi.metropolia.javacrew.wellnesswizardapp.stepCounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

public class StepCounterActivity extends AppCompatActivity {

    public static final StepCounterActivity stepInstance = new StepCounterActivity();

    public static StepCounterActivity getInstance(){
        return stepInstance;
    }

    private SensorManager sensorManager;
    private  boolean moving = false;
    private  float totalSteps = 0f;
    //private float previousSteps = 0f;
    //private Sensor stepSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(fi.metropolia.javacrew.wellnesswizardapp.R.layout.activity_step_counter);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            moving = true;
        }else {
            System.out.println("KAIKKI PASKANA!");
            moving = false;
        }

        totalSteps = 1f;


    }

    public void StartCounting(){



        //totalSteps = 2f;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            Sensor stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            StepsCounter.getInstance().setStepSensor(stepSensor);
            sensorManager.registerListener(StepsCounter.getInstance(), stepSensor, SensorManager.SENSOR_DELAY_UI);
        }

    }


}