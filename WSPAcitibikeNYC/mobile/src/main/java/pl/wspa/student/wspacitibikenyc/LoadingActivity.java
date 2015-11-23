package pl.wspa.student.wspacitibikenyc;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;


public class LoadingActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        //TODO w nowym watku trzeba to zrobic
        if(InternetConnectionUtil.isConnectedToInternet(getApplicationContext())) {
            JSONAsyncTask json=new JSONAsyncTask(){
                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    finish();
                }
            };
            json.execute(MainActivity.NY_CITY_BIKE_URL);
            Toast.makeText(getApplicationContext(), "Zaktualizowano stacje", Toast.LENGTH_LONG).show();
        }
        else {
            InternetConnectionDialog dialog=new InternetConnectionDialog(){
                @Override
                public void addBroadcastRegisterOperations() {
                    finish();
                }
            };
            dialog.setContext(getApplicationContext());
            DialogFragment fragment = dialog;
            fragment.show(getSupportFragmentManager(), "internet");
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