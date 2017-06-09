package com.redditpoc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.redditpoc.DetailsActivity;
import com.redditpoc.Model.TopReddit;
import com.redditpoc.R;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by levaa on 6/5/2017.
 */

public class RedditRecyclerAdapter extends RecyclerView.Adapter<RedditRecyclerAdapter.ItemViewHolder> {
    public static final String ADAPTER_IMAGEN = "url";
    private Context context;
    private List<TopReddit> topReddits;
    private ArrayList<Integer> items;
    private long UTC_TIMEZONE=1496636570;

    public RedditRecyclerAdapter(Context context, List<TopReddit> topReddits, ArrayList<Integer> items) {
        this.context = context;
        this.topReddits = topReddits;
        this.items = items;
    }

    @Override
    public RedditRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_info_reddit,parent,false);
        ItemViewHolder redditView = new ItemViewHolder(v);
        return redditView;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        setImagen_picasso(topReddits.get(items.get(position)).getThumbnail(),holder.thumbnails);
        holder.title.setText(topReddits.get(items.get(position)).getTitle());
        holder.author.setText(topReddits.get(items.get(position)).getAuthor());
        holder.comments.setText(topReddits.get(items.get(position)).getNum_comments()+" comments");
        holder.date.setText(String.valueOf(getDateFromUTCTimestamp(topReddits.get(items.get(position)).getCreated_utc(),"h"))+" hours ago");

        holder.thumbnails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra(ADAPTER_IMAGEN,topReddits.get(items.get(position)).getThumbnail());
                context.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return items.size();
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
                .resize(100,100)
                .error(R.drawable.icon_reddit)
                .into(imageView);
    }

    public String getDateFromUTCTimestamp(int mTimestamp, String mDateFormate) {
        String date = null;
        try {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal.setTimeInMillis(mTimestamp * 1000L);
            date = DateFormat.format(mDateFormate, cal.getTimeInMillis()).toString();

            SimpleDateFormat formatter = new SimpleDateFormat(mDateFormate);
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(date);

            SimpleDateFormat dateFormatter = new SimpleDateFormat(mDateFormate);
            dateFormatter.setTimeZone(TimeZone.getDefault());
            date = dateFormatter.format(value);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}


