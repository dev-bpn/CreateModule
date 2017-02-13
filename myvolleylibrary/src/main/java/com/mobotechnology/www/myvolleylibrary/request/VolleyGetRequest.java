package com.mobotechnology.www.myvolleylibrary.request;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mobotechnology.www.myvolleylibrary.events.VolleyResponseEventBus;

import org.json.JSONObject;

/**
 * Created by bipin on 2/13/17.
 */

public class VolleyGetRequest {

    private String url = "http://sms8383.com/appmodule/appcorpolist";
    private VolleyResponseEventBus volleyResponseEventBus;

    public void hitServer(Context context) {

        RequestQueue queue = Volley.newRequestQueue(context);
        volleyResponseEventBus = (VolleyResponseEventBus) context;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        volleyResponseEventBus.onResponseSuccess(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        volleyResponseEventBus.onErrorResponse(error);

                    }
                });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(jsonObjectRequest);

    }


}
