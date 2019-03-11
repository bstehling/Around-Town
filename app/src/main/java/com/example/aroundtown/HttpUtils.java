package com.example.aroundtown;
import com.loopj.android.http.*;

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
}
