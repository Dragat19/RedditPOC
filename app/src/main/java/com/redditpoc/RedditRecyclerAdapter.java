package com.redditpoc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by levaa on 6/5/2017.
 */

public class RedditRecyclerAdapter extends RecyclerView.Adapter<RedditRecyclerAdapter.ItemViewHolder> {
    private Context context;

    public RedditRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RedditRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main,parent,false);
        ItemViewHolder redditView = new ItemViewHolder(v);
        return redditView;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView thumbnails;
        private TextView title,author,date,comments;
        public ItemViewHolder(View itemView) {
            super(itemView);

            thumbnails = (ImageView) itemView.findViewById(R.id.thumbnails_reddit);
            title = (TextView) itemView.findViewById(R.id.title_reddit);
            author = (TextView) itemView.findViewById(R.id.author_reddit);
            date = (TextView) itemView.findViewById(R.id.date_reddit);
            comments = (TextView) itemView.findViewById(R.id.comments_reddit);
        }
    }
}


