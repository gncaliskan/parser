package com.instagram_parser.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.instagram_parser.Model.Comment;
import com.instagram_parser.Model.CommentList;
import com.instagram_parser.R;
import com.instagram_parser.ResultActivity;

import java.util.List;
import java.util.Map;

public class WinnerRecyclerAdapter extends RecyclerView.Adapter<WinnerViewHolder> {
    List<Comment> winners;
    Activity activity;

    public WinnerRecyclerAdapter(List<Comment> winners, Activity activity) {
        this.winners = winners;
        this.activity = activity;
    }

    @Override
    public WinnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.royal_winner_item,null);
        return new WinnerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final WinnerViewHolder holder, final int position) {
        holder.username.setText(winners.get(position).getOwnerName());
        Glide.with(holder.itemView.getContext()).load(winners.get(position).getPicUrl()).apply(RequestOptions.circleCropTransform()).into(holder.profilPic);
        holder.winnerComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserComments(winners.get(position).getOwnerId(), holder.itemView.getContext());
            }
        });
    }

    private void getUserComments(String ownerId, Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_user_comments);
        ListView rv_userComments = dialog.findViewById(R.id.dialog_comments);
        Map<String, List<Comment>> commentMap = CommentList.getInstance().getCommentMap();
        List<Comment> userComments;
        if(ownerId!=null && !ownerId.isEmpty()){
            userComments = commentMap.get(ownerId);
            if(userComments!=null && userComments.size()>0){
                CommentListAdapter commentListAdapter = new CommentListAdapter(context,userComments, false);
                rv_userComments.setAdapter(commentListAdapter);
                dialog.show();
            }
        }
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