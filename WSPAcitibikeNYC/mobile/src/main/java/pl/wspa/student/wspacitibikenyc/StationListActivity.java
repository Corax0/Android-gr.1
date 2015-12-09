package pl.wspa.student.wspacitibikenyc;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Daniel on 2015-12-02.
 */
public class StationListActivity extends ActionBarActivity{

    StationAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_station);
        listView = (ListView)findViewById(R.id.list);
        adapter=new StationAdapter(getApplicationContext(),MainActivity.stationArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogFragment fragment = new StationInfoDialog();
                ((StationInfoDialog)fragment).setStation(adapter.getItem(position));
                fragment.show(getSupportFragmentManager(), "station");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            if(!InternetConnectionUtil.isUpdatingStations()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        InternetConnectionUtil.updateStationFromJSON(getApplicationContext(), getSupportFragmentManager());
                        if (InternetConnectionUtil.isUpdatingStations()) {
                            while (InternetConnectionUtil.isUpdatingStations()) {
                            }
                            try {
                                adapter.notifyDataSetChanged();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
            else {
                Toast.makeText(getApplicationContext(), R.string.toast_sync_inProgress, Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
