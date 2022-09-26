package fi.metropolia.javacrew.wellnesswizardapp.stepCounter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class TestCounter implements SensorEventListener {
    private static TestCounter instance;

    public static TestCounter getInstance() {
        if (instance == null)
            instance = new TestCounter();
        return instance;
    }

    private TestCounter() {
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
