package com.example.project_g4.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_g4.R;
import com.example.project_g4.adapters.ViewAllAdapter;
import com.example.project_g4.models.ViewAllModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class ViewAllActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    List<ViewAllModel> viewAllModelList;
    ViewAllAdapter viewAllAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        recyclerView = findViewById(R.id.view_all_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this, viewAllModelList);
        recyclerView.setAdapter(viewAllAdapter);

        // Initialize DatabaseReference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("AllProducts");

        // Fetch data from Firebase based on type
        String type = getIntent().getStringExtra("type");
        databaseReference.orderByChild("type").equalTo(type).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                viewAllModelList.clear(); // Clear the list before adding new data

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String rating = snapshot.child("rating").getValue(String.class);
                    String description = snapshot.child("description").getValue(String.class);
                    String imgUrl = snapshot.child("img_url").getValue(String.class);
                    Integer price = snapshot.child("price").getValue(Integer.class);

                    if (name != null && rating != null && description != null && imgUrl != null && price != null) {
                        ViewAllModel viewAllModel = new ViewAllModel(name, rating, description, type, imgUrl, price);
                        viewAllModelList.add(viewAllModel);
                    }
                }

                viewAllAdapter.notifyDataSetChanged(); // Notify adapter of data change
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }
}
