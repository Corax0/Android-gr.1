package pl.wspa.student.wspacitibikenyc;

import android.content.Context;
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
            if(!SettingsUtil.askLocation) {
                freeze=true;
                DialogFragment fragment = new LocationConnectionDialog(){
                    @Override
                    public void onDestroyView() {
                        super.onDestroyView();
                        freeze = false;
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
