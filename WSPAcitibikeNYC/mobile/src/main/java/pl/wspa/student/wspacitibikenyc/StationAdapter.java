package pl.wspa.student.wspacitibikenyc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Tomasz on 17.09.2015.
 * Stolen by Piotrek on 2015-11-04.
 * Read by Adrian on 2015-11-20.*/
public class StationAdapter extends ArrayAdapter<Station> {
    LayoutInflater view;

    public StationAdapter(Context context, ArrayList<Station> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        StationView v = (StationView)convertView;
        if (v == null) {
            v = StationView.inflate(parent);
        }
        v.setStation(getItem(position));

        return v;
    }


}
