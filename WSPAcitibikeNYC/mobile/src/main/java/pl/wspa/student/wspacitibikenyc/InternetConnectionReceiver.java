package pl.wspa.student.wspacitibikenyc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Karolina i Daniel on 2015-11-22.
 */
public abstract class InternetConnectionReceiver extends BroadcastReceiver {
    JSONAsyncTask json;

    public InternetConnectionReceiver(JSONAsyncTask task){
        json=task;
        json.setUpdatingStations(true);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(InternetConnectionUtil.isConnectedToInternet(context)){
            json.execute(InternetConnectionUtil.NY_CITY_BIKE_URL);
            onConnectedToInternet();
        }
    }
    public abstract void onConnectedToInternet();
}
