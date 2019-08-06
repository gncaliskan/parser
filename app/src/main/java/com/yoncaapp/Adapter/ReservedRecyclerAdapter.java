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
import com.bumptech.glide.request.RequestOptions;
import com.yoncaapp.Model.Comment;
import com.yoncaapp.Model.CommentList;
import com.yoncaapp.R;

import java.util.List;
import java.util.Map;

public class ReservedRecyclerAdapter extends RecyclerView.Adapter<ReservedViewHolder> {
    List<Comment> reserveds;
    Activity activity;
    boolean unique;

    public ReservedRecyclerAdapter(List<Comment> reserveds, Activity activity, boolean unique) {
        this.reserveds = reserveds;
        this.activity = activity;
        this.unique = unique;
    }

    @Override
    public ReservedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reserved_winner_item, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView.setLayoutParams(lp);
        return new ReservedViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final ReservedViewHolder holder, final int position) {
        holder.username.setText(reserveds.get(position).getOwnerName());
        Glide.with(holder.itemView.getContext()).load(reserveds.get(position).getPicUrl()).apply(RequestOptions.circleCropTransform()).into(holder.profilPic);
        holder.reservedComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserComments(reserveds.get(position).getOwnerId(), holder.itemView.getContext());
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
        return reserveds.size();
    }
}


class ReservedViewHolder extends RecyclerView.ViewHolder {
   TextView username, reservedComments;
   ImageView profilPic;

   public ReservedViewHolder(View itemView) {
       super(itemView);
       username = itemView.findViewById(R.id.reserved_winner_username);
       profilPic = itemView.findViewById(R.id.reserved_winner_profilPic);
       reservedComments = itemView.findViewById(R.id.reserved_winner_comments);

   }

}