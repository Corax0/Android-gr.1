package pl.wspa.student.wspacitibikenyc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Daniel on 2015-12-05.
 */
public abstract class LocationConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(LocationConnectionUtil.isConnectedToGPS(context)){
            //TODO wątek z lokalizacją
            onConnectedToGPS();
        }
    }
    public abstract void onConnectedToGPS();
}
