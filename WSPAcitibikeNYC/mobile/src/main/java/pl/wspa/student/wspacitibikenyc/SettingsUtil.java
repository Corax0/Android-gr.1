package pl.wspa.student.wspacitibikenyc;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Daniel on 2015-12-04.
 */
public class SettingsUtil {
    public static final String KEY_INTERNET_OFF="internet_off",
                               KEY_LOCATION_OFF="location_off";
    public static boolean askInternet, askLocation;

    public static void loadSettings(Context context){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        askInternet =settings.getBoolean(KEY_INTERNET_OFF, false);
        askLocation =settings.getBoolean(KEY_LOCATION_OFF, false);
    }
}
