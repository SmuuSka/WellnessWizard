package fi.metropolia.javacrew.wellnesswizardapp.stepCounter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import fi.metropolia.javacrew.wellnesswizardapp.MainActivity;

/**
 * @author Turo Vaarti
 *
 */
public class StepsCounter implements SensorEventListener {
    private static StepsCounter instance;

    private float steps;
    private float currentSteps;
    private float startSteps;
    private  boolean isFirstEventOfDay;

    private float compensationStepsAmount;

    private Sensor stepSensor;

    MainActivity mainActivity;

    public static StepsCounter getInstance() {
        if (instance == null)
            instance = new StepsCounter();
        return instance;
    }

    private StepsCounter() {

        steps = 0;
        isFirstEventOfDay = true;

    }



    public void setStepSensor(Sensor stepSensor) {
        this.stepSensor = stepSensor;

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //if (sensorEvent.sensor == stepSensor) {
            System.out.println(Float.toString(steps));
            steps = sensorEvent.values[0];
            if(isFirstEventOfDay){
                startSteps = steps;
                isFirstEventOfDay = false;
            }
            currentSteps = sensorEvent.values[0] - startSteps;

        //}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public float getSteps() {

        return currentSteps;
    }

    public float resetSteps() {
        //steps = 0;
        //currentSteps = steps - steps;
        isFirstEventOfDay = true;
        return currentSteps;
    }
    public  void  setSteps(float compensationSteps){

        compensationStepsAmount = compensationSteps;

    }

    public float GetCompensationStepAmount(){
        return compensationStepsAmount;
    }


}
