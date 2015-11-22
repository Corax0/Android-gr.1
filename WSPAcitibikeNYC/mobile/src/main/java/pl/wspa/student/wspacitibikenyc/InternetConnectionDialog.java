package pl.wspa.student.wspacitibikenyc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;

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
        WifiManager wifiManager = (WifiManager)this.context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
    }

    public void setContext(Context context){
        this.context = context;
    }
}
