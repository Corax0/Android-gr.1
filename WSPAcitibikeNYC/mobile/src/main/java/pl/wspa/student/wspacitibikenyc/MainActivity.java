package pl.wspa.student.wspacitibikenyc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;



public class MainActivity extends ActionBarActivity {
    private boolean taskInternet, taskLocation,taskDatabase;
    public static final int APPLICATION_START = 1,
            RESULT_LOADED = 2;

    public static ArrayList<Station> stationArrayList;

    public static double gpsLongitude = 0.0;
    public static double gpsLatitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (stationArrayList == null) {
            stationArrayList = new ArrayList<Station>();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivityForResult(new Intent(this, LoadingActivity.class), APPLICATION_START);



        /////////////// Kod do przechodzenia do innej aktywności po naciśnięciu guzika, na youtube dziala, u mnie nie i nie wiem jak to naprawić.
        //Po kliknięciu na przycisk Aktualizacja powinna wyskoczyć jedna stacja //////////////////

        Button mapButt = (Button) findViewById(R.id.menu_right_top_button);
        mapButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), StationMapActivity.class));
            }
        });
        Button listButt = (Button) findViewById(R.id.menu_left_center_button);
        listButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), StationListActivity.class));
            }
        });
        Button settingsButt = (Button) findViewById(R.id.menu_left_bottom_button);
        settingsButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SettingsActivity.class));
            }
        });
        Button findButt = (Button) findViewById(R.id.menu_left_top_button);
        findButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), NavigateToActivity.class));
            }
        });

        Button ownMarks = (Button) findViewById(R.id.menu_right_center_button);
        ownMarks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), StationListActivity.class));
            }
        });

        Button refreshButt = (Button) findViewById(R.id.menu_right_bottom_button);
        refreshButt.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
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
                        taskInternet=!InternetConnectionUtil.updateStationFromJSON(getApplicationContext(),getSupportFragmentManager(),new JSONAsyncTask(getApplicationContext()));
                    }
                }).start();
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

                    }
                }).start();


            }
        });


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
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == APPLICATION_START) {
            switch (resultCode) {
                case RESULT_LOADED:
                    if (data.hasExtra("")) {
                        //TODO()data.getExtras().getSerializable("") ;
                    }
                    break;
                default:
                    break;
            }
        } else {
            finish();
        }
    }
}
