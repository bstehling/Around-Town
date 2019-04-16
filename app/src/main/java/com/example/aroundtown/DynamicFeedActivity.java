package com.example.aroundtown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

public class DynamicFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_feed);

        ListView listView = (ListView) findViewById(R.id.eventList);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    class CustomAdapter extends BaseAdapter{
        int countLength = 1;

        @Override
        public int getCount() {
            return countLength;
        }

        @Override
        public Object getItem(int position) {
            return null;
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
                    String[] NAMES = httpUtils.getEventName(data);
                    countLength = NAMES.length;
                    String[] DESCRIPTION = httpUtils.getEventDetail(data);
                    Integer[] IMAGES = httpUtils.getCategoryImage(data);
                    textView_name.setText(NAMES[position]);
                    imageView.setImageResource(IMAGES[position]);
                    textView_description.setText(DESCRIPTION[position]);
                }
            });

            return convertView;
        }
    }
}
