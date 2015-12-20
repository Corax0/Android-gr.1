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

import java.util.ArrayList;
import java.util.Collections;
/**
 * Created by Daniel on 2015-12-02.
 */
public class StationListActivity extends ActionBarActivity{

    ArrayList list;
    StationAdapter adapter;
    ListView listView;
    Toast toast;
    boolean orderDist= Station.DistanceComparator.DESC,
            orderName= Station.NameComparator.DESC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_station);
        listView = (ListView)findViewById(R.id.list);
        list=(ArrayList<Station>)MainActivity.stationArrayList.clone();
        adapter=new StationAdapter(getApplicationContext(),list);
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
        switch(id){
            case R.id.action_refresh:
                if(!RefreshListAsyncTask.isUpdatingStations()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                        InternetConnectionUtil.updateStationFromJSON(getApplicationContext(),getSupportFragmentManager(),new RefreshListAsyncTask());
                        }
                    }).start();
                }
                else {
                    if(toast == null){
                        toast=Toast.makeText(getApplicationContext(), R.string.toast_sync_inProgress, Toast.LENGTH_SHORT);
                    }
                    try{
                        if(toast.getView().isShown() == false)
                            toast.show();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                return true;
            case R.id.action_sort_name:
                orderName=!orderName;
                Collections.sort(list,new Station.NameComparator(orderName));
                adapter.notifyDataSetChanged();
                return true;
            case R.id.action_sort_distance:
                orderDist=!orderDist;
                Collections.sort(list,new Station.DistanceComparator(orderDist));
                adapter.notifyDataSetChanged();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class RefreshListAsyncTask extends JSONAsyncTask{
        public RefreshListAsyncTask() {
            super(getApplicationContext());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Station> stationList) {
            super.onPostExecute(stationList);
            if(stationList.size()!=0){
                list.clear();
                list.addAll(MainActivity.stationArrayList);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
                Collections.sort(list);
            }
        }
    }
}
