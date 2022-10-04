package fi.metropolia.javacrew.wellnesswizardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import fi.metropolia.javacrew.wellnesswizardapp.Login.LoginActivity;


public class UserCreateActivity extends AppCompatActivity {

    EditText name;
    EditText gender;
    EditText age;
    EditText weight;
    EditText height;
    private String loginName;
    private Intent nextActivityIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);
        //starts
        /**
         * Author @tristan
         * Create user, name comes from login screen as a parameter.
         * Take user inputs as parameters and create Henkilo as singleton
         * save it also as a sharedPreferense.
         * */
        Intent intent = getIntent();
        loginName = intent.getStringExtra(LoginActivity.EXTRA_BERBA);
        SharedPreferences sharedPreferences = getSharedPreferences("Henkilo", Activity.MODE_PRIVATE);

        //Parameters from user inputs.
        name = (EditText) findViewById(R.id.editTextTextPersonName);
        if (loginName.isEmpty()) {
            name.setText("");
        } else {
            name.setText(loginName);
        }
        gender = (EditText) findViewById(R.id.editTextTextPersonNameGender);
        age = (EditText) findViewById(R.id.editTextNumberAge);
        height = (EditText) findViewById(R.id.editTextNumberHeight);
        weight = (EditText) findViewById(R.id.editTextNumberDecimalWeight);
        //just to get some data, will eventually be removed.
        System.out.println(sharedPreferences);

        Button send = findViewById(R.id.buttonSendData);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save data and move to next activity.

                Henkilo uusiKayttaja = createHenkilo();

                if (uusiKayttaja == null) {
                    return;
                }

                saveData(uusiKayttaja);
                Henkilo.setInstance(uusiKayttaja);

                //Testi load old Henkilo
                Henkilo ladattu = loadData();
                System.out.println(ladattu + " ladattu 60 sekunnissa");
                nextActivityIntent = new Intent(UserCreateActivity.this, MainActivity.class);
                startActivity(nextActivityIntent);
            }
        });

        //ends
    }

    private Henkilo createHenkilo() {
        String personName = loginName;

        String personGender = gender.getText().toString();

        int personAge;
        try {
            personAge = Integer.parseInt(age.getText().toString());
            if (personAge < 18 || personAge > 100) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Age must be between 18 and 100", Toast.LENGTH_SHORT).show();
            return null;
        }

        int personHeight;
        try {
            personHeight = Integer.parseInt(height.getText().toString());
            if (personHeight < 120 || personHeight > 250) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Height must be between 100cm - 250cm", Toast.LENGTH_SHORT).show();
            return null;
        }

        double personWeight;
        try {
            personWeight = Double.parseDouble(weight.getText().toString());
            if (personWeight < 40.0 || personWeight > 200.0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "weight must be between 40kg and 200kg", Toast.LENGTH_SHORT).show();
            return null;
        }

        if (personGender.isEmpty()) {
            return new Henkilo(personName, personAge, personHeight, personWeight);
        } else {
            return new Henkilo(personName, personAge, personHeight, personWeight, personGender);
        }

    }

    private void saveData(Henkilo henkilo) {
        //Might be for another acivity.
        SharedPreferences prefPut = getSharedPreferences("Henkilo", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        Gson gson = new Gson();
        String json = gson.toJson(henkilo);
        prefEditor.putString("Henkilo", json);
        prefEditor.apply();
    }

    private Henkilo loadData() {
        //Load object
        SharedPreferences prefPut = getSharedPreferences("Henkilo", Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefPut.getString("Henkilo", null);
        Henkilo person = gson.fromJson(json, Henkilo.class);
        return person;
    }
}