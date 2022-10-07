package fi.metropolia.javacrew.wellnesswizardapp;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.material.timepicker.TimeFormat;
import com.google.gson.Gson;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@RequiresApi(api = Build.VERSION_CODES.O)
public class ResetProgress extends Service {

    private static String CURRENT_USER_TIMEZONE = "Europe/Helsinki";
    private static final LocalTime midnight = LocalTime.MIDNIGHT;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(getCurrentTime().equals(midnight)){
                        resetAllProgressData();
                    }
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public ZonedDateTime getCurrentTime(){
        Instant now = Instant.now();
        ZonedDateTime time = ZonedDateTime.ofInstant(now,
                             ZoneId.of(CURRENT_USER_TIMEZONE));
        System.out.println(midnight);
        return time;

    }

    private void resetAllProgressData(){
        Henkilo.getInstance().nollaaSyodytKalorit();
        Henkilo.getInstance().resetSteps();
        saveData(Henkilo.getInstance());

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
}



