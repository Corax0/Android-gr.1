package pl.wspa.student.wspacitibikenyc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

/**
 * Created by Karolina i Daniel on 2015-11-22.
 */
public class InternetConnectionUtil {
    public static final String NY_CITY_BIKE_URL="http://www.citibikenyc.com/stations/json";

    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    private static boolean updatingStations=false;
    private static boolean freeze=false;

    public static boolean isConnectedToInternet(Context context){
        ConnectivityManager cm =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork!=null) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = InternetConnectionUtil.getConnectivityStatus(context);
        String status = null;
        if (conn == InternetConnectionUtil.TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == InternetConnectionUtil.TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == InternetConnectionUtil.TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }

    /**
     * Funkcja do łączenia i pobierania danych z internetu, jeśli nie jest wyłączona opcja pytania się użytkownika o połączenie się z internetem, wyświetla się alert.
     * UWAGA! Należy używać tej metody w oddzielnym wątku np:
     *
        new Thread(new Runnable() {
            @Override
            public void run() {
                InternetConnectionUtil.updateStationFromJSON(getApplicationContext(),getSupportFragmentManager());
            }
        }).start();
     *
     * @param context context aktywności
     * @param supportFragmentManager supportFragmentManager aktywności
     * @return zwraca true po zakonczeniu pobierania lub pytania sie o pobieranie
     */
    public static boolean updateStationFromJSON(Context context,FragmentManager supportFragmentManager){
        if(isConnectedToInternet(context)) {
            updatingStations = true;
            JSONAsyncTask json = new JSONAsyncTask(context) {
                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    updatingStations = false;
                }
            };
            json.execute(NY_CITY_BIKE_URL);
        }
        else {
            if(SettingsUtil.hasConnectToInternet()){
                WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);
                IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
                filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
                BroadcastReceiver icReceiver = new InternetConnectionReceiver() {
                    @Override
                    public void onConnectedToInternet() {
                        updatingStations=false;
                    }
                };
                updatingStations=true;
                context.registerReceiver(icReceiver, filter);
            }
            else if(SettingsUtil.askInternet()) {
                freeze=true;
                DialogFragment fragment = new InternetConnectionDialog(){
                    @Override
                    public void onOkClick(DialogInterface dialog, int id) {
                        super.onOkClick(dialog, id);
                        updatingStations=true;
                    }

                    @Override
                    public void onDestroyView() {
                        super.onDestroyView();
                        freeze = false;
                    }
                    @Override
                    public void addBroadcastRegisterOperations() {
                        updatingStations =false;
                    }
                };
                try {
                    fragment.show(supportFragmentManager, "internet");
                    while(freeze){}
                } catch (ClassCastException e) {
                    Log.e("INTERNET_UTIL", "Can't get fragment manager");
                }
            }
        }
        return true;
    }

    public static boolean isUpdatingStations() {
        return updatingStations;
    }
}
