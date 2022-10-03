package fi.metropolia.javacrew.wellnesswizardapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import fi.metropolia.javacrew.wellnesswizardapp.MainActivity;
import fi.metropolia.javacrew.wellnesswizardapp.R;

public class LoginActivity extends AppCompatActivity {

    //Luodaan username ja enterButton muuttujat
    private EditText username;
    private Button enterButton;

    //väliaikainen ohitus nappula
    private Button bypassButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //Luodaan yhteys username muuttujaan PlainText componentin id:n avulla
        username = findViewById(R.id.editTextTextUsername);
        username.setText("");

        //Luodaan yhteys Enter-nappulaan
        enterButton = findViewById(R.id.enterButton);

        bypassButton = findViewById(R.id.bypassButton);

        bypassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bypassIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(bypassIntent);
            }
        });
    }


    public void enterTestActivity(){
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.getText().toString().toLowerCase(Locale.ROOT);

            }
        });
    }

}