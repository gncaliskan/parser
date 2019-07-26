package com.instagram_parser.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.instagram_parser.Model.Comment;
import com.instagram_parser.Model.CommentList;
import com.instagram_parser.R;

import java.util.List;

public class CommentListAdapter extends ArrayAdapter<Comment> {
    private final LayoutInflater inflater;
    List<Comment> comments;
    CommentItemHolder holder;
    Context context;

    public CommentListAdapter(@NonNull Context context) {
        super(context, 0);
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.comments = CommentList.getInstance().getComments();


    }

    @Override
    public int getCount() {
        return comments.size();
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
        this.comments = CommentList.getInstance().getComments();
        final Comment comment = comments.get(position);
        if(comment != null){
            holder.username.setText(comment.getOwnerName());
            holder.comment.setText(comment.getCommentText());

        }

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comments.get(position).isActive()) {
                    comments.get(position).setActive(false);
                }else{
                    comments.get(position).setActive(true);
                }
                notifyDataSetChanged();
            }
        });

        if(comments.get(position).isActive()){
            holder.delete.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.ic_delete), null);
            convertView.setAlpha(1);
        }else{
            holder.delete.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.ic_add), null);
            convertView.setAlpha(0.4f);
        }

        return convertView;
    }

    private class CommentItemHolder {
        TextView username;
        TextView comment;
        Button delete;

    }
}
