package fi.metropolia.javacrew.wellnesswizardapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@RequiresApi(api = Build.VERSION_CODES.O)

/**
 * Reset progress class holding reset progress functionality
 * @author Samu
 */
public class ResetProgress extends Service {
    private boolean isReseted = false;
    private static String CURRENT_USER_TIMEZONE = "Europe/Helsinki";
    private static final LocalTime midnight = LocalTime.MIDNIGHT;

    /**
     * Main activity launch the service
     * Service override onStartCommand
     * and create a new Thread with new interface for overriding the run method.
     * Method running background loop for checking when helsinki-time hits midnight
     * and resetting user eaten and burned kilocalories back to zero
     * The service stops at midnight
     *
     * @param intent intent which keeped alive
     * @param flags controlling the task
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    String helsinki = getCurrentTime().format(DateTimeFormatter.ofPattern("HH:mm"));
                    String midnightToString = midnight.format(DateTimeFormatter.ofPattern("HH:mm"));
                    if(helsinki.equals(midnight)){
                        resetAllProgressData();
                        stopSelf(1001);
                    }

                    try {
                        Thread.sleep(59000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        final String CHANNELID = "Reset progress service ID";
        NotificationChannel channel = new NotificationChannel(CHANNELID,CHANNELID, NotificationManager.IMPORTANCE_LOW);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notification = new Notification.Builder(this,CHANNELID)
                .setContentText("Reset service is running")
                .setContentTitle("Reset Progress Service")
                .setSmallIcon(R.drawable.vector);
        startForeground(1001,notification.build());
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * getCurrentTime get the time from java.time.instant
     * and method fix the time to Helsinki timezone
     * @return and return LocalTime-type
     */

    public LocalTime getCurrentTime(){
        Instant now = Instant.now();
        ZonedDateTime time = ZonedDateTime.ofInstant(now,
                             ZoneId.of(CURRENT_USER_TIMEZONE));
        LocalTime helsinki = time.toLocalTime();
        return helsinki;

    }

    /**
     * resetAllProgressData method reset all user progress
     * and save data
     */
    private void resetAllProgressData(){
        Henkilo.getInstance().nollaaSyodytKalorit();
        Henkilo.getInstance().resetSteps();
        Henkilo.getInstance().resetCompensationSteps();
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



