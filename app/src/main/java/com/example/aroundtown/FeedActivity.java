package com.example.aroundtown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        getItems(0);

    }

    public void getItems(final int index){

        HttpUtils.get("events", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    getIndexedEvent(data,index);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getIndexedEvent(JSONArray data,int index){
        try {
            Log.d("data", "this is the data" + data.get(index));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
