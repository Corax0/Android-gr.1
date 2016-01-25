package pl.wspa.student.wspacitibikenyc;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by Adrian Bieliński on 2016-01-25.
 */
public class NavigateToActivity extends ActionBarActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate_to);

        Button stationList = (Button) findViewById(R.id.button_station_list);
        stationList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), StationListActivity.class));
            }
        });

        Button map = (Button) findViewById(R.id.button_map);
        map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), StationMapActivity.class));
            }
        });

        Button marks = (Button) findViewById(R.id.button_own_marks);
        marks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), StationListActivity.class));
            }
        });

        Button find = (Button) findViewById(R.id.button_find);
        find.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), MainActivity.class));
            }
        });


    }

}
