package com.mobotechnology.www.myvolleylibrary.request;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobotechnology.www.myvolleylibrary.events.VolleyResponseEventBus;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bipin on 2/13/17.
 */

public class VolleyGetRequest {

    private String url = "http://samples.openweathermap.org/data/2.5/find?q=London&appid=b1b15e88fa797225412429c1c50c122a1";
    private VolleyResponseEventBus volleyResponseEventBus;

    public void hitServer(Context context) {

        RequestQueue queue = Volley.newRequestQueue(context);
        volleyResponseEventBus = (VolleyResponseEventBus) context;

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            volleyResponseEventBus.onResponseSuccess(new JSONObject(response));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        volleyResponseEventBus.onErrorResponse(error);

                    }
                });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(jsonObjectRequest);

    }


}
