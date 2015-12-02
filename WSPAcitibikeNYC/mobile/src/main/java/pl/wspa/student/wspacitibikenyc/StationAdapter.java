package pl.wspa.student.wspacitibikenyc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tomasz on 17.09.2015.
 * Stolen by Piotrek on 2015-11-04.
 * Read by Adrian on 2015-11-20.*/
public class StationAdapter extends ArrayAdapter<Station> {
    static class ViewHolder {
        private LinearLayout layoutAvailableDocks;
        private LinearLayout layoutAvailableBikes;
        private LinearLayout layoutLastCommunicationTime;

        private TextView id;
        private TextView stationName;
        private TextView availableDocks;
        private TextView totalDocks;
        private TextView statusValue;
        private TextView availableBikes;
        private TextView lastCommunicationTime;
        private TextView distance;
        private TextView unit;
        private Button show;
        private Button navigate;
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
            holder.layoutAvailableDocks = (LinearLayout) v.findViewById(R.id.rs_layout_availableDocks);
            holder.layoutAvailableBikes = (LinearLayout) v.findViewById(R.id.rs_layout_availableBikes);
            holder.layoutLastCommunicationTime = (LinearLayout) v.findViewById(R.id.rs_layout_lastCommunicationTime);

            holder.id = (TextView) v.findViewById(R.id.rs_value_id);
            holder.stationName = (TextView) v.findViewById(R.id.rs_value_stationName);
            holder.availableDocks = (TextView) v.findViewById(R.id.rs_value_availableDocks);
            holder.totalDocks = (TextView) v.findViewById(R.id.rs_value_totalDocks);
            holder.statusValue = (TextView) v.findViewById(R.id.rs_value_statusValue);
            holder.availableBikes = (TextView) v.findViewById(R.id.rs_value_availableBikes);
            holder.lastCommunicationTime = (TextView) v.findViewById(R.id.rs_value_lastCommunicationTime);
            holder.distance = (TextView) v.findViewById(R.id.rs_value_distance);
            holder.unit = (TextView) v.findViewById(R.id.rs_label_unit);
            holder.show = (Button) v.findViewById(R.id.rs_show);
            holder.navigate = (Button) v.findViewById(R.id.rs_navigate);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        Station s=stationList.get(position);
        holder.id.setText(s.getId());
        holder.stationName.setText(s.getStationName());
        switch(s.getStatusKey()){
            case 1:
                holder.availableDocks.setText(s.getAvailableDocks());
                holder.totalDocks.setText(s.getTotalDocks());
                holder.availableBikes.setText(s.getAvailableBikes());
                holder.lastCommunicationTime.setText(s.getLastCommunicationTime());
                holder.statusValue.setText(R.string.station_status_value_1);
                break;
            case 2:
                holder.statusValue.setText(R.string.station_status_value_2);
                holder.layoutAvailableDocks.setVisibility(View.GONE);
                holder.layoutAvailableBikes.setVisibility(View.GONE);
                holder.layoutLastCommunicationTime.setVisibility(View.GONE);
                break;
            case 3:
                holder.statusValue.setText(R.string.station_status_value_3);
                holder.lastCommunicationTime.setText(s.getLastCommunicationTime());
                holder.layoutAvailableDocks.setVisibility(View.GONE);
                holder.layoutAvailableBikes.setVisibility(View.GONE);
                break;
        }
        Double dist=0.0; //TODO obliczenie odległości od punku 0 (my) do stacji
        holder.distance.setText(dist.toString());
        if(dist>1000) {
            dist/=1000;
            holder.unit.setText("km");
        }
        else
            holder.unit.setText("m");


        holder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO pokazanie na mapie
            }
        });
        holder.navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO przejście do nawigacji
            }
        });

        return v;
    }


}
