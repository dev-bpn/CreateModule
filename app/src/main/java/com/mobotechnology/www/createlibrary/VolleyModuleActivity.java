package com.mobotechnology.www.createlibrary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.mobotechnology.www.myvolleylibrary.events.VolleyResponseEventBus;
import com.mobotechnology.www.myvolleylibrary.request.VolleyGetRequest;

import org.json.JSONObject;

public class VolleyModuleActivity extends AppCompatActivity implements VolleyResponseEventBus {

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
        textView.setText(volleyError.toString());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.retrofitModule) {


            callApiUsingRetrofit();


        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Call APIs using RETROFIT
     */
    private void callApiUsingRetrofit() {

        startActivity(new Intent(this, RetrofitModuleActivity.class));

    }

}
