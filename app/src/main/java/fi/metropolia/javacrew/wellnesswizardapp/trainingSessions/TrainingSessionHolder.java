package fi.metropolia.javacrew.wellnesswizardapp.trainingSessions;

import android.media.Image;

import java.util.ArrayList;
import java.util.List;

import fi.metropolia.javacrew.wellnesswizardapp.*;

/**
 * @author turovaarti
 * @class is singleton, contains selected training types that user can use.
 *        Every trainingSession view contains input
 */
public class TrainingSessionHolder {

    private static final TrainingSessionHolder trainingInstance = new TrainingSessionHolder();

    private List<TrainingSession> trainings = new ArrayList();
    private float trainingDistanceAmount;

    public static TrainingSessionHolder getInstance(){
        return trainingInstance;
    }

    private TrainingSessionHolder(){

        trainings.add(new TrainingSession("Juoksu", 0));
        trainings.add(new TrainingSession("Kävely", 0));
        trainings.add(new TrainingSession("Uinti", 0));
        trainings.add(new TrainingSession("Hiihto", 0));
        trainings.add(new TrainingSession("Maastojuoksu", 0));
        trainings.add(new TrainingSession("Pyöräily", 0));
        trainings.add(new TrainingSession("Maastopyöräily", 0));
        trainings.add(new TrainingSession("Maastojuoksu", 0));
        trainings.add(new TrainingSession("Sisäharjoittelu", 0));
        trainings.add(new TrainingSession("Ulkoharjoittelu", 0));

    }

    /**
     *
     * @return
     */
    public List<TrainingSession> getTraining(){

        return trainings;
    }

    /**
     *
     * @param distance sets trainingDistance value
     */
    public  void SetTrainingDistance(float distance){
        trainingDistanceAmount = distance;
    }

    /**
     *
     * @return returns trainingDistance value for later use in MainActivity calculations
     */
    public  float GetTrainingDistance(){
        return trainingDistanceAmount;
    }

}
