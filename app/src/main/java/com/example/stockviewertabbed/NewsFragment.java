package com.example.stockviewertabbed;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsFragment extends Fragment {
    ArrayList<News> news = new ArrayList<>();

    ArrayList<String> arrayList = new ArrayList<>();
    
    NewsAdapter newsAdapter;
    RecyclerView newsRecyclerview;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.newsfragment_layout,container,false);

        return view;
    }

    private void getNewsResponce() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewsAPI.BASE_URL)
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsAPI newsAPI = retrofit.create(NewsAPI.class);
        Call<List<News>> call = newsAPI.getNews();

        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                news = new ArrayList<>(response.body());
                newsAdapter =new NewsAdapter(context,news);
                newsRecyclerview.setAdapter(newsAdapter);
                newsRecyclerview.getAdapter().notifyDataSetChanged();
                Toast.makeText(getActivity(), "Success news", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.d("Error:", t.getMessage());
                Toast.makeText(getActivity(), "Error news", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getNewsResponce();
        newsRecyclerview=view.findViewById(R.id.news_recycleview);
        layoutManager = new LinearLayoutManager(getContext());
        newsRecyclerview.setLayoutManager(layoutManager);
    }

    @Override
    public void onAttach(@NonNull Context mcontext) {
        super.onAttach(context);
        context = mcontext;
    }
}
