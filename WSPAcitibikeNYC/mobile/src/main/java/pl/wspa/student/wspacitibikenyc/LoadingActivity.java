package pl.wspa.student.wspacitibikenyc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.util.ArrayList;


public class LoadingActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        JSONAsyncTask json=new JSONAsyncTask(){
            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                finish();
            }
        };
        if(NetworkUtil.isConnectedToInternet(getApplicationContext())) {
            json.execute(MainActivity.NY_CITY_BIKE_URL);
        }
        else{

        }

        //TODO SQL, i jakies inne pierdy
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
}