package com.redditpoc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.redditpoc.Model.TopReddit;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by levaa on 6/5/2017.
 */

public class RedditRecyclerAdapter extends RecyclerView.Adapter<RedditRecyclerAdapter.ItemViewHolder> {
    public static final String ADAPTER_THUMBNAILS = "thumbnails";
    private Context context;
    private List<TopReddit> topReddits;

    public RedditRecyclerAdapter(Context context, List<TopReddit> topReddits) {
        this.context = context;
        this.topReddits = topReddits;
    }

    @Override
    public RedditRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_info_reddit,parent,false);
        ItemViewHolder redditView = new ItemViewHolder(v);
        return redditView;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        setImagen_picasso(topReddits.get(position).getThumbnail(),holder.thumbnails);
        holder.title.setText(topReddits.get(position).getTitle());
        holder.author.setText(topReddits.get(position).getAuthor());
        holder.comments.setText("comments "+topReddits.get(position).getNum_comments());

        holder.thumbnails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra(ADAPTER_THUMBNAILS,topReddits.get(position).getThumbnail());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return topReddits.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView thumbnails;
        private TextView title,author,date,comments;
        private CardView cv;
        public ItemViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            thumbnails = (ImageView) itemView.findViewById(R.id.thumbnails_reddit);
            title = (TextView) itemView.findViewById(R.id.title_reddit);
            author = (TextView) itemView.findViewById(R.id.author_reddit);
            date = (TextView) itemView.findViewById(R.id.date_reddit);
            comments = (TextView) itemView.findViewById(R.id.comments_reddit);
        }
    }

    public void setImagen_picasso(String Url, ImageView imageView) {
        Picasso.with(context)
                .load(Url)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }
}


