package fi.metropolia.javacrew.wellnesswizardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import fi.metropolia.javacrew.wellnesswizardapp.Login.LoginActivity;


public class UserCreateActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private Henkilo person;

    EditText name;
    EditText gender;
    EditText age;
    EditText weight;
    EditText height;
    private String personName, personGender;
    private int personAge, personHeight;
    private double personWeight;
    private Intent intent;


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
        Intent intent= getIntent();
        String loginName =intent.getStringExtra(LoginActivity.EXTRA_BERBA);

        sharedPreferences = getSharedPreferences("Henkilo", Activity.MODE_PRIVATE);
        //Parameters from user inputs.
        name = (EditText) findViewById(R.id.editTextTextPersonName);
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
                personName = loginName;
                personGender = gender.getText().toString();
                personAge = Integer.parseInt(age.getText().toString());
                personHeight = Integer.parseInt(height.getText().toString());
                personWeight = Double.parseDouble(weight.getText().toString());

                Henkilo uusiKayttaja = new Henkilo(personName, personAge, personHeight, personWeight, personGender);
                saveData(uusiKayttaja);
                Henkilo.setInstance(uusiKayttaja);

                //Testi load old Henkilo
                Henkilo ladattu = loadData();
                System.out.println(ladattu + " ladattu 60 sekunnissa");
            }
        });

        //ends
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
        person = gson.fromJson(json, Henkilo.class);
        return person;
    }
}