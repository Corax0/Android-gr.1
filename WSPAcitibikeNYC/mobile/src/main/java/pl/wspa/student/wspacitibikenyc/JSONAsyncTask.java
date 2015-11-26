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

class JSONAsyncTask extends AsyncTask<String, Void, Boolean>
{
    private Context context;
    public static ArrayList<Station> stationArrayList;

    public JSONAsyncTask(Context context){
        super();
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... urls) {
        if(stationArrayList == null)
            stationArrayList = new ArrayList<Station>();

        try {
            HttpGet httppost = new HttpGet(urls[0]);
            HttpClient httpClient = new DefaultHttpClient();

            HttpResponse response = httpClient.execute(httppost);

            if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);
                JSONObject jasonObj = new JSONObject(data);
                JSONArray jsonArray = jasonObj.getJSONArray("stationBeanList");

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
                return true;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return false;
    }


    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        //TODO adapter.notifyDataSetChanged();
        if (aBoolean){
            Toast.makeText(context,R.string.toast_sync_station_success , Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context,R.string.toast_sync_station_failed,Toast.LENGTH_LONG).show();
        }
    }

}