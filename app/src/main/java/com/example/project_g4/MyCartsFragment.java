package com.example.project_g4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_g4.adapters.MyCartAdapter;
import com.example.project_g4.models.MyCartModell;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyCartsFragment extends Fragment {
    private FirebaseAuth auth;
    private DatabaseReference db;
    private RecyclerView recyclerView;
    private MyCartAdapter cartAdapter;
    private List<MyCartModell> cartModellList;
    TextView overtotalAmount;
    public MyCartsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_carts, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference("AddToCart").child(auth.getCurrentUser().getUid()).child("CurrentUser");

        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        overtotalAmount = root.findViewById(R.id.textView6);

        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));

        cartModellList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getActivity(), cartModellList);
        recyclerView.setAdapter(cartAdapter);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartModellList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MyCartModell cartModell = snapshot.getValue(MyCartModell.class);
                    if (cartModell != null) {
                        cartModellList.add(cartModell);
                    }
                }
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.
            }
        });

        return root;
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        int totalBill = intent.getIntExtra("totalAmount",0);
        overtotalAmount.setText("Total Bill:"+totalBill+"$");
        }
    };
}
