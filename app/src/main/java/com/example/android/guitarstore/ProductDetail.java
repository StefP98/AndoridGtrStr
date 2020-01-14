package com.example.android.guitarstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.internal.StringResourceValueReader;

import static com.example.android.guitarstore.data.MyAdapter.getImgId;

public class ProductDetail extends AppCompatActivity {
    private TextView tvName;
    private TextView tvType;
    private TextView tvPrice;
    private TextView tvDescrip;
    private ImageView tvThumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        tvName = findViewById(R.id.detailName);
        tvThumb = findViewById(R.id.detailPic);
        tvType = findViewById(R.id.detailType);
        tvDescrip = findViewById(R.id.detailDetail);
        tvPrice = findViewById(R.id.detailPrice);
        Context c = this;
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("name") != null) {
            tvName.setText(bundle.getString("name"));
            tvThumb.setImageResource(bundle.getInt("thumbnail"));
            tvDescrip.setText(bundle.getString("descrip"));
            tvType.setText(bundle.getString("type"));
            tvPrice.setText(bundle.getString("price"));
        }
    }
}
