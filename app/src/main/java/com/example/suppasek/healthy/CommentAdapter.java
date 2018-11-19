package com.example.suppasek.healthy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {

    ArrayList<Comment> comments;
    Context context;

    public CommentAdapter(@NonNull Context context, int resource, ArrayList<Comment> comments) {
        super(context, resource, comments);
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View commentItem = LayoutInflater.from(context).inflate(
                R.layout.comment_item,
                parent,
                false);

        TextView id = commentItem.findViewById(R.id.comment_item_id);
        TextView postId = commentItem.findViewById(R.id.comment_item_post_id);
        TextView name = commentItem.findViewById(R.id.comment_item_name);
        TextView body = commentItem.findViewById(R.id.comment_item_body);
        TextView email = commentItem.findViewById(R.id.comment_item_email);


        Comment comment = comments.get(position);
        id.setText(comment.getId());
        postId.setText(comment.getPostId());
        body.setText(comment.getBody());
        name.setText(comment.getName());
        email.setText(comment.getEmail());

        return commentItem;
    }
}


