package com.example.instagramappclone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList;
    private BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                // Check which item was selected based on its ID
                if (itemId == R.id.navigation_home) {
                    // Handle selection for menu_item1
                    // Do something when menu_item1 is selected
                    return true;
                } else if (itemId == R.id.navigation_new_post) {
                    // Handle selection for menu_item2
                    // Do something when menu_item2 is selected
                    return true;
                } else if (itemId == R.id.navigation_dashboard) {
                    // Handle selection for menu_item3
                    // Do something when menu_item3 is selected
                    return true;
                }

                // If none of the items match, return false
                return false;
            }
        });


        if (bottomNavigationView != null) {
            // Now, it's safe to work with bottomNavigationView
            bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    // Handle item selection here
                    return true;
                }
            });
        }

        postList = new ArrayList<>();

        int[] imageResources = {
                R.drawable.sample_img1,
                R.drawable.sample_img2,
                R.drawable.sample_img3,
                R.drawable.sample_img4,
                R.drawable.sample_img5,
                R.drawable.sample_img6,
                R.drawable.sample_img7,
                R.drawable.sample_img8,
        };

        for (int i = 0; i < imageResources.length; i++) {
            String postContent = "Sample post " + (i + 1); // Adjust for 0-based indexing
            int imageResource = imageResources[i];

            Post post = new Post(postContent, imageResource);
            postList.add(post);
        }


        postAdapter = new PostAdapter(postList);
        recyclerView.setAdapter(postAdapter);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }
    private ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        // Extract and process the selected image(s) from the data
                        Uri selectedImageUri = data.getData();
                        if (selectedImageUri != null) {
                            loadAndDisplayImage(selectedImageUri);
                        }
                    }
                }

            }


    );

    private void loadAndDisplayImage(Uri imageUri) {
        ImageView imageView = findViewById(R.id.imageViewPostImage);
        Glide.with(this)
                .load(imageUri) // Load the image from the URI
                .into(imageView); // Display it in the ImageView
    }

}

