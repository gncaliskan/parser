package com.instagram_parser.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.instagram_parser.Model.Comment;
import com.instagram_parser.R;

import java.util.List;

public class ReservedListAdapter extends ArrayAdapter<Comment> {
    private final LayoutInflater inflater;
    List<Comment> reservedList;
    Context context;

    public ReservedListAdapter(@NonNull Context context, List<Comment> reservedList) {
        super(context, 0);
        inflater = LayoutInflater.from(context);
        this.reservedList = reservedList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return reservedList.size();
    }

    @Nullable
    @Override
    public Comment getItem(int position) {
        return reservedList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.reserved_winner_item, null);
        }
        ReservedViewHolder holder = new ReservedViewHolder();
        holder.username = convertView.findViewById(R.id.reserved_winner_username);
        holder.profilPic = convertView.findViewById(R.id.reserved_winner_profilPic);
        holder.reservedComments = convertView.findViewById(R.id.reserved_winner_comments);
        Comment c = reservedList.get(position);

        holder.username.setText(c.getOwnerName());
        Glide.with(context).load(reservedList.get(position).getPicUrl()).apply(RequestOptions.circleCropTransform()).into(holder.profilPic);
        holder.reservedComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return convertView;
    }

    private class ReservedViewHolder {
        TextView username, reservedComments;
        ImageView profilPic;
    }
}

