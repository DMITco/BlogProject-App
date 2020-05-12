package com.dmitco.blogproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.dmitco.blogproject.R;
import com.dmitco.blogproject.interfaces.ListSelectorListener;
import com.dmitco.blogproject.model.Post;
import com.dmitco.blogproject.utility.CircleImageView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder>  {

    private List<Post> posts = new ArrayList<>();
    private ListSelectorListener<Post> listener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtTitle,txtPost,txtAuthor,txtDate;
        CircleImageView imgAuthor;
        RoundedImageView imgPost;



        public MyViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.txtTitle);
            txtPost = view.findViewById(R.id.txtPost);
            txtAuthor = view.findViewById(R.id.txtAuthor);
            txtDate = view.findViewById(R.id.txtDate);
            imgAuthor = view.findViewById(R.id.imgAuthor);
            imgPost = view.findViewById(R.id.imgPost);

        }
        Post item;
        public void bind(final Post post){
            item = post;
            txtTitle.setText(item.getPostTitle());
            txtPost.setText(item.getPostText());
            txtAuthor.setText(item.getUserFirstName()+" "+item.getUserLastName());
            txtDate.setText(item.getCreateDate());

            //Author Image
            String authorURL    = post.getUserAvatar() != null && !post.getUserAvatar().isEmpty() ? post.getUserAvatar() : "URL";
            Picasso.get()
                    .load(authorURL)
                    .placeholder(imgAuthor.getDrawable())
                    .resize(200, 200)
                    .error(imgAuthor.getDrawable())
                    .into(imgAuthor);
            //post image
            String postURL   = post.getImageName() != null && !post.getImageName().isEmpty() ? post.getImageName() : "URL";
            Picasso.get()
                    .load(postURL)
//                    .resize(400,300)
                    .fit()
                    .into(imgPost);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onSelected(item);
            }
        }
    }
    

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post,parent, false);
        return new PostAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts==null ?0:posts.size();
    }

    public void setListener(ListSelectorListener<Post> listener) {
        this.listener = listener;
    }

    public void clearAndPut(@Nullable List<Post> items) {
        posts.clear();
        if (items != null) {
            posts.addAll(items);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    public void put(@Nullable List<Post> items) {
        if (items != null) {
            posts.addAll(items);
        }
        notifyDataSetChanged();
    }



}
