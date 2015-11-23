package pl.wspa.student.wspacitibikenyc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Karolina i Daniel on 2015-11-22.
 */
public abstract class InternetConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(InternetConnectionUtil.isConnectedToInternet(context)){
            //TODO pobranie i aktualizacja
            Toast.makeText(context, "Zaktualizowano stacje", Toast.LENGTH_LONG).show();
            onConnectedToInternet();
        }
    }
    public abstract void onConnectedToInternet();
}
