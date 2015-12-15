package pl.wspa.student.wspacitibikenyc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

/**
 * Created by Daniel on 2015-11-25.
 */
public class LocationConnectionUtil {

    private static boolean updatingLocation =false;
    private static boolean freeze=false;

    public static boolean isConnectedToGPS(Context context){
        LocationManager lm =(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static boolean hasGpsModule(Context context){
        PackageManager packMan = context.getPackageManager();
        return packMan.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
    }

    /**
     * Funkcja do łączenia i pobierania danych z modułu gps, jeśli nie jest wyłączona opcja pytania się użytkownika o połączenie się z gps, wyświetla się alert.
     * UWAGA! Należy używać tej metody w oddzielnym wątku ponieważ "zamraża" aplikację np:
     *
        new Thread(new Runnable() {
            @Override
            public void run() {
                LocationConnectionUtil.updateLocationData(getApplicationContext(),getSupportFragmentManager());
            }
        }).start();
     *
     * @param context context aktywności
     * @param supportFragmentManager supportFragmentManager aktywności
     * @return zwraca true po zakonczeniu pobierania lub pytania sie o pobieranie
     */
    public static boolean updateLocationData(Context context,FragmentManager supportFragmentManager){
        if(isConnectedToGPS(context)) {
            updatingLocation =true;
            //TODO wątek z lokalizacją po wykonaniu zmienić na:
            updatingLocation = false;
        }
        else {
            if(SettingsUtil.hasConnectToLocation()){
                Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                callGPSSettingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
                context.startActivity(callGPSSettingIntent);
                //TODO jesli nie będzie działać trzeba zmienić filtry
                IntentFilter filter = new IntentFilter("android.location.GPS_ENABLED_CHANGE");
                filter.addAction("android.location.GPS_FIX_CHANGE");
                BroadcastReceiver icReceiver = new LocationConnectionReceiver() {
                    @Override
                    public void onConnectedToGPS() {
                        updatingLocation=false;
                    }
                };
                updatingLocation=true;
                context.registerReceiver(icReceiver, filter);
            }
            else if(SettingsUtil.askLocation()) {
                freeze=true;
                DialogFragment fragment = new LocationConnectionDialog(){
                    @Override
                    public void onDestroyView() {
                        super.onDestroyView();
                        freeze = false;
                        updatingLocation=true;
                    }
                    @Override
                    public void addBroadcastRegisterOperations() {
                        updatingLocation =false;
                    }
                };
                try {
                    fragment.show(supportFragmentManager, "location");
                    while(freeze){}
                } catch (ClassCastException e) {
                    Log.e("LOCATION_UTIL", "Can't get fragment manager");
                }
            }
        }
        return true;
    }

    public static boolean isUpdatingLocation() {
        return updatingLocation;
    }
}
