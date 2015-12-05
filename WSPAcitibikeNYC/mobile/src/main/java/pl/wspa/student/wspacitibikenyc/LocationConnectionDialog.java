package pl.wspa.student.wspacitibikenyc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by Daniel on 2015-11-25.
 */
public class LocationConnectionDialog extends ConnectionDialog{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog= (AlertDialog) super.onCreateDialog(savedInstanceState);
        dialog.setView(view);
        dialog.setTitle(R.string.settings_location_off_title);
        message.setText(getString(R.string.dialog_location_message));
        return dialog;
    }

    @Override
    public void onOkClick(DialogInterface dialog, int id) {
        Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(callGPSSettingIntent);
        //TODO jesli nie będzie działać trzeba zmienić filtry
        IntentFilter filter = new IntentFilter("android.location.GPS_ENABLED_CHANGE");
        filter.addAction("android.location.GPS_FIX_CHANGE");
        BroadcastReceiver icReceiver = new LocationConnectionReceiver() {
            @Override
            public void onConnectedToGPS() {
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
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(SettingsUtil.KEY_LOCATION_OFF, true);
            editor.commit();
        }
    }
}
