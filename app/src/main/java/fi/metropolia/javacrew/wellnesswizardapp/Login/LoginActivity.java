package fi.metropolia.javacrew.wellnesswizardapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.Locale;

import fi.metropolia.javacrew.wellnesswizardapp.Henkilo;
import fi.metropolia.javacrew.wellnesswizardapp.MainActivity;
import fi.metropolia.javacrew.wellnesswizardapp.R;
import fi.metropolia.javacrew.wellnesswizardapp.UserCreateActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_BERBA = "username.EXTRA_BERBA";

    //Luodaan username ja enterButton muuttujat
    private EditText username;
    private Button enterButton;

    //väliaikainen ohitus nappula
    private Button bypassButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Henkilo loaded = loadData();
        if (loaded != null){
            Henkilo.setInstance(loaded);
            System.out.println("Tämä tulee iffistä");
            Intent bypassIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(bypassIntent);
        } else {
            username = findViewById(R.id.editTextTextUsername);
            username.setText("");
            System.out.println("Tämä tulee elsestä");
        }


        //Luodaan yhteys Enter-nappulaan
        enterButton = findViewById(R.id.enterButton);

        enterButton();

    }

    private Henkilo loadData() {
        //Load object
        SharedPreferences prefPut = getSharedPreferences("Henkilo", Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefPut.getString("Henkilo", null);
        return gson.fromJson(json, Henkilo.class);
    }

    public void enterButton(){
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString().toLowerCase(Locale.ROOT);
                Intent enterIntent = new Intent(LoginActivity.this, UserCreateActivity.class);
                enterIntent.putExtra(EXTRA_BERBA, name);
                startActivity(enterIntent);
            }
        });
    }
}