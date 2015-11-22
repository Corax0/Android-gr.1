package pl.wspa.student.wspacitibikenyc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;


public class LoadingActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        if(NetworkUtil.isConnectedToInternet(getApplicationContext())) {
            JSONAsyncTask json=new JSONAsyncTask(){
                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    finish();
                }
            };
            json.execute(MainActivity.NY_CITY_BIKE_URL);
        }
        else {
            InternetConnectionDialog dialog=new InternetConnectionDialog(){
                @Override
                public void onOkClick(DialogInterface dialog, int id) {
                    super.onOkClick(dialog, id);
                    //TODO BroadcastReciever
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