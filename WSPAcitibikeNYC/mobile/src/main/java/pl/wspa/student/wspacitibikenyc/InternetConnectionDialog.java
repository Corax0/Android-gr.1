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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Karolina i Daniel on 2015-11-22.
 */
public class InternetConnectionDialog extends ConnectionDialog {

    Context context;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog= (AlertDialog) super.onCreateDialog(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialog.setView(inflater.inflate(R.layout.dialog_con_internet, null));
        dialog.setTitle(R.string.settings_internet_off_title);
        return dialog;
    }

    @Override
    public void onOkClick(DialogInterface dialog, int id) {
        CheckBox checkBox=(CheckBox)new View(context).findViewById(R.id.dci_checkBox);
        if(checkBox.isChecked()){
            //TODO zmienic settingsy
        }
        WifiManager wifiManager = (WifiManager)this.context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        BroadcastReceiver icReceiver = new InternetConnectionReceiver() {
            @Override
            public void onConnectedToInternet() {
                addBroadcastRegisterOperations();
            }
        };
        context.registerReceiver(icReceiver, filter);
    }
    public void addBroadcastRegisterOperations(){};
    public void setContext(Context context){
        this.context = context;
    }
}
