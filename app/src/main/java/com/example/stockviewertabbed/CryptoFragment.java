package com.example.stockviewertabbed;


        import android.content.Context;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Toast;


        import androidx.annotation.NonNull;;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.google.gson.annotations.SerializedName;

        import java.util.ArrayList;
        import java.util.List;

        import okhttp3.OkHttpClient;
        import okhttp3.logging.HttpLoggingInterceptor;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;


public class CryptoFragment extends Fragment {
    ArrayList<Bitcoin> bitcoins = new ArrayList<>();
    Cryptoadapter cryptoadapter;
    RecyclerView crypto_recycleview;
    RecyclerView.LayoutManager layoutManager;
    Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cryptofragment_layout, container, false);
        getCyrptoresponse();
        crypto_recycleview = view.findViewById(R.id.crypto_recycleview);
        layoutManager = new LinearLayoutManager(getContext());
        crypto_recycleview.setLayoutManager(layoutManager);
        return view;
    }
    private void getCyrptoresponse() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Bitcoin>> call = api.getBitcoins();

        call.enqueue(new Callback<List<Bitcoin>>() {
            @Override
            public void onResponse(Call<List<Bitcoin>> call, Response<List<Bitcoin>> response) {

                bitcoins = new ArrayList<>(response.body());

                cryptoadapter = new Cryptoadapter(context,bitcoins);
                crypto_recycleview.setAdapter(cryptoadapter);

                Log.e("Values", bitcoins.get(0).getName());
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<List<Bitcoin>> call, Throwable t) {
                Log.d("Error:", t.getMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context mcontext) {
        super.onAttach(context);
        context=mcontext;
    }
}
