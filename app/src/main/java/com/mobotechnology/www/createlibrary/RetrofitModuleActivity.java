package com.mobotechnology.www.createlibrary;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mobotechnology.www.myretrofitlibrary.model.Movie;
import com.mobotechnology.www.myretrofitlibrary.model.MoviesResponse;
import com.mobotechnology.www.myretrofitlibrary.rest.ApiClient;
import com.mobotechnology.www.myretrofitlibrary.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitModuleActivity extends AppCompatActivity implements Callback<MoviesResponse> {


    private static final String TAG = RetrofitModuleActivity.class.getSimpleName();
    private ProgressDialog progressDialog;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_module);

        initViews();

        progressDialog.show();
        callApiUsingRetrofitModule();

    }


    private void initViews() {

        /** Title to actionbar*/
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Retrofit Library Module");

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


    /**
     * Call APIs using RETROFIT library
     */
    private void callApiUsingRetrofitModule() {

        String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(this);

    }


    @Override
    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

        List<Movie> movies = response.body().getResults();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < movies.size(); i++) {
            String titleName = movies.get(i).getTitle() + "\n";
            stringBuilder.append(titleName);
        }
        textView.setText(stringBuilder);
        progressDialog.dismiss();

    }


    @Override
    public void onFailure(Call<MoviesResponse> call, Throwable t) {

        textView.setText(t.getMessage());
        progressDialog.dismiss();

    }
}
