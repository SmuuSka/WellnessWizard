package fi.metropolia.javacrew.wellnesswizardapp.trainingSessions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import fi.metropolia.javacrew.wellnesswizardapp.R;
import fi.metropolia.javacrew.wellnesswizardapp.recipe.Recipe;
import fi.metropolia.javacrew.wellnesswizardapp.recipe.RecipeInfoHolder;
import fi.metropolia.javacrew.wellnesswizardapp.recipe.RecipeLibraryActivity;

public class TrainingDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(fi.metropolia.javacrew.wellnesswizardapp.R.layout.activity_training_detail);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(TrainingSessionsLibraryActivity.EXTRA_TRAINING, 0);

        TrainingSession trainingSession = TrainingSessionHolder.getInstance().getTraining().get(i);

        ((TextView)findViewById(R.id.textView_NameOfTraining)).setText(trainingSession.getName());
        ((TextView)findViewById(R.id.textView_TrainingCalorieAmount)).setText(Integer.toString(trainingSession.getCalories()));
    }
}