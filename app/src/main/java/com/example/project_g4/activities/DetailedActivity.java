package com.example.project_g4.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.project_g4.R;
import com.example.project_g4.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {
    ImageView detailedImg;
    TextView price, rating, description;
    Button addToCart;
    ImageView addItem, removeItem;
    Toolbar toolbar;

    DatabaseReference databaseReference;
    FirebaseAuth auth;
    ViewAllModel viewAllModel = null;

    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("AddToCart")
                .child(auth.getCurrentUser().getUid())
                .child("CurrentUser");

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel) {
            viewAllModel = (ViewAllModel) object;
        }

        quantity = findViewById(R.id.quantity);
        detailedImg = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);
        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        description = findViewById(R.id.detailed_dec);

        if (viewAllModel != null) {
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText("Price: $" + viewAllModel.getPrcie() + "/kg");
            totalPrice = viewAllModel.getPrcie() * totalQuantity;

            if (viewAllModel.getType().equals("egg")) {
                price.setText("Price: $" + viewAllModel.getPrcie() + "/dozen");
                totalPrice = viewAllModel.getPrcie() * totalQuantity;
            }
            if (viewAllModel.getType().equals("milk")) {
                price.setText("Price: $" + viewAllModel.getPrcie() + "/litre");
                totalPrice = viewAllModel.getPrcie() * totalQuantity;
            }
        }

        addToCart = findViewById(R.id.add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }

            private void addToCart() {
                String saveCurrentDate, saveCurrentTime;
                Calendar calForDate = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
                saveCurrentDate = currentDate.format(calForDate.getTime());
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                saveCurrentTime = currentTime.format(calForDate.getTime());

                final HashMap<String, Object> cartMap = new HashMap<>();
                cartMap.put("productName", viewAllModel.getName());
                cartMap.put("productPrice", price.getText().toString());
                cartMap.put("currentDate", saveCurrentDate);
                cartMap.put("currentTime", saveCurrentTime);
                cartMap.put("totalQuantity", quantity.getText().toString());
                cartMap.put("totalPrice", totalPrice);

                databaseReference.push().setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(DetailedActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrcie() * totalQuantity;

                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 0) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrcie() * totalQuantity;

                }
            }
        });
    }
}
