package com.example.project_g4.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_g4.R;
import com.example.project_g4.models.MyCartModell;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModell> cartModellList;

    public MyCartAdapter(Context context, List<MyCartModell> cartModellList) {
        this.context = context;
        this.cartModellList = cartModellList;
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {
        holder.name.setText(cartModellList.get(position).getProductName());
        holder.price.setText(cartModellList.get(position).getProductPrice());
        holder.date.setText(cartModellList.get(position).getCurrentDate());
        holder.time.setText(cartModellList.get(position).getCurrentTime());
        holder.quantity.setText(cartModellList.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(cartModellList.get(position).getTotalPrice()));



        // Tính tổng giá trị
        int totalPrice = 0;
        for (MyCartModell cart : cartModellList) {
            totalPrice += cart.getTotalPrice();
        }

        // Gửi dữ liệu tổng giá trị qua Intent
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount", totalPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        context.sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return cartModellList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, date, time, quantity, totalPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            quantity = itemView.findViewById(R.id.total_quantity);
            totalPrice = itemView.findViewById(R.id.total_price);
        }
    }
}
