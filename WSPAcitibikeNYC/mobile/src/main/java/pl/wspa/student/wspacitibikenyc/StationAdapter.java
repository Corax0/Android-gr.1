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
 */
public class StationAdapter extends ArrayAdapter<Station> {
    ArrayList<Station> stationList;
    LayoutInflater view;
    int Resource;
    ViewHolder holder;

    public StationAdapter(Context context, int resource, ArrayList<Station> objects) {
        super(context, resource, objects);
        view = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        stationList = objects;
    }





    static class ViewHolder {
        public TextView stationName;
        public TextView availableDocks;
        public TextView availableBikes;
        public TextView statusValue;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = view.inflate(Resource, null);
            holder.stationName = (TextView) v.findViewById(R.id.stationName);
            holder.availableDocks = (TextView) v.findViewById(R.id.availableDocks);
            holder.availableBikes = (TextView) v.findViewById(R.id.availableBikes);
            holder.statusValue = (TextView) v.findViewById(R.id.statusValue);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.stationName.setText("Nazwa stacji: " + stationList.get(position).getStationName());
        holder.availableDocks.setText("Wolnych miejsc: " + stationList.get(position).getAvailableDocks());
        holder.availableBikes.setText("Dostêpnych rowerów: " + stationList.get(position).getAvailableBikes());
        holder.statusValue.setText("Status stacji: " + stationList.get(position).getStatusValue());

        return v;
    }


}
