package com.example.aroundtown;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class HttpUtils {
    private static final String BASE_URL = "http://165.227.54.221/api/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url),responseHandler);
    }

    public static void post (String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getByUrl(String url, AsyncHttpResponseHandler responseHandler) {
        client.get(url, responseHandler);
    }

    public static void postByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.post(url, params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl){
        return BASE_URL + relativeUrl;
    }

    /*
    public void getEventItems(){
        get("events",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    getEventName(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    public String[] getEventName(JSONArray data){
        ArrayList<String> eventArrayList = new ArrayList<>();
        for (int i = 0; i < data.length(); i++){
            try {
                JSONObject finalObject = new JSONObject(data.getString(i));
                String name = finalObject.getString("name");
                eventArrayList.add(name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return eventArrayList.toArray(new String[0]);
    }

    public String[] getEventDetail(JSONArray data){
        ArrayList<String> eventArrayList = new ArrayList<>();
        for (int i = 0; i < data.length(); i++){
            try{
                JSONObject finalObject = new JSONObject(data.getString(i));
                String description = finalObject.getString("info");
                eventArrayList.add(description);
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return eventArrayList.toArray(new String[0]);
    }

    public Integer[] getCategoryImage(JSONArray data){
        ArrayList<String> eventArrayList = new ArrayList<>();
        ArrayList<Integer> eventImageList = new ArrayList<>();
        for (int i = 0; i < data.length(); i++){
            try{
                JSONObject finalObject = new JSONObject(data.getString(i));
                String description = finalObject.getString("category");
                eventArrayList.add(description);
            }catch(JSONException e){
                e.printStackTrace();
            }
        }

        for(int i =0; i < eventArrayList.size(); i++){
            if (eventArrayList.get(i).equals("cardgame")){
                eventImageList.add(R.drawable.cardgame);
            }
            else if (eventArrayList.get(i).equals("computer")){
                eventImageList.add(R.drawable.computer);
            }
            else if (eventArrayList.get(i).equals("concert")){
                eventImageList.add(R.drawable.concert);
            }
        }
        return eventImageList.toArray(new Integer[0]);
    }



    public void readData(final dataCallback dataCallback){
        get("events",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    dataCallback.onCallback(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface dataCallback{
        void onCallback(JSONArray data);
    }
}
