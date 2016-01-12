package pl.wspa.student.wspacitibikenyc;

import java.util.ArrayList;

/**
 * Created by Piotrek on 2015-11-04.
 */
public class DataSaver {
    private static ArrayList<Station> _stationArrayList;

    public static ArrayList<Station> GetStationList() {
        if (_stationArrayList == null) {
            if (MainActivity.stationArrayList == null)
                ;//TODO tutaj dac pobranie danych z backupu w sqlite
            else
                _stationArrayList = MainActivity.stationArrayList;
        }
        return _stationArrayList;
    }

    /**
     * Metoda aktualizuje odległość od pozycji (GPS) do stacji.
     */
    public static void updateStationList() {
        for (Station station : _stationArrayList) {
            station.updateDistance();
        }
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
