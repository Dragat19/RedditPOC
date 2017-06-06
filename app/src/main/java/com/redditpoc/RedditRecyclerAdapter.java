package com.redditpoc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by levaa on 6/5/2017.
 */

public class RedditRecyclerAdapter extends RecyclerView.Adapter<RedditRecyclerAdapter.ItemViewHolder> {
    private Context context;
    private List<InfoReddit> infoReddits;

    public RedditRecyclerAdapter(Context context, List<InfoReddit> infoReddits) {
        this.context = context;
        this.infoReddits = infoReddits;
    }

    @Override
    public RedditRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_info_reddit,parent,false);
        ItemViewHolder redditView = new ItemViewHolder(v);
        return redditView;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.title.setText(infoReddits.get(position).getTitle_reddit());
        holder.author.setText(infoReddits.get(position).getAuthor_reddit());
        holder.comments.setText(infoReddits.get(position).getComments_reddit());

    }

    @Override
    public int getItemCount() {
        return infoReddits.size();
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


