package com.example.suppasek.healthy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends ArrayAdapter<Post>{

    List<Post> posts;
    Context context;

    public PostAdapter(@NonNull Context context, int resource, List<Post> posts) {
        super(context, resource, posts);
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View postItem = LayoutInflater.from(context).inflate(
                R.layout.post_item,
                parent,
                false);

        TextView userId = postItem.findViewById(R.id.post_item_id);
        TextView title = postItem.findViewById(R.id.post_item_title);
        TextView body = postItem.findViewById(R.id.post_item_body);

        Post post = posts.get(position);
        userId.setText(post.getId());
        title.setText(post.getTitle());
        body.setText(post.getBody());

        return postItem;
     }
}

