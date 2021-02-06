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

        accessoriesBtn = findViewById(R.id.accessories);
        accessoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("category", "accessories");
                FragmentCategory fragObject = FragmentCategory.newInstance("accessories");
                fragObject.setArguments(bundle);
            }
        });

        businessBtn = findViewById(R.id.business);
        businessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("category", "business");
                FragmentCategory fragObject = FragmentCategory.newInstance("business");
                fragObject.setArguments(bundle);
            }
        });

        cookbookBtn = findViewById(R.id.cookbook);
        cookbookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("category", "cookbook");
                FragmentCategory fragObject = FragmentCategory.newInstance("cookbook");
                fragObject.setArguments(bundle);
            }
        });

        mysteryBtn = findViewById(R.id.mystery);
        mysteryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("category", "mystery");
                FragmentCategory fragObject = FragmentCategory.newInstance("mystery");
                fragObject.setArguments(bundle);
            }
        });

        scifiBtn = findViewById(R.id.scifi);
        scifiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("category", "scifi");
                FragmentCategory fragObject = FragmentCategory.newInstance("scifi");
                fragObject.setArguments(bundle);
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCategory()).commit();

        if (savedInstanceState == null) {
            bundle.putString("category", "accessories");
            FragmentCategory fragObject = FragmentCategory.newInstance("accessories");
            fragObject.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCategory()).commit();
        }

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