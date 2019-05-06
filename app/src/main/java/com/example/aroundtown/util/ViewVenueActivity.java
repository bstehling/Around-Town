package com.example.aroundtown.util;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aroundtown.HttpUtils;
import com.example.aroundtown.R;

import org.json.JSONArray;

public class ViewVenueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_venue);

        String eventName = getIntent().getStringExtra("eventName");
        String eventDescription = getIntent().getStringExtra("description");

        TextView event_title = (TextView)findViewById(R.id.eventName);
        event_title.setText(eventName);

        TextView description = (TextView)findViewById(R.id.editDescription);
        description.setText((eventDescription));
    }
}
