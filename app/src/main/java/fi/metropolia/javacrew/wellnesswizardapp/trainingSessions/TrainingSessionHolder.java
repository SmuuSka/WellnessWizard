package fi.metropolia.javacrew.wellnesswizardapp.trainingSessions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author turovaarti
 */
public class TrainingSessionHolder {

    private static final TrainingSessionHolder trainingInstance = new TrainingSessionHolder();

    private List<TrainingSession> trainings = new ArrayList();
    private float trainingDistanceAmount;

    public static TrainingSessionHolder getInstance(){
        return trainingInstance;
    }

    private TrainingSessionHolder(){

        trainings.add(new TrainingSession("Juoksu", 567));
        trainings.add(new TrainingSession("Kävely", 56007));

    }

    public List<TrainingSession> getTraining(){

        return trainings;
    }

    public  void SetTrainingDistance(float distance){
        trainingDistanceAmount = distance;
    }

    public  float GetTrainingDistance(){
        return trainingDistanceAmount;
    }

}
