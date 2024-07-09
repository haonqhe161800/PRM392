package com.example.project_g4.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_g4.R;
import com.example.project_g4.adapters.NavCategoryAdapter;
import com.example.project_g4.models.NavCategoryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    DatabaseReference categoryDatabaseReference;
    RecyclerView recyclerView;
    List<NavCategoryModel> categoryModelList;
    NavCategoryAdapter navCategoryAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = root.findViewById(R.id.cat_rec);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        categoryModelList = new ArrayList<>();
        navCategoryAdapter = new NavCategoryAdapter(getActivity(), categoryModelList);
        recyclerView.setAdapter(navCategoryAdapter);

        // Initialize DatabaseReference for category items
        categoryDatabaseReference = FirebaseDatabase.getInstance().getReference().child("NavCategory");

        // Add ValueEventListener to fetch data for category items from Firebase
        categoryDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categoryModelList.clear(); // Clear the list before adding new data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Extract data from snapshot
                    String productName = snapshot.child("name").getValue(String.class);
                    String description = snapshot.child("description").getValue(String.class);
                    String discount = snapshot.child("discount").getValue(String.class);
                    String imgUrl = snapshot.child("img_url").getValue(String.class);

                    // Check for null values
                    if (productName != null && description != null && discount != null && imgUrl != null) {
                        // Create a NavCategoryModel object with extracted properties
                        NavCategoryModel navCategoryModel = new NavCategoryModel(productName, description, discount, imgUrl);
                        categoryModelList.add(navCategoryModel); // Add the model to the list
                    }
                }
                navCategoryAdapter.notifyDataSetChanged(); // Notify adapter of data change
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });

        return root;
    }
}
