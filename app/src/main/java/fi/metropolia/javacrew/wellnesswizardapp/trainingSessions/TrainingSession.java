package fi.metropolia.javacrew.wellnesswizardapp.trainingSessions;

import android.media.Image;

/**
 * @author turovaarti
 * @class this class contains all needed data for trainingSessionLibraryActivity to work.
 */
public class TrainingSession {

    private String trainingName;
    private int caloriesPerTraining;
    private Image trainingImage;

    public TrainingSession(String nameOfTraining, int calories){
        this.trainingName = nameOfTraining;
        this.caloriesPerTraining = calories;
    }

    public String getName(){
        return trainingName;
    }
    public int getCalories(){
        return caloriesPerTraining;
    }

    public String toString(){
        return this.trainingName;
    }

}
