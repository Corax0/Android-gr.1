package pl.wspa.student.wspacitibikenyc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Daniel on 2015-12-03.
 */
public class StationView extends LinearLayout{
    private LinearLayout layoutAvailableDocks;
    private LinearLayout layoutAvailableBikes;
    private LinearLayout layoutLastCommunicationTime;

    private TextView id;
    private TextView stationName;
    private TextView availableDocks;
    private TextView totalDocks;
    private View statusValue;
    private TextView availableBikes;
    private TextView lastCommunicationTime;
    private TextView distance;
    private TextView unit;
    private Button show;
    private Button navigate;

    boolean extended;
    Station station;

    public StationView(Context context) {
        super(context);
        init();
    }

    public StationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        extended=false;
        setupLayout();
        setupChildren();
    }
    private void setupLayout(){
        if(extended)
            LayoutInflater.from(getContext()).inflate(R.layout.row_station_extended, this, true);
        else
            LayoutInflater.from(getContext()).inflate(R.layout.row_station_narrowed, this, true);
    }
    private void setupChildren() {
        layoutAvailableDocks = (LinearLayout)findViewById(R.id.rs_layout_availableDocks);
        layoutAvailableBikes = (LinearLayout)findViewById(R.id.rs_layout_availableBikes);
        layoutLastCommunicationTime = (LinearLayout)findViewById(R.id.rs_layout_lastCommunicationTime);

        id = (TextView)findViewById(R.id.rs_value_id);
        stationName = (TextView)findViewById(R.id.rs_value_stationName);
        availableDocks = (TextView)findViewById(R.id.rs_value_availableDocks);
        totalDocks = (TextView)findViewById(R.id.rs_value_totalDocks);
        statusValue =findViewById(R.id.rs_value_statusValue);
        availableBikes = (TextView)findViewById(R.id.rs_value_availableBikes);
        lastCommunicationTime = (TextView)findViewById(R.id.rs_value_lastCommunicationTime);
        distance = (TextView)findViewById(R.id.rs_value_distance);
        unit = (TextView)findViewById(R.id.rs_label_unit);
        show = (Button)findViewById(R.id.rs_show);
        navigate = (Button)findViewById(R.id.rs_navigate);
    }
    public void setStation(Station s){
        station=s;
        id.setText(s.getId());
        stationName.setText(s.getStationName());
        switch(s.getStatusKey()){
            case 1:
                availableDocks.setText(s.getAvailableDocks());
                totalDocks.setText(s.getTotalDocks());
                availableBikes.setText(s.getAvailableBikes());
                lastCommunicationTime.setText(s.getLastCommunicationTime());
                if(statusValue instanceof TextView)
                    ((TextView)statusValue).setText(R.string.station_status_value_1);
                else
                    ((ImageView)statusValue).setImageResource(android.R.drawable.presence_online);
                break;
            case 2:
                if(statusValue instanceof TextView)
                    ((TextView)statusValue).setText(R.string.station_status_value_2);
                else
                    ((ImageView)statusValue).setImageResource(android.R.drawable.presence_away);
                layoutAvailableDocks.setVisibility(View.GONE);
                layoutAvailableBikes.setVisibility(View.GONE);
                layoutLastCommunicationTime.setVisibility(View.GONE);
                break;
            case 3:
                if(statusValue instanceof TextView)
                    ((TextView)statusValue).setText(R.string.station_status_value_3);
                else
                    ((ImageView)statusValue).setImageResource(android.R.drawable.presence_busy);
                lastCommunicationTime.setText(s.getLastCommunicationTime());
                layoutAvailableDocks.setVisibility(View.GONE);
                layoutAvailableBikes.setVisibility(View.GONE);
                break;
        }
        Double dist=0.0; //TODO obliczenie odległości od punku 0 (my) do stacji
        distance.setText(dist.toString());
        if(dist>1000) {
            dist/=1000;
            unit.setText("km");
        }
        else
            unit.setText("m");

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO przejście do mapy
            }
        });
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO przejście do nawigacji
            }
        });
    }

    public static StationView inflate(ViewGroup parent) {
        return (StationView)LayoutInflater.from(parent.getContext()).inflate(R.layout.row_station, parent, false);
    }
}
