package com.example.instagramappclone;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> postList;

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_fragment, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.textViewPostContent.setText(post.getContent());
        holder.imageViewPostImage.setImageResource(post.getImageResource());

        // Handle action buttons (like, share, comment) here
        holder.buttonLike.setOnClickListener(v -> {
            // Handle the "Like" action for the post
            // You can update UI or perform other actions
        });

        holder.buttonShare.setOnClickListener(v -> {
            // Handle the "Share" action for the post
            // You can implement sharing functionality
        });

        holder.buttonComment.setOnClickListener(v -> {
            // Handle the "Comment" action for the post
            // You can open a comment activity or perform other actions
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPostContent;
        ImageView imageViewPostImage;
        Button buttonLike;
        Button buttonShare;
        Button buttonComment;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPostContent = itemView.findViewById(R.id.textViewPostContent);
            imageViewPostImage = itemView.findViewById(R.id.imageViewPostImage);
            buttonLike = itemView.findViewById(R.id.buttonLike);
            buttonShare = itemView.findViewById(R.id.buttonShare);
            buttonComment = itemView.findViewById(R.id.buttonComment);
        }

        private void sharePost(Post post) {
            // Implement sharing functionality here (e.g., share post content via Intent)
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, post.getContent());
            itemView.getContext().startActivity(Intent.createChooser(shareIntent, "Share post via"));
        }

        private void openCommentActivity(Post post) {
            // Implement opening a comment activity or fragment here
            // You can pass the post data to the comment activity/fragment
            // For simplicity, we'll show a toast message here
            Toast.makeText(itemView.getContext(), "Open comments for post: " + post.getContent(), Toast.LENGTH_SHORT).show();
        }
    }
}
