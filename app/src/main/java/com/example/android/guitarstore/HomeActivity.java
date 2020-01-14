package com.example.android.guitarstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SaveSharedPreference.getUserName(this).length() == 0){
            startActivity(new Intent(HomeActivity.this, LoginActivity2.class));
        } else {
            final Context context = this;
            setContentView(R.layout.activity_home);
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.action_home:
//                        Toast.makeText(HomeActivity.this, "Recents", Toast.LENGTH_SHORT).show();
                            openFragment(new WelcomeActivity());
                            return true;
                        case R.id.action_search:
//                        Toast.makeText(HomeActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                            openFragment(new MainActivity());
                            return true;
                        case R.id.action_cart:
//                            Toast.makeText(HomeActivity.this, "cart", Toast.LENGTH_SHORT).show();
                            openFragment(new CartActivity());
                            return true;
                        case R.id.action_logout:
//                        Toast.makeText(HomeActivity.this, "Nearby", Toast.LENGTH_SHORT).show();

                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("Logging out")
                                    .setMessage("Are you sure you want to log out?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            FirebaseAuth.getInstance().signOut();
                                            SaveSharedPreference.clearUserName(context);
                                            startActivity(new Intent(HomeActivity.this, LoginActivity2.class));
                                            Toast.makeText(HomeActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .setNegativeButton("No", null)
                                    .show();
                            return true;
                    }
                    return false;
                }
            });
            openFragment(new WelcomeActivity());
        }
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}