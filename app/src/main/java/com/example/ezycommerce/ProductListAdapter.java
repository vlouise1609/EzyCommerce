package com.example.ezycommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Product> productLists;

    public ProductListAdapter(Context ctx, ArrayList<Product> productLists) {
        this.ctx = ctx;
        this.productLists = productLists;
    }

    public ProductListAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.activity_product_list_adapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        final Product product = productLists.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(Double.toString(product.getPrice()));
        for (int i = 0; i < 3; i++) {
            holder.productRating.setImageResource(R.drawable.ic_baseline_star_24);
        }
//        Glide.with(ctx)
//                .load(product.getImg())
//                .into(holder.productImage);

        Picasso.with(ctx).load(product.getImg()).into(holder.productImage);

    }

    public void setListProduct(ArrayList<Product> productLists) {
        this.productLists = productLists;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView productImage, productRating;
        TextView productName, productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productImage = itemView.findViewById(R.id.productImg);
            productPrice = itemView.findViewById(R.id.productPrice);
            productRating = itemView.findViewById(R.id.productStar);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}