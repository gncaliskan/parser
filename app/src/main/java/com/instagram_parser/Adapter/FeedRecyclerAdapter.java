package com.instagram_parser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.instagram_parser.DrawActivity;
import com.instagram_parser.Entity.Data;
import com.instagram_parser.Model.Comment;
import com.instagram_parser.R;
import com.instagram_parser.Request.RequestController;
import com.instagram_parser.System.Constants;
import com.instagram_parser.Util.SharedUtil;

import java.util.List;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    List<Data> feeds;
    Context context;

    public FeedRecyclerAdapter(List<Data> feeds, Context context) {
        this.feeds = feeds;
        this.context = context;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.feed_list_view_item,null);
        return new FeedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FeedViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext()).load(feeds.get(position).getImages().getThumbnail().getUrl()).into(holder.feedPic);
        holder.feedPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SharedUtil.setDefaults(Constants.SHARED_MEDIA_ID, feeds.get(position).getId(),context);
                String mediaUrl = Constants.GET_MEDIA_URL+ feeds.get(position).getId()+Constants.COMMENTS+SharedUtil.getDefaults(Constants.SHARED_ACCESS_TOKEN,context);
                RequestController.getRoot(mediaUrl);
                context.startActivity(new Intent(context, DrawActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }
}


class FeedViewHolder extends RecyclerView.ViewHolder {
   ImageView feedPic;

   public FeedViewHolder(View itemView) {
       super(itemView);
       feedPic = itemView.findViewById(R.id.feed_item_pic);
   }

}