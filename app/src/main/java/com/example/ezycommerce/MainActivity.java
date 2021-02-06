package com.example.ezycommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Bundle bundle;
    public Button accessoriesBtn, businessBtn, cookbookBtn, mysteryBtn, scifiBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_round_account_circle_24));

        FragmentCategory fragmentCategory = new FragmentCategory();
        FragmentManager fragmentManager = getSupportFragmentManager();

        bundle = new Bundle();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCategory()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.cart){

        } else if(id == R.id.logout){

        }
        return  true;
    }



}