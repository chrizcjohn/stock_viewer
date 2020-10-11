package com.example.stockviewertabbed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsAPI {
    String BASE_URL="https://newsapi.org/v2/";
    @GET("everything?q=stock&apiKey=26ca94862fa344a5927833eafb32374b")
    Call<List<News>> getNews();
}
