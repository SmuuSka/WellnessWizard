package fi.metropolia.javacrew.wellnesswizardapp.stepCounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.widget.Toast;


import fi.metropolia.javacrew.wellnesswizardapp.R;

public class StepCounterActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private  boolean moving = false;
    private  float totalSteps = 0f;
    private float previousSteps = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(fi.metropolia.javacrew.wellnesswizardapp.R.layout.activity_step_counter);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        moving = true;
        Sensor stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if(stepSensor == null){
            Toast.makeText(this, "No Sensor Detected in This Device!", Toast.LENGTH_SHORT).show();
        }else {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(moving){
            totalSteps = sensorEvent.values[0];
            float currentSteps = totalSteps - previousSteps;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}