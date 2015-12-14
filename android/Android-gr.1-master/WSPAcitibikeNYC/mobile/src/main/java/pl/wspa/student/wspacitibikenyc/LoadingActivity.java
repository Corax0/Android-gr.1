package pl.wspa.student.wspacitibikenyc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class LoadingActivity extends ActionBarActivity {
    private boolean taskInternet, taskLocation,taskDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        SettingsUtil.loadSettings(getApplicationContext());
        taskInternet=true;
        taskLocation =true;
        taskDatabase=true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                taskLocation=!LocationConnectionUtil.updateLocationData(getApplicationContext(),getSupportFragmentManager());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                taskInternet=!InternetConnectionUtil.updateStationFromJSON(getApplicationContext(),getSupportFragmentManager());
            }
        }).start();
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