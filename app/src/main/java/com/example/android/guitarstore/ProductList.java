package com.example.android.guitarstore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductList {
    @SerializedName("list")
    public List<Product> list;

    static public class Product {
        @SerializedName("idP")
        public int idP;
        @SerializedName("NAME")
        public String name;
        @SerializedName("thumbnail")
        public String thumbnail;
        @SerializedName("TYPE")
        public String type;
        @SerializedName("price")
        public double price;
        @SerializedName("descrip")
        public String descrip;
        @SerializedName("imgs")
        public String imgs;

    }
}
