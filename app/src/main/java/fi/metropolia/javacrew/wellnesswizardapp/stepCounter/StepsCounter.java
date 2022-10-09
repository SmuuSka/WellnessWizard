package fi.metropolia.javacrew.wellnesswizardapp.stepCounter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import fi.metropolia.javacrew.wellnesswizardapp.Henkilo;
import fi.metropolia.javacrew.wellnesswizardapp.MainActivity;

/**
 * @author Turo Vaarti
 * @class This class contains functionalitys for counting steps by STEP_COUNTER sensor.
 *        class is singleton, so data is accessible from anywhere.
 *        Sensor activation and call is made in MainActivity.
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

    /**
     * @function contains main functionality for counting Steps
     * @param sensorEvent sets value to currentStep
     */
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

        Henkilo.getInstance().setSteps(currentSteps);

        //}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public float getSteps() {

        return currentSteps;
    }

    /**
     *
     * @return currentStepsAmount
     */
    public float resetSteps() {
        isFirstEventOfDay = true;
        return currentSteps;
    }

    /**
     *
     * @param compensationSteps sets value for this param that is needed for counting energy usage of user.
     */
    public  void  setSteps(float compensationSteps){

        compensationStepsAmount = compensationSteps;

    }

    /**
     *
     * @return compensationSteps value for later use.
     */
    public float GetCompensationStepAmount(){
        return compensationStepsAmount;
    }


}
