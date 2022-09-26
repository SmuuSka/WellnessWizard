package fi.metropolia.javacrew.wellnesswizardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class UserCreateActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private Henkilo person;

    EditText name, gender, age, weight, height;
    private String personName, personGender;
    private int personAge, personHeight;
    private double personWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);
        //starts
        sharedPreferences = getSharedPreferences("Henkilo", Activity.MODE_PRIVATE);

        name = (EditText) findViewById(R.id.editTextTextPersonName);
        gender = (EditText) findViewById(R.id.editTextTextPersonNameGender);
        age = (EditText) findViewById(R.id.editTextNumberAge);
        height = (EditText) findViewById(R.id.editTextNumberHeight);
        weight = (EditText) findViewById(R.id.editTextNumberDecimalWeight);

        personName = name.toString();
        personGender = gender.toString();
        personAge = Integer.parseInt(age.toString());
        personHeight = Integer.parseInt(height.toString());
        personWeight = Double.parseDouble(weight.toString());



        Button send = findViewById(R.id.buttonSendData);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //save data and move to next activity.
                Henkilo uusiKayttaja = new Henkilo(personName, personAge, personHeight, personWeight);
                System.out.println(uusiKayttaja);
                System.out.println(personName + personAge + personHeight + personWeight + personGender);
            }
        });
        //ends
    }

/*    private void saveData(String key, Henkilo henkilo) {
        //Might be for another acivity.
        SharedPreferences prefPut = getSharedPreferences("Henkilo", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString(key, henkilo); //put object here somehow putString is temprary.
        prefEditor.commit();

    }*/
}