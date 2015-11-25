package pl.wspa.student.wspacitibikenyc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by Karolina i Daniel on 2015-11-22.
 */
public class InternetConnectionDialog extends ConnectionDialog {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog= (AlertDialog) super.onCreateDialog(savedInstanceState);
        dialog.setView(view);
        dialog.setTitle(R.string.settings_internet_off_title);
        dialog.setMessage(getString(R.string.dialog_internet_message));
        return dialog;
    }

    @Override
    public void onOkClick(DialogInterface dialog, int id) {
        if(checkBox.isChecked()){
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(SettingsActivity.KEY_INTERNET_OFF, true);
            editor.commit();
        }
        WifiManager wifiManager = (WifiManager)getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        BroadcastReceiver icReceiver = new InternetConnectionReceiver() {
            @Override
            public void onConnectedToInternet() {
                addBroadcastRegisterOperations();
            }
        };
        getActivity().getApplicationContext().registerReceiver(icReceiver, filter);
    }
    public void addBroadcastRegisterOperations(){};
}
