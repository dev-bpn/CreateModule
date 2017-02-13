package com.mobotechnology.www.createlibrary;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.mobotechnology.www.myvolleylibrary.events.VolleyResponseEventBus;
import com.mobotechnology.www.myvolleylibrary.request.VolleyGetRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements VolleyResponseEventBus {

    private ProgressDialog progressDialog;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        progressDialog.show();
        VolleyGetRequest volleyGetRequest = new VolleyGetRequest();
        volleyGetRequest.hitServer(this);


    }

    private void initViews() {

        /** Initialize Views*/
        textView = (TextView) findViewById(R.id.textView);

        /** initialize progress dialog*/
        initializeProgressDialog();

    }


    private void initializeProgressDialog() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);

    }


    @Override
    public void onResponseSuccess(JSONObject jsonObject) {

        progressDialog.hide();
        textView.setText(jsonObject.toString());

    }


    @Override
    public void onErrorResponse(VolleyError volleyError) {

        progressDialog.hide();
        textView.setText(volleyError.getMessage());

    }


}
