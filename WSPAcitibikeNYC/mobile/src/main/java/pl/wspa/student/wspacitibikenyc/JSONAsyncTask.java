package pl.wspa.student.wspacitibikenyc;
/**
 * Created by Tomasz around 17.09.2015.
 * Stolen by Piotrek on 2015-11-04.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

class JSONAsyncTask extends AsyncTask<String, Void, ArrayList<Station>>
{
    private Context context;
    private static boolean updatingStations=false;

    public JSONAsyncTask(Context context){
        super();
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        updatingStations=true;
    }

    @Override
    protected ArrayList<Station> doInBackground(String... urls) {

        try {
            HttpGet httppost = new HttpGet(urls[0]);
            HttpClient httpClient = new DefaultHttpClient();

            HttpResponse response = httpClient.execute(httppost);

            if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);
                JSONObject jasonObj = new JSONObject(data);
                JSONArray jsonArray = jasonObj.getJSONArray("stationBeanList");
                ArrayList<Station> stationArrayList=new ArrayList<Station>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    Station station = new Station(
                            object.getString("id"),
                            object.getString("stationName"),
                            object.getString("availableDocks"),
                            object.getString("totalDocks"),
                            object.getString("latitude"),
                            object.getString("longitude"),
                            object.getString("statusValue"),
                            object.getString("statusKey"),
                            object.getString("availableBikes"),
                            object.getString("stAddress1"),
                            object.getString("stAddress2"),
                            object.getString("city"),
                            object.getString("postalCode"),
                            object.getString("location"),
                            object.getString("altitude"),
                            object.getString("testStation"),
                            object.getString("lastCommunicationTime"),
                            object.getString("landMark"));
                    stationArrayList.add(station);
                }
                return stationArrayList;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Station> stationList) {
        super.onPostExecute(stationList);
        if (stationList==null) {
            Toast.makeText(context, R.string.toast_sync_station_failed, Toast.LENGTH_LONG).show();
            updatingStations=false;
            return;
        }
        else if(stationList.size()!=0){
            MainActivity.stationArrayList=stationList;
        }
        updatingStations=false;
        Toast.makeText(context,R.string.toast_sync_station_success, Toast.LENGTH_LONG).show();
    }

    public static void setUpdatingStations(boolean updatingStations) {
        JSONAsyncTask.updatingStations = updatingStations;
    }

    public static boolean isUpdatingStations() {
        return updatingStations;
    }
}