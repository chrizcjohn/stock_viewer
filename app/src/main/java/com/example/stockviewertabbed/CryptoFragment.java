package com.example.stockviewertabbed;

        import android.app.DownloadManager;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.RecyclerView;

        import com.google.gson.annotations.SerializedName;


public class CryptoFragment extends Fragment {
    RecyclerView crypto_recycleview;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.cryptofragment_layout,container,false);
        crypto_recycleview =view.findViewById(R.id.crypto_recycleview);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//
//        crypto_recycleview.setLayoutManager(layoutManager);
        return view;
}
}
