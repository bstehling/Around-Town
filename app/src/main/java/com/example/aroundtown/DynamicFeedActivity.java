package com.example.aroundtown;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aroundtown.util.ViewVenueActivity;

import org.json.JSONArray;

import java.util.Arrays;

public class DynamicFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_feed);

        ListView listView = (ListView) findViewById(R.id.eventList);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               String data = adapterView.getItemAtPosition(i).toString();
               data = data.replaceAll("\\[","");
               data = data.replaceAll("\\]","");
               String[] splitdata = data.split(",");
               Log.d("data", splitdata[0]);
                //start next intent;
                Intent intent = new Intent(getApplicationContext(),ViewVenueActivity.class);
                intent.putExtra("eventName", splitdata[0]);
                intent.putExtra("description", splitdata[1]);

                startActivity(intent);

            }
        });
    }

    class CustomAdapter extends BaseAdapter{
        int countLength = 1;
        String[] NAMES,DESCRIPTION;
        Integer[] IMAGES;
        @Override
        public int getCount() {
            return countLength;
        }

        @Override
        public Object getItem(int position) {
            String[] ray = new String[2];
            ray[0]=NAMES[position];
            ray[1] =DESCRIPTION[position];
            Log.d("data", Arrays.toString(ray));
            return Arrays.toString(ray);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.customlayout,null);
            final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
            final TextView textView_name = (TextView) convertView.findViewById(R.id.textView_name);
            final TextView textView_description = (TextView) convertView.findViewById(R.id.textView_description);

            final HttpUtils httpUtils = new HttpUtils();

            httpUtils.readData(new HttpUtils.dataCallback() {
                @Override
                public void onCallback(JSONArray data) {
                    NAMES = httpUtils.getEventName(data);
                    countLength = NAMES.length;
                    DESCRIPTION = httpUtils.getEventDetail(data);
                    IMAGES = httpUtils.getCategoryImage(data);
                    textView_name.setText(NAMES[position]);
                    imageView.setImageResource(IMAGES[position]);
                    textView_description.setText(DESCRIPTION[position]);
                }
            });

            return convertView;
        }
    }
}
