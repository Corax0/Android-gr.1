package pl.wspa.student.wspacitibikenyc;
/**
 * Created by Tomasz around 17.09.2015.
 * Stolen by Piotrek on 2015-11-04.
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;

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
import java.util.ArrayList;

class JSONAsyncTask extends AsyncTask<String, Void, Boolean>
{

    public static ArrayList<Station> stationArrayList;
    //ProgressDialog dialog;

    public static void InitializeStationArrayList()
    {
        if (stationArrayList == null)
            stationArrayList = new ArrayList<Station>();
        return;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        /*dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Trwa ³adowanie danych");
        dialog.setTitle("Nawi¹zywanie po³¹czenia");
        dialog.show();
        dialog.setCancelable(false);*/
    }

    @Override
    protected Boolean doInBackground(String... urls) {
        try {
            HttpGet httppost = new HttpGet(urls[0]);
            HttpClient httpClient = new DefaultHttpClient();

            HttpResponse response = httpClient.execute(httppost);

            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);
                JSONObject jasonObj = new JSONObject(data);
                JSONArray jsonArray = jasonObj.getJSONArray("stationBeanList");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    Station station = new Station();

                    station.setStationName(object.getString("stationName"));
                    station.setAvailableDocks(object.getString("availableDocks"));
                    station.setAvailableBikes(object.getString("availableBikes"));
                    station.setStatusValue(object.getString("statusValue"));
                    station.setLastCommunicationTime(object.getString("lastCommunicationTime"));
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
        //dialog.cancel();
        //adapter.notifyDataSetChanged();
        if (aBoolean==false)
        {
            //Toast.makeText(getApplicationContext(),"Nie mo¿na pobraæ danych",Toast.LENGTH_LONG).show();
        }
    }
}