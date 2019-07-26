package com.instagram_parser.Adapter;

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
import com.instagram_parser.Model.Comment;
import com.instagram_parser.R;

import java.util.List;

public class WinnerRecyclerAdapter extends RecyclerView.Adapter<WinnerViewHolder> {
    List<Comment> winners;

    public WinnerRecyclerAdapter(List<Comment> winners) {
        this.winners = winners;
    }

    @Override
    public WinnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.royal_winner_item,null);
        return new WinnerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final WinnerViewHolder holder, int position) {
        holder.username.setText(winners.get(position).getOwnerName());
        Glide.with(holder.itemView.getContext()).load(winners.get(position).getPicUrl()).apply(RequestOptions.circleCropTransform()).into(holder.profilPic);
        holder.winnerComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return winners.size();
    }
}


 class WinnerViewHolder extends RecyclerView.ViewHolder {
    TextView username, winnerComments;
    ImageView profilPic;

    public WinnerViewHolder(View itemView) {
        super(itemView);
        username = itemView.findViewById(R.id.royal_winner_username);
        profilPic = itemView.findViewById(R.id.royal_winner_image);
        winnerComments = itemView.findViewById(R.id.royal_winner_comments);
    }

}