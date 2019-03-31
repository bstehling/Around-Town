package com.example.aroundtown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testButton = (ImageButton) findViewById(R.id.ButtonMap);
        testButton.setOnClickListener(new View.OnClickListener()    {
            public void onClick(View v) {
/*
                Toast.makeText(MainActivity.this, "It Works", Toast.LENGTH_LONG).show();
*/
                setContentView(R.layout.activity_maps);
            }
        });

    }
    //Red Vines, What can't they do?
}
