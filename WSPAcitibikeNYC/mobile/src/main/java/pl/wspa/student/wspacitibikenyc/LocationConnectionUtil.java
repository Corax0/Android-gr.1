package pl.wspa.student.wspacitibikenyc;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;

/**
 * Created by Daniel on 2015-11-25.
 */
public class LocationConnectionUtil {

    public static boolean isConnectedToGPS(Context context){
        LocationManager lm =(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static boolean hasGpsModule(Context context){
        PackageManager packMan = context.getPackageManager();
        return packMan.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
    }
}
