package fi.metropolia.javacrew.wellnesswizardapp.recipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;

import fi.metropolia.javacrew.wellnesswizardapp.MainActivity;
import fi.metropolia.javacrew.wellnesswizardapp.R;
import fi.metropolia.javacrew.wellnesswizardapp.trainingSessions.TrainingDetailActivity;
import fi.metropolia.javacrew.wellnesswizardapp.trainingSessions.TrainingSessionsLibraryActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    private NavigationBarView bottomNav;

    int mealCaloriesAmount;

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

        mealCaloriesAmount = recipe.getCaloryAmount();

        bottomNav = findViewById(R.id.bottomNavID);
        bottomNav.getMenu().getItem(1).setChecked(true);

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()){
                    case R.id.exercise:
                        intent = new Intent(RecipeDetailActivity.this, TrainingSessionsLibraryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.receipt:
                        intent = new Intent(RecipeDetailActivity.this, RecipeLibraryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.home:
                        intent = new Intent(RecipeDetailActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }

        });
    }

    public void SendEatenCalories(View view) {

        RecipeInfoHolder.getInstance().SetIntakeCaloriesAmount(mealCaloriesAmount);
    }
}