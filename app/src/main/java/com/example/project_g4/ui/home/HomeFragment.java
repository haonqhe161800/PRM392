package com.example.project_g4.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_g4.R;
import com.example.project_g4.adapters.HomeAdapter;
import com.example.project_g4.adapters.PopularAdapters;
import com.example.project_g4.adapters.RecommendedAdapter;
import com.example.project_g4.models.HomeCategory;
import com.example.project_g4.models.PopularModel;
import com.example.project_g4.models.RecommendedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ScrollView scrollView;
    ProgressBar progressBar;
    // popular item
    RecyclerView popularRec, homeCatRec, recommendedRec;
    List<PopularModel> popularModelList;
    PopularAdapters popularAdapters;
    DatabaseReference popularDatabaseReference;
    // home category
    List<HomeCategory> categoryList;
    HomeAdapter homeAdapter;
    // recommended
    List<RecommendedModel> recommendedModelList;
    RecommendedAdapter recommendedAdapter;
    DatabaseReference recommendedDatabaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        popularRec = root.findViewById(R.id.pop_rec);
        homeCatRec = root.findViewById(R.id.explore_rec);
        recommendedRec = root.findViewById(R.id.recommended_rec);
        scrollView = root.findViewById(R.id.scroll_view);
        progressBar = root.findViewById(R.id.progressbar);



        // popular items
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapters(getActivity(), popularModelList);
        popularRec.setAdapter(popularAdapters);

        // Initialize DatabaseReference for popular items
        popularDatabaseReference = FirebaseDatabase.getInstance().getReference().child("PopularProducts");

        // Add ValueEventListener to fetch data for popular items from Firebase
        popularDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                popularModelList.clear(); // Clear the list before adding new data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Extract data from snapshot
                    String productName = snapshot.child("name").getValue(String.class);
                    String description = snapshot.child("description").getValue(String.class);
                    String discount = snapshot.child("discount").getValue(String.class);
                    String imgUrl = snapshot.child("img_url").getValue(String.class);
                    String type = snapshot.child("type").getValue(String.class);
                    String rating = snapshot.child("rating").getValue(String.class);

                    // Check for null values
                    if (productName != null && description != null && discount != null && imgUrl != null && type != null && rating != null) {
                        // Create a PopularModel object with extracted properties
                        PopularModel popularModel = new PopularModel(productName, rating, description, discount, type, imgUrl);
                        popularModelList.add(popularModel); // Add the model to the list
                    }
                }
                popularAdapters.notifyDataSetChanged(); // Notify adapter of data change
                progressBar.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });

        // explore items
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(), categoryList);
        homeCatRec.setAdapter(homeAdapter);

        // Initialize DatabaseReference for home categories
        DatabaseReference homeDatabaseReference = FirebaseDatabase.getInstance().getReference().child("HomeCategory");

        // Add ValueEventListener to fetch data for home categories from Firebase
        homeDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categoryList.clear(); // Clear the list before adding new data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Extract data from snapshot
                    String productName = snapshot.child("name").getValue(String.class);
                    String imgUrl = snapshot.child("img_url").getValue(String.class);
                    String type = snapshot.child("type").getValue(String.class);

                    // Check for null values
                    if (productName != null && imgUrl != null && type != null) {
                        // Create a HomeCategory object with extracted properties
                        HomeCategory homeCategory = new HomeCategory(productName, type, imgUrl);
                        categoryList.add(homeCategory); // Add the model to the list
                    }
                }
                homeAdapter.notifyDataSetChanged(); // Notify adapter of data change
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });

        // recommended items
        recommendedRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recommendedModelList = new ArrayList<>();
        recommendedAdapter = new RecommendedAdapter(getActivity(), recommendedModelList);
        recommendedRec.setAdapter(recommendedAdapter);

        // Initialize DatabaseReference for recommended items
        recommendedDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Recommended");

        // Add ValueEventListener to fetch data for recommended items from Firebase
        recommendedDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recommendedModelList.clear(); // Clear the list before adding new data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Extract data from snapshot
                    String productName = snapshot.child("name").getValue(String.class);
                    String description = snapshot.child("description").getValue(String.class);
                    String imgUrl = snapshot.child("img_url").getValue(String.class);
                    String rating = snapshot.child("rating").getValue(String.class);

                    // Check for null values
                    if (productName != null && description != null && imgUrl != null && rating != null) {
                        // Create a RecommendedModel object with extracted properties
                        RecommendedModel recommendedModel = new RecommendedModel(productName, rating, description, 0, imgUrl);
                        recommendedModelList.add(recommendedModel); // Add the model to the list
                    }
                }
                recommendedAdapter.notifyDataSetChanged(); // Notify adapter of data change
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });

        return root;
    }
}
