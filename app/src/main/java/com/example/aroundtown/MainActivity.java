package com.example.aroundtown;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;

import com.example.aroundtown.util.ViewVenueActivity;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.JsonHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ImageButton mapButton;
    ImageButton feedButton;
    ImageButton calendarButton;
    ImageButton searchButton;
    ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNotification();

        mapButton = (ImageButton) findViewById(R.id.ButtonMap);
        mapButton.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v) {

                //Toast.makeText(MainActivity.this, "It Works 1", Toast.LENGTH_LONG).show();
                //setContentView(R.layout.activity_maps);
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);

            }
        });

        feedButton = (ImageButton) findViewById(R.id.ButtonFeed);
        feedButton.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),DynamicFeedActivity.class);
                startActivity(intent);

            }
        });

        calendarButton = (ImageButton) findViewById(R.id.ButtonCalendar);
        calendarButton.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CalendarActivity.class);
                startActivity(intent);

            }
        });

        searchButton = (ImageButton) findViewById(R.id.ButtonSearch);
        searchButton.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Search.class);
                startActivity(intent);

            }
        });

        settingsButton = (ImageButton) findViewById(R.id.ButtonSettings);
        settingsButton.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v) {

/*
                Toast.makeText(MainActivity.this, "This is settings button", Toast.LENGTH_SHORT).show();
*/
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);

            }
        });
    }
    final HttpUtils httpUtils = new HttpUtils();

    protected void addNotification() {

        HttpUtils.get("events", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray data = response.getJSONArray("data");
                    Log.d("data", data.length() + "");
                    for (int x = 0; x < data.length(); x++) {

                        JSONObject venue = data.getJSONObject(x);
                        String VenueName = venue.getString("venue");
                        String annoucementTime = venue.getString("announceTime");
                        String[] announce = annoucementTime.split(";", 2);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.logo);
        builder.setContentTitle("Notifications Example");
        builder.setContentText("This is a test notificiation");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        //Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    //Red Vines, What can't they do?
}
