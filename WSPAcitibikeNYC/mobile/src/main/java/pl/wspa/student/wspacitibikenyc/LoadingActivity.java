package pl.wspa.student.wspacitibikenyc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;


public class LoadingActivity extends ActionBarActivity {
    private boolean askInternet, askLocation;
    private boolean taskInternet, taskLocation,taskDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        askInternet =settings.getBoolean(SettingsActivity.KEY_INTERNET_OFF, false);
        askLocation =settings.getBoolean(SettingsActivity.KEY_LOCATION_OFF, false);
        taskInternet=true;
        taskLocation =true;
        taskDatabase=true;
        if(LocationConnectionUtil.isConnectedToGPS(getApplicationContext())) {
            //TODO wątek z lokalizacją
            taskLocation =false;
        }
        else{
            if(!askLocation){
                DialogFragment fragment = new LocationConnectionDialog(){
                    @Override
                    public void onDestroyView() {
                        super.onDestroyView();
                        taskLocation =false;
                    }
                };
                fragment.show(getSupportFragmentManager(), "location");
            }
            else{
                taskLocation =false;
            }
        }
        if(InternetConnectionUtil.isConnectedToInternet(getApplicationContext())) {
            JSONAsyncTask json=new JSONAsyncTask(getApplicationContext());
            json.execute(InternetConnectionUtil.NY_CITY_BIKE_URL);
            taskInternet=false;
        }
        else {
            if(!askInternet) {
                DialogFragment fragment = new InternetConnectionDialog(){
                    @Override
                    public void onDestroyView() {
                        super.onDestroyView();
                        taskInternet=false;
                    }
                };
                fragment.show(getSupportFragmentManager(), "internet");
            }
            else{
                taskInternet=false;
            }
        }
        //TODO SQL, i jakies inne pierdy
        taskDatabase=false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(taskInternet|| taskLocation ||taskDatabase){}
                int millis=1000;
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        }).start();
    }

    @Override
    public void onBackPressed() {
        //Zablokowany powrot
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
}