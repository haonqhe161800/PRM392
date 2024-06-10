package com.example.project_g4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.project_g4.R;
import com.example.project_g4.models.HomeCategory;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    List<HomeCategory> categoryList;

    public HomeAdapter(Context context, List<HomeCategory> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cat_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Tạo RequestOptions với listener cho quá trình tải hình ảnh
        RequestOptions requestOptions = new RequestOptions()
                .error(getErrorImage(position)) // Đặt hình ảnh mặc định khi quá trình tải thất bại
                .placeholder(R.drawable.diet); // Đặt hình ảnh placeholder trong quá trình tải

        // Tải hình ảnh từ URL bằng Glide và áp dụng RequestOptions
        Glide.with(context)
                .load(categoryList.get(position).getImg_url())
                .apply(requestOptions)
                .into(holder.catImg);

        // Đặt tên danh mục
        holder.name.setText(categoryList.get(position).getName());
    }

    // Phương thức này trả về hình ảnh lỗi dựa trên vị trí của mục danh mục
    private int getErrorImage(int position) {
        switch (position) {
            case 0:
                return R.drawable.eggs;
            case 1:
                return R.drawable.fish;
            case 2:
                return R.drawable.milk2;
            case 3:
                return R.drawable.diet;
            case 4:
                return R.drawable.fruits;
            default:
                return R.drawable.iconminus;
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView catImg;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catImg = itemView.findViewById(R.id.home_cat_img);
            name = itemView.findViewById(R.id.home_cat_name);
        }
    }
}
