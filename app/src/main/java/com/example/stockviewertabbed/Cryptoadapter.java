package com.example.stockviewertabbed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Cryptoadapter extends RecyclerView.Adapter<Cryptoadapter.ViewHolder> {
    private ArrayList<Bitcoin> bitcoins = new ArrayList<>();
    private Context context;

    public Cryptoadapter(Context context, ArrayList<Bitcoin> bitcoins){
        this.bitcoins=bitcoins;
        this.context=context;

    }
    @NonNull
    @Override
    public Cryptoadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crypto_list_items,parent,false);
        return new Cryptoadapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cryptoadapter.ViewHolder viewholder, int position) {
        viewholder.crypto_name.setText(bitcoins.get(position).getName());
      viewholder.crypto_price.setText(bitcoins.get(position).getCurrentPrice());

        Picasso.get().load(bitcoins.get(position).getImage()).into(viewholder.crypto_image);

    }

    @Override
    public int getItemCount() {
        return bitcoins.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView crypto_image;
        TextView crypto_name;
        TextView crypto_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            crypto_image =(ImageView)itemView.findViewById(R.id.crypto_image);
            crypto_name = (TextView) itemView.findViewById(R.id.crypto_name);
            crypto_price = (TextView) itemView.findViewById(R.id.crypto_price);
        }
    }
}
