package com.example.stockviewertabbed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

    private ArrayList<Article> articles = new ArrayList<>();
    private Context context;

    public NewsAdapter(Context context, ArrayList<Article> news) {
        this.articles = news;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_items,parent,false);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.ViewHolder viewholder, int position) {
        viewholder.news_name.setText(articles.get(position).getTitle());
        viewholder.news_description.setText(articles.get(position).getDescription());
        Picasso.get().load(articles.get(position).getUrlToImage()).into(viewholder.news_image);
        viewholder.url=articles.get(position).getUrl();
    }

    @Override
    public int getItemCount() {

        Log.d("Errorsize",articles.size()+"");
    return articles.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView news_image;
        TextView news_name;
        TextView news_description;
        String url;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news_image = (ImageView)itemView.findViewById(R.id.news_image);
            news_name = (TextView)itemView.findViewById(R.id.news_name);
            news_description=(TextView)itemView.findViewById(R.id.news_desc);
            news_name.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
                    context.startActivity(intent);
                }
            });
        }
    }
}


