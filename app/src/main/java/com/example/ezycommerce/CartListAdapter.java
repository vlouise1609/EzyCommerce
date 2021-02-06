package com.example.ezycommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Product> productLists;
    DBManager dbManager;

    public CartListAdapter(Context ctx, ArrayList<Product> productLists) {
        this.ctx = ctx;
        this.productLists = productLists;
    }

    public CartListAdapter(){

    }

    @NonNull
    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.activity_product_list_adapter, parent, false);

        return new CartListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {
        final Product product = productLists.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(Double.toString(product.getPrice()));
        holder.productQuantity.setText(product.getQuantity());
        Picasso.with(ctx).load(product.getImg()).into(holder.productImage);

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.productQuantity.setText(product.getQuantity() + 1);
                dbManager.addCartRecords(product);
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(product.getQuantity()>0){
                    holder.productQuantity.setText(product.getQuantity() - 1);
                    dbManager = new DBManager();
                    dbManager.addCartRecords(product);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName, productPrice, productQuantity;
        Button btnMinus, btnPlus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productCartName);
            productImage = itemView.findViewById(R.id.productCartImg);
            productPrice = itemView.findViewById(R.id.productCartPrice);
            productQuantity = itemView.findViewById(R.id.quantityProduct);

            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);

        }
    }
}