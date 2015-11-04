package pl.wspa.student.wspacitibikenyc;

import java.util.ArrayList;

/**
 * Created by Piotrek on 2015-11-04.
 */
public class DataSaver
{
    private static ArrayList<Station> _stationArrayList;
    public static ArrayList<Station> GetStationList()
    {
        if (_stationArrayList == null)
        {
            if (JSONAsyncTask.stationArrayList == null)
                ;//TODO tutaj daæ pobranie danych z backupu w sqlite
            else
                _stationArrayList = JSONAsyncTask.stationArrayList;
        }
        return _stationArrayList;
    }
    /*Kradziony kod:
    setContentView(R.layout.activity_main);
        stationArrayList = new ArrayList<Station>();
        new JSONAsyncTask().execute("http://www.citibikenyc.com/stations/json");
        ListView listView = (ListView)findViewById(R.id.list);
        adapter = new StationAdapter(getApplicationContext(),R.layout.wiersz,stationArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Ostatnia aktualizacja: "+stationArrayList.get(position).getLastCommunicationTime(),Toast.LENGTH_LONG).show();
            }
        });
    */
}
