package com.yoncaapp.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yoncaapp.Model.Comment;
import com.yoncaapp.Model.CommentList;
import com.yoncaapp.R;

import java.util.List;
import java.util.Map;

public class WinnerRecyclerAdapter extends RecyclerView.Adapter<WinnerViewHolder> {
    private final List<Comment> winners;
    private final boolean unique;

    public WinnerRecyclerAdapter(List<Comment> winners, Activity activity, boolean unique) {
        this.winners = winners;
        Activity activity1 = activity;
        this.unique = unique;
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
        Glide.with(holder.itemView.getContext()).load(winners.get(position).getPicUrl()).into(holder.profilPic);
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
        TextView warning = dialog.findViewById(R.id.dialog_warning);
        if(unique){
            warning.setVisibility(View.VISIBLE);
        }else{
            warning.setVisibility(View.GONE);
        }
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
    final TextView username;
     final TextView winnerComments;
    final ImageView profilPic;

    public WinnerViewHolder(View itemView) {
        super(itemView);
        username = itemView.findViewById(R.id.royal_winner_username);
        profilPic = itemView.findViewById(R.id.royal_winner_image);
        winnerComments = itemView.findViewById(R.id.royal_winner_comments);
    }

}