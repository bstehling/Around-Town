package com.example.aroundtown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton mapButton;
    ImageButton feedButton;
    ImageButton calendarButton;
    ImageButton searchButton;
    ImageButton settingsButton;
    ImageButton testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                Toast.makeText(MainActivity.this, "This is calendar button", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(getApplicationContext(),CalendarActivity.class);
                startActivity(intent);*/

            }
        });

        searchButton = (ImageButton) findViewById(R.id.ButtonSearch);
        searchButton.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "This is search button", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);*/

            }
        });

        settingsButton = (ImageButton) findViewById(R.id.ButtonSettings);
        settingsButton.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "This is settings button", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);*/

            }
        });

        testButton = (ImageButton) findViewById(R.id.ButtonTEST);
        testButton.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),CreateActivity.class);
                startActivity(intent);

            }
        });

    }
    //Red Vines, What can't they do?
}
