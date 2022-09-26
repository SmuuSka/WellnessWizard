package fi.metropolia.javacrew.wellnesswizardapp.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import fi.metropolia.javacrew.wellnesswizardapp.R;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resipe_detail);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(RecipeLibraryActivity.EXTRA_RECIPE, 0);

        Recipe recipe = RecipeInfoHolder.getInstance().getResipes().get(i);

        ((TextView)findViewById(R.id.textView_RecipeName)).setText(recipe.getNameOfDish());
        ((TextView)findViewById(R.id.textViewRecipeContent)).setText(recipe.getIngredientsList());
        ((TextView)findViewById(R.id.textView_Calories)).setText(Integer.toString(recipe.getCaloryAmount()));
    }
}