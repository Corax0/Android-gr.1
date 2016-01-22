package pl.wspa.student.wspacitibikenyc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.FrameLayout;
//import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Kontroler do formy z ogólnej mapa
 */
public class MainMapActivity extends ActionBarActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
//    private ImageView mStationPointer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
/*
    //nie jestem pewien gdzie to wrzucić więc na razie wrzucam to tu;
    //+ brak pomysłu jak wywoływać metodę --Darek

    @Override
    public void ShowStationLocation(Location location) {
        double latitude = (double) (location.getLatitude() * 1e6);
        double longitude = (double) (location.getLongitude()* 1e6);

        MapView.LayoutParams lp = new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT,
                MapView.LayoutParams.WRAP_CONTENT, new GeoPoint(latitude,longitude),
                MapView.LayoutParams.CENTER);

        if (mStationPointer == null){
            mStationPointer = new ImageView(this);
            //mStationPointer.setImageResource(R.drawable.ic_maps_indicator_current_position);
            mMapView.addView(mStationPointer,lp);
        } else {
            mStationPointer.setLayoutParams(lp);
        }
    }*/
}
