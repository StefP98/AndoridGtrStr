package com.example.android.guitarstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.android.guitarstore.data.MyAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Fragment {

    public MainActivity(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        String myJson = inputStreamToString(getResources().openRawResource(R.raw.product));
        final ProductList lista = new Gson().fromJson(myJson, ProductList.class);

        RecyclerView recyclerView = view.findViewById(R.id.product_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        MyAdapter myAdapter = new MyAdapter(lista.list, this.getActivity().getApplicationContext());

        recyclerView.setAdapter(myAdapter);
        return view;
    }

    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }

}
