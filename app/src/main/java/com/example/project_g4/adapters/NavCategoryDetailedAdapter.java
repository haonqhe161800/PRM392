package com.example.project_g4.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_g4.R;
import com.example.project_g4.activities.NavCategoryActivity;
import com.example.project_g4.models.NavCategoryDetailedModel;

import java.util.List;

public class NavCategoryDetailedAdapter extends RecyclerView.Adapter<NavCategoryDetailedAdapter.ViewHolder> {

    private Context context;
    private List<NavCategoryDetailedModel> list;

    public NavCategoryDetailedAdapter(Context context, List<NavCategoryDetailedModel> navCategoryList) {
        this.context = context;
        this.list = navCategoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_dettailed_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NavCategoryDetailedModel model = list.get(position);

        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());

        // Load image using Glide library
        Glide.with(context)
                .load(model.getImg_url())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to NavCategoryActivity with type information
                Intent intent = new Intent(context, NavCategoryActivity.class);
                intent.putExtra("type", model.getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nav_cat_name);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.cat_nav_img);
        }
    }
}
