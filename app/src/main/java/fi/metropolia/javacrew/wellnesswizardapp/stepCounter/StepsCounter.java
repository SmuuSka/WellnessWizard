package fi.metropolia.javacrew.wellnesswizardapp.stepCounter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class StepsCounter implements SensorEventListener {
    private static StepsCounter instance;

    public static StepsCounter getInstance() {
        if (instance == null)
            instance = new StepsCounter();
        return instance;
    }

    private StepsCounter() {
    }

    private float steps = 0;

    private Sensor stepSensor;

    public void setStepSensor(Sensor stepSensor) {
        this.stepSensor = stepSensor;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //if (sensorEvent.sensor == stepSensor) {
            System.out.println(Float.toString(steps));
            steps = sensorEvent.values[0];
        //}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public float getSteps() {
        //steps = steps + 1;
        return steps;
    }

    public float resetSteps() {
        steps = 0;
        return steps;
    }


}
