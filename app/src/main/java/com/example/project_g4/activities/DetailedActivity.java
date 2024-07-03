package com.example.project_g4.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.project_g4.R;
import com.example.project_g4.models.ViewAllModel;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView price, rating, description;
    Button addToCart;
    ImageView addItems, removeItems;
    androidx.appcompat.widget.Toolbar toolbar;
    ViewAllModel viewAllModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;
        }

        detailedImg = findViewById(R.id.detailed_img);
        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        description = findViewById(R.id.detailed_dec);
        if (viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText("Price: $" +viewAllModel.getPrice()+"/kg");

            if (viewAllModel.getType().equals("egg")){
                price.setText("Price: $" +viewAllModel.getPrice()+"/dozen");
            }
            if (viewAllModel.getType().equals("milk")){
                price.setText("Price: $" +viewAllModel.getPrice()+"/litre");
            }
        }
        addToCart = findViewById(R.id.add_to_cart);
        addItems = findViewById(R.id.add_item);

        removeItems = findViewById(R.id.remove_item);
    }
}