package fi.metropolia.javacrew.wellnesswizardapp.Login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fi.metropolia.javacrew.wellnesswizardapp.R;

public class LoginActivity extends AppCompatActivity {


    EditText username;
    Button enterButton = findViewById(R.id.enterButton);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //Luodaan username muuttuja PlainText componentille,
        //ja luodaan yhteys componentiin id:n avulla.
        username = findViewById(R.id.editTextTextUsername);
        username.setText("");



    }

    public void enterTestActivity(){
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

}