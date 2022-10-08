/**
 * Author @tristan
 * Create user, name comes from login screen as a parameter.
 * Take user inputs as parameters and create Henkilo as singleton
 * save it also as a sharedPreferense.
 * */
package fi.metropolia.javacrew.wellnesswizardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import fi.metropolia.javacrew.wellnesswizardapp.Login.LoginActivity;

/**
 * Takes user data form app user and creates a Henkilo object with given information.
 * @author tristan
 */

public class UserCreateActivity extends AppCompatActivity {

    EditText name;
    EditText age;
    EditText weight;
    EditText height;
    private String loginName;
    private Intent nextActivityIntent;
    private RadioGroup genderSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);
        //starts
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
        //gender = (EditText) findViewById(R.id.editTextTextPersonNameGender);
        age = (EditText) findViewById(R.id.editTextNumberAge);
        height = (EditText) findViewById(R.id.editTextNumberHeight);
        weight = (EditText) findViewById(R.id.editTextNumberDecimalWeight);
        //just to get some data, will eventually be removed.
        System.out.println(sharedPreferences);


        genderSelect = findViewById(R.id.radioGroup);
        genderSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                System.out.println(getGender());
            }
        });

        Button send = findViewById(R.id.buttonSendData);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save data and move to next activity.
                System.out.println("Luodaan henkiloa...");
                Henkilo uusiKayttaja = createHenkilo();

                if (uusiKayttaja == null) {
                    return;
                }
                System.out.println("Tallennetaan henkiloa");
                saveData(uusiKayttaja);
                Henkilo.setInstance(uusiKayttaja);

                //Testi load old Henkilo
                System.out.println("Yritetaan ladata henkiloa.");
                Henkilo ladattu = loadData();
                System.out.println(ladattu + " ladattu 60 sekunnissa");
                nextActivityIntent = new Intent(UserCreateActivity.this, MainActivity.class);
                startActivity(nextActivityIntent);
            }
        });

        //ends
    }

    /**
     * Create a Henkilo object with given user inputs, validates the inputs for wanted values.
     * @return Henkilo object with user inputs as constructor parameters.
     */
    private Henkilo createHenkilo() {
        String personName = loginName;

        int personAge;
        /**
         * Tries to make an integer from value, and checks if value is between 18 and 100
         * if not, the application pushes a notification to the screen for the user
         * and asks to give wanted values.
         */
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
        /**
         * Tries to make an integer from value, and checks if value is between 120 and 250
         * if not, the application pushes a notification to the screen for the user
         * and asks to give wanted values.
         */
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
        /**
         * Tries to make an double from value, and checks if value is between 40.0 and 200.0
         * if not, the application pushes a notification to the screen for the user
         * and asks to give wanted values.
         */
        try {
            personWeight = Double.parseDouble(weight.getText().toString());
            if (personWeight < 40.0 || personWeight > 200.0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "weight must be between 40kg and 200kg", Toast.LENGTH_SHORT).show();
            return null;
        }

        String personGender;
        /**
         * Checks if user hase chosen a gender, if not pushes a notification to choose gender.
         */
        try {
            personGender = getGender();
        } catch (Exception ex) {
            Toast.makeText(this, "Choose gender!", Toast.LENGTH_SHORT).show();
            return null;
        }

        if (personGender.isEmpty()) {
            Toast.makeText(this, "Choose gender!", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            System.out.println("Poistutaan createHenkilosta!");
            return new Henkilo(personName, personAge, personHeight, personWeight, personGender);
        }
    }

    /**
     * creates a json from the Henkilo object and saves it to shared preferences.
     * @param henkilo
     */
    private void saveData(Henkilo henkilo) {
        //Might be for another acivity.
        SharedPreferences prefPut = getSharedPreferences("Henkilo", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        Gson gson = new Gson();
        String json = gson.toJson(henkilo);
        prefEditor.putString("Henkilo", json);
        prefEditor.apply();
    }

    /**
     * Creates a Henkilo object from the saved json and returns a Henkilo object.
     * @return henkilo
     */
    private Henkilo loadData() {
        //Load object
        SharedPreferences prefPut = getSharedPreferences("Henkilo", Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefPut.getString("Henkilo", null);
        Henkilo person = gson.fromJson(json, Henkilo.class);
        return person;
    }

    /**
     * cheks what radio button is selected and returns gender as string accordingly.
     * @return gender as String value.
     */
    private String getGender() {
        String gender;
        int selectedId = (genderSelect).getCheckedRadioButtonId();
        if (selectedId == R.id.radioButtonMale) {
            gender = "Male";
            return gender;
        } else if (selectedId == R.id.radioButtonFemale) {
            gender = "Female";
            return gender;
        } else if (selectedId == R.id.radioButtonOther) {
            gender = "Other";
            return gender;
        } else {
            gender = "";
            return gender;
        }
    }
}