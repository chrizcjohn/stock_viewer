package com.example.stockviewertabbed;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.service.autofill.LuhnChecksumValidator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stockviewertabbed.ui.main.SectionsPagerAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<Bitcoin> bitcoins =new ArrayList<>();
     Cryptoadapter cryptoadapter;
     RecyclerView crypto_recycleview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
//        crypto_recycleview =(RecyclerView)findViewById(R.id.crypto_recycleview);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//
//        crypto_recycleview.setLayoutManager(layoutManager);




        getCyrptoresponse();

    }

    private void getCyrptoresponse() {
        HttpLoggingInterceptor httpLoggingInterceptor =new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();

        Api api =retrofit.create(Api.class);
        Call<List<Bitcoin>> call = api.getBitcoins();

        call.enqueue(new Callback<List<Bitcoin>>() {
            @Override
            public void onResponse(Call<List<Bitcoin>> call, Response<List<Bitcoin>> response) {

                bitcoins=new ArrayList<>(response.body());
                cryptoadapter=new Cryptoadapter(MainActivity.this,bitcoins);
                crypto_recycleview.setAdapter(cryptoadapter);
                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Bitcoin>> call, Throwable t) {
                Log.d("error:", t.getMessage());
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}






