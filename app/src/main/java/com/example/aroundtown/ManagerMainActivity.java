package com.example.aroundtown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ManagerMainActivity extends AppCompatActivity {

    ImageButton testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);

        testButton = (ImageButton) findViewById(R.id.ButtonAddEvent);
        testButton.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),CreateActivity.class);
                startActivity(intent);

            }
        });
    }
}
