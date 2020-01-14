package com.example.android.guitarstore.data;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.guitarstore.HomeActivity;
import com.example.android.guitarstore.MainActivity;
import com.example.android.guitarstore.ProductDetail;
import com.example.android.guitarstore.ProductList.Product;
import com.example.android.guitarstore.R;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.GuitarViewHolder> {
    private final List<Product> data;
    private Context c;
    public MyAdapter(List<Product> data, Context context) {
        this.data = data;
        this.c = context;
    }

    @NonNull
    @Override
    public MyAdapter.GuitarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guitar_list_item, parent, false);
        GuitarViewHolder viewHolder = new GuitarViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.GuitarViewHolder holder, final int position) {
        DecimalFormat df = new DecimalFormat("#.00");
        Product p = data.get(position);
        final String pr = String.format("%.2f", p.price) + "€";
        final int imm = getImgId(p.thumbnail, c);
        holder.mGuitarName.setText(p.name);
        holder.mGuitarCategory.setText(p.type);
        holder.mGuitarPrice.setText(pr);

        holder.mGuitarThumbnail.setImageResource(imm);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, ProductDetail.class);
                intent.putExtra("name",data.get(position).name);
                intent.putExtra("thumbnail",imm);
                intent.putExtra("type",data.get(position).type);
                intent.putExtra("price",pr);
                intent.putExtra("descrip",data.get(position).descrip);
                intent.putExtra("imgs",data.get(position).imgs);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static int getImgId(String name, Context c){
        return c.getResources().getIdentifier("drawable/" + name, null, c.getPackageName());
    }

    public void setOnClickListener() {
    }

    static class GuitarViewHolder extends RecyclerView.ViewHolder {
        final ImageView mGuitarThumbnail;
        final TextView mGuitarName;
        final TextView mGuitarCategory;
        final TextView mGuitarPrice;

        public GuitarViewHolder(@NonNull View itemView) {
            super(itemView);
            mGuitarName = itemView.findViewById(R.id.GuitarName);
            mGuitarThumbnail = itemView.findViewById(R.id.guitarThumbnail);
            mGuitarCategory = itemView.findViewById(R.id.GuitarCategory);
            mGuitarPrice = itemView.findViewById(R.id.GuitarPrice);
        }
    }
}