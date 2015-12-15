package pl.wspa.student.wspacitibikenyc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;

/**
 * Created by Karolina i Daniel on 2015-11-22.
 */
public class InternetConnectionDialog extends ConnectionDialog {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog= (AlertDialog) super.onCreateDialog(savedInstanceState);
        dialog.setView(view);
        dialog.setTitle(R.string.settings_internet_connection_title);
        message.setText(getString(R.string.dialog_internet_message));
        return dialog;
    }

    @Override
    public void onOkClick(DialogInterface dialog, int id) {
        if(checkBox.isChecked()){
            SettingsUtil.setConnectInternet(true);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(checkBox.isChecked()){
            SettingsUtil.setAskInternet(true);
        }
    }
}
