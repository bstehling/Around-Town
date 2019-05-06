package com.example.aroundtown;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

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

    public String[] getUserAuth(JSONArray data){
        ArrayList<String> userArrayList = new ArrayList<>();
        for (int i = 0; i < data.length(); i++){
            try {
                JSONObject finalObject = new JSONObject(data.getString(i));
                String auth = finalObject.getString("auth");
                userArrayList.add(auth);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return userArrayList.toArray(new String[0]);
    }

    public String[] getUserEmail(JSONArray data){
        ArrayList<String> userArrayList = new ArrayList<>();
        for (int i = 0; i < data.length(); i++){
            try {
                JSONObject finalObject = new JSONObject(data.getString(i));
                String email = finalObject.getString("email");
                userArrayList.add(email);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return userArrayList.toArray(new String[0]);
    }

    public String[] getUserPass(JSONArray data){
        ArrayList<String> userArrayList = new ArrayList<>();
        for (int i = 0; i < data.length(); i++){
            try {
                JSONObject finalObject = new JSONObject(data.getString(i));
                String pass = finalObject.getString("password");
                userArrayList.add(pass);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return userArrayList.toArray(new String[0]);
    }

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
            HashSet<String> h = new HashSet<String>();
            StringTokenizer st = new StringTokenizer(eventArrayList.get(i),",");
            while(st.hasMoreTokens())
            {
                h.add(st.nextToken());
            }

            if (h.contains("Games") || h.contains("Trivia") || h.contains("Sports")){
                eventImageList.add(R.drawable.cardgame);
            }
            else if (h.contains("Food and Drink") || h.contains("Bar Event")){
                eventImageList.add(R.drawable.computer);
            }
            else if (h.contains("Concert") || h.contains("Comedy") || h.contains("Movie")){
                eventImageList.add(R.drawable.concert);
            }
            else{
                eventImageList.add(R.drawable.concert);
            }
        }
        return eventImageList.toArray(new Integer[0]);
    }

    public void readDataUsers(final dataCallback dataCallback){
        get("users",new JsonHttpResponseHandler(){
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
