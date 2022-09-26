package fi.metropolia.javacrew.wellnesswizardapp.Login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import fi.metropolia.javacrew.wellnesswizardapp.R;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //Luodaan username muuttuja PlainText componentille,
        //ja luodaan yhteys componentiin id:n avulla.
        EditText username = findViewById(R.id.editTextTextUsername);
        username.setText("");
    }




}