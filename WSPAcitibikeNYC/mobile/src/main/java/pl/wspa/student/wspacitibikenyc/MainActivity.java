package pl.wspa.student.wspacitibikenyc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
    public static final int APPLICATION_START=1,
                               RESULT_LOADED=2;
    public static final String NY_CITY_BIKE_URL="http://www.citibikenyc.com/stations/json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivityForResult(new Intent(this, LoadingActivity.class), APPLICATION_START);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==APPLICATION_START){
            switch(resultCode){
                case RESULT_LOADED:
                    if(data.hasExtra("")){
                        //TODO()data.getExtras().getSerializable("") ;
                    }
                    break;
                default:
                    break;
            }
        }
        else{
            finish();
        }
    }
}
