package pl.wspa.student.wspacitibikenyc;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Daniel on 2015-12-04.
 */
public class SettingsUtil {
    public static final String KEY_INTERNET_DIALOG ="internet_dialog",
                               KEY_LOCATION_DIALOG ="location_dialog",
                               KEY_INTERNET_CONNECTION ="internet_connection",
                               KEY_LOCATION_CONNECTION ="location_connection";

    private static SharedPreferences settings;
    private static SharedPreferences.Editor editor;

    public static void loadSettings(Context context){
        settings = PreferenceManager.getDefaultSharedPreferences(context);
        editor = settings.edit();
    }

    public static void setAskInternet(boolean value) {
        editor.putBoolean(SettingsUtil.KEY_INTERNET_DIALOG, value);
        editor.commit();
    }

    public static void setAskLocation(boolean value) {
        editor.putBoolean(SettingsUtil.KEY_LOCATION_DIALOG, value);
        editor.commit();
    }

    public static void setConnectInternet(boolean value) {
        editor.putBoolean(SettingsUtil.KEY_INTERNET_CONNECTION, value);
        if(value&&!SettingsUtil.askInternet()){
            editor.putBoolean(SettingsUtil.KEY_INTERNET_DIALOG, value);
        }
        editor.commit();
    }

    public static void setConnectLocation(boolean value) {
        editor.putBoolean(SettingsUtil.KEY_LOCATION_CONNECTION, value);
        if(value&&!SettingsUtil.askLocation()){
            editor.putBoolean(SettingsUtil.KEY_LOCATION_DIALOG, value);
        }
        editor.commit();
    }

    public static boolean askInternet() {
        return !settings.getBoolean(KEY_INTERNET_DIALOG, false);
    }

    public static boolean askLocation() {
        return !settings.getBoolean(KEY_LOCATION_DIALOG, false);
    }

    public static boolean hasConnectToInternet() {
        return settings.getBoolean(KEY_INTERNET_CONNECTION, false);
    }

    public static boolean hasConnectToLocation() {
        return settings.getBoolean(KEY_LOCATION_CONNECTION, false);
    }
}
