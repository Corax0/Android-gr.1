package pl.wspa.student.wspacitibikenyc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;


public class LoadingActivity extends ActionBarActivity {
    private boolean askInternet, askLocation;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        askInternet =settings.getBoolean(SettingsActivity.KEY_INTERNET_OFF, false);
        askLocation =settings.getBoolean(SettingsActivity.KEY_LOCATION_OFF, false);

        //TODO w nowym watku trzeba to zrobic
        if(InternetConnectionUtil.isConnectedToInternet(getApplicationContext())) {
            JSONAsyncTask json=new JSONAsyncTask(){
                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    Toast.makeText(getApplicationContext(), "Zaktualizowano stacje", Toast.LENGTH_LONG).show();
                    finish();
                }
            };
            json.execute(MainActivity.NY_CITY_BIKE_URL);
        }
        else {
            if(!askInternet) {
                DialogFragment fragment = new InternetConnectionDialog() {
                    @Override
                    public void addBroadcastRegisterOperations() {
                        finish();
                    }
                };
                fragment.show(getSupportFragmentManager(), "internet");
            }
        }
        //TODO SQL, i jakies inne pierdy
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