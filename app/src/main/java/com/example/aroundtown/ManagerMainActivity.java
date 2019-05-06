package com.example.aroundtown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;

public class ManagerMainActivity extends AppCompatActivity {

    ImageButton AddEventButton;
    ImageButton calendarButton;
    ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);

        AddEventButton = (ImageButton) findViewById(R.id.ButtonAddEvent);
        AddEventButton.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),CreateActivity.class);
                startActivity(intent);

            }
        });

        calendarButton = (ImageButton) findViewById(R.id.ButtonCalendar);
        calendarButton.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
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
}
