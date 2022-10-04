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
        trainings.add(new TrainingSession("Nyrkkeily", 0));


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
