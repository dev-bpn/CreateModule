package com.mobotechnology.www.myvolleylibrary.events;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by bipin on 2/13/17.
 */

public interface VolleyResponseEventBus {


    /**
     * Volley OnResponseSuccess
     */
    void onResponseSuccess(JSONObject jsonObject);


    /**
     * onResponseFailed
     */
    void onErrorResponse(VolleyError volleyError);


}
