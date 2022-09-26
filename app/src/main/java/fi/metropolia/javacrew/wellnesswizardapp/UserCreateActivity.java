package fi.metropolia.javacrew.wellnesswizardapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);
        //starts
        sharedPreferences = getSharedPreferences("Henkilo", Activity.MODE_PRIVATE);
        //Parameters from user inputs.
        name = (EditText) findViewById(R.id.editTextTextPersonName);
        gender = (EditText) findViewById(R.id.editTextTextPersonNameGender);
        age = (EditText) findViewById(R.id.editTextNumberAge);
        height = (EditText) findViewById(R.id.editTextNumberHeight);
        weight = (EditText) findViewById(R.id.editTextNumberDecimalWeight);


        Button send = findViewById(R.id.buttonSendData);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //save data and move to next activity.

                personName = name.getText().toString();
                personGender = gender.getText().toString();
                personAge = Integer.parseInt(age.getText().toString());
                personHeight = Integer.parseInt(height.getText().toString());
                personWeight = Double.parseDouble(weight.getText().toString());

                Henkilo uusiKayttaja = new Henkilo(personName, personAge, personHeight, personWeight, personGender);
                System.out.println(uusiKayttaja.toString());
                System.out.println(personName + personAge + personHeight + personWeight + personGender);
                saveData(uusiKayttaja);
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
        System.out.println(json);
        prefEditor.commit();
    }

    private Henkilo loadData() {
        SharedPreferences prefPut = getSharedPreferences("Henkilo", Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefPut.getString("Henkilo", null);
        Henkilo person = gson.fromJson(json, Henkilo.class);
        System.out.println(json + " load data json");
        return person;
    }
}