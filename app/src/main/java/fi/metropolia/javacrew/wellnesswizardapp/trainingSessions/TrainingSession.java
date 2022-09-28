package fi.metropolia.javacrew.wellnesswizardapp.trainingSessions;

public class TrainingSession {

    private String trainingName;
    private int caloriesPerTraining;

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

}
