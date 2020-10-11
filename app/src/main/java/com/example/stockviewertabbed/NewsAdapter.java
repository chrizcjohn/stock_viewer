package com.example.stockviewertabbed;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> news = new ArrayList<>();
    private Context context;

    public NewsAdapter(Context context, ArrayList<News> news) {
        this.news = news;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_items,parent,false);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder viewholder, int position) {
        viewholder.news_name.setText(news.get(position).getArticles().get(position).getTitle());
        viewholder.news_description.setText(news.get(position).getArticles().get(position).getDescription());
        Picasso.get().load(news.get(position).getArticles().get(position).getUrlToImage()).into(viewholder.news_image);
    }

    @Override
    public int getItemCount() {

        Log.d("Errorsize",news.size()+"");
    return news.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView news_image;
        TextView news_name;
        TextView news_description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news_image = (ImageView)itemView.findViewById(R.id.news_image);
            news_name = (TextView)itemView.findViewById(R.id.news_name);
            news_description=(TextView)itemView.findViewById(R.id.news_desc);
        }
    }
}


