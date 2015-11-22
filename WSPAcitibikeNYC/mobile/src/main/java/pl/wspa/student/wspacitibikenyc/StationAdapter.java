package pl.wspa.student.wspacitibikenyc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tomasz on 17.09.2015.
 * Stolen by Piotrek on 2015-11-04.
 * Read by Adrian on 2015-11-20.*/
public class StationAdapter extends ArrayAdapter<Station> {
    static class ViewHolder {
        /*TODO elementy wiersza w liscie
        public TextView stationName;
        public TextView availableDocks;
        public TextView availableBikes;
        public TextView statusValue;
        */
    }

    ArrayList<Station> stationList;
    LayoutInflater view;
    int resource;
    ViewHolder holder;

    public StationAdapter(Context context, int resource, ArrayList<Station> objects) {
        super(context, resource, objects);
        this.view = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
        this.stationList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = view.inflate(resource, null);
            /*TODO elementy wiersza w liście
            holder.stationName = (TextView) v.findViewById(R.id.stationName);
            */
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        /*TODO elementy wiersza w liście
        holder.stationName.setText(R.string.station_name+": " + stationList.get(position).getStationName());
        */

        return v;
    }


}
