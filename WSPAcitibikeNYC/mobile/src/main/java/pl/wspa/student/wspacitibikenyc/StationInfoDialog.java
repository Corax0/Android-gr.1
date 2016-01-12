package pl.wspa.student.wspacitibikenyc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Karolina i Daniel on 2015-12-07.
 */
public class StationInfoDialog extends DialogFragment {
    private LinearLayout layoutAvailableDocks;
    private LinearLayout layoutAvailableBikes;
    private LinearLayout layoutLastCommunicationTime;

    private TextView id;
    private TextView stationName;
    private TextView availableDocks;
    private TextView totalDocks;
    private TextView statusValueText;
    private ImageView statusValueImg;
    private TextView availableBikes;
    private TextView lastCommunicationTime;
    private TextView distance;
    private TextView unit;
    private Button show;
    private Button navigate;
    private Button favourite;
    private Button cancel;

    View view;
    private Station s;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view=inflater.inflate(R.layout.dialog_station_info, null);
        builder.setView(view);
        builder.setTitle(getString(R.string.dialog_station_info_title)+": "+s.getId());
        setupChilden();
        setupStation();
        return builder.create();
    }

    private void setupChilden() {
        layoutAvailableDocks = (LinearLayout)view.findViewById(R.id.rs_layout_availableDocks);
        layoutAvailableBikes = (LinearLayout)view.findViewById(R.id.rs_layout_availableBikes);
        layoutLastCommunicationTime = (LinearLayout)view.findViewById(R.id.rs_layout_lastCommunicationTime);

        //id = (TextView)view.findViewById(R.id.rs_value_id);
        stationName = (TextView)view.findViewById(R.id.rs_value_stationName);
        availableDocks = (TextView)view.findViewById(R.id.rs_value_availableDocks);
        totalDocks = (TextView)view.findViewById(R.id.rs_value_totalDocks);
        statusValueText =(TextView)view.findViewById(R.id.rs_value_statusValue_text);
        statusValueImg =(ImageView)view.findViewById(R.id.rs_value_statusValue_img);
        availableBikes = (TextView)view.findViewById(R.id.rs_value_availableBikes);
        lastCommunicationTime = (TextView)view.findViewById(R.id.rs_value_lastCommunicationTime);
        distance = (TextView)view.findViewById(R.id.rs_value_distance);
        unit = (TextView)view.findViewById(R.id.rs_label_unit);
        show = (Button)view.findViewById(R.id.rs_show);
        navigate = (Button)view.findViewById(R.id.rs_navigate);
        favourite = (Button)view.findViewById(R.id.rs_favourite);
        cancel = (Button)view.findViewById(R.id.rs_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    public void setupStation(){
        //id.setText(s.getId());
        stationName.setText(s.getStationName());
        switch(s.getStatusKey()){
            case 1:
                availableDocks.setText(s.getAvailableDocks());
                totalDocks.setText(s.getTotalDocks());
                availableBikes.setText(s.getAvailableBikes());
                lastCommunicationTime.setText(s.getLastCommunicationTime());
                statusValueText.setText(R.string.station_status_value_1);
                statusValueImg.setImageResource(android.R.drawable.presence_online);
                break;
            case 2:
                statusValueText.setText(R.string.station_status_value_2);
                statusValueImg.setImageResource(android.R.drawable.presence_away);
                layoutAvailableDocks.setVisibility(View.GONE);
                layoutAvailableBikes.setVisibility(View.GONE);
                layoutLastCommunicationTime.setVisibility(View.GONE);
                break;
            case 3:
                statusValueText.setText(R.string.station_status_value_3);
                statusValueImg.setImageResource(android.R.drawable.presence_busy);
                lastCommunicationTime.setText(s.getLastCommunicationTime());
                layoutAvailableDocks.setVisibility(View.GONE);
                layoutAvailableBikes.setVisibility(View.GONE);
                break;
        }
        Double dist=s.getDistance(); //TODO obliczenie odległości od punku 0 (my) do stacji
        if(dist>1000) {
            dist/=1000;
            unit.setText("km");
        }
        else
            unit.setText("m");
        distance.setText(dist.toString());
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO przejście do mapy
                Log.i("DIALOG","show");
            }
        });
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO przejście do nawigacji
                Log.i("DIALOG","nav");
            }
        });
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO dodanie do ulubionych
                Log.i("DIALOG","fav");
            }
        });
    }

    public void setStation(Station s) {
        this.s = s;
    }
}
