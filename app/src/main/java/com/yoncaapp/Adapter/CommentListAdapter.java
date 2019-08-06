package com.yoncaapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.yoncaapp.Model.Comment;
import com.yoncaapp.R;

import java.util.List;

public class CommentListAdapter extends ArrayAdapter<Comment> {
    private final LayoutInflater inflater;
    List<Comment> comments;
    CommentItemHolder holder;
    Context context;
    boolean canDelete;

    public CommentListAdapter(@NonNull Context context, List<Comment> comments, boolean canDelete) {
        super(context, 0);
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.comments = comments;
        this.canDelete = canDelete;
    }

    @Override
    public int getCount() {
        if(comments!=null) {
            return comments.size();
        }
        return 0;
    }

    @Nullable
    @Override
    public Comment getItem(int position) {
        return comments.get(position);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.comment_list_item, null);

            holder = new CommentItemHolder();
            holder.username = convertView.findViewById(R.id.item_username);
            holder.comment = convertView.findViewById(R.id.item_comment);
            holder.delete =  convertView.findViewById(R.id.item_delete);
            convertView.setTag(holder);
        }
        else{
            //Get viewholder we already created
            holder = (CommentItemHolder) convertView.getTag();
        }
        final Comment comment = comments.get(position);
        if(comment != null){
            holder.username.setText(comment.getOwnerName());
            holder.comment.setText(comment.getCommentText());

        }

        if(canDelete) {
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comments.get(position).isNotDeleted()) {
                        comments.get(position).setNotDeleted(false);
                    } else {
                        comments.get(position).setNotDeleted(true);
                    }
                    notifyDataSetChanged();
                }
            });
        }else{
            holder.delete.setVisibility(View.GONE);
        }

        if(getActiveStatus(comment)){
            holder.delete.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.ic_delete), null);
            convertView.setAlpha(1);
        }else{
            holder.delete.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.ic_add), null);
            convertView.setAlpha(0.4f);
        }

        return convertView;
    }

    private boolean getActiveStatus(Comment c){
        if(c.isContainLabelCount() && c.isAccepted() && c.isNotDeleted() && c.isMatched()){
           return true;
        }
        return false;
    }

    private class CommentItemHolder {
        TextView username;
        TextView comment;
        Button delete;

    }
}
