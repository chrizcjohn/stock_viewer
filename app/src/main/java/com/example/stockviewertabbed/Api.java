package com.example.stockviewertabbed;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL=  "https://api.coingecko.com/api/v3/";
    @GET("coins/markets?vs_currency=inr&order=market_cap_desc&per_page=5&page=1&sparkline=false")
    Call<List<Bitcoin>> getBitcoins();

}
