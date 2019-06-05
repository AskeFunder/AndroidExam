package com.example.keardea.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.keardea.adapter.RecyclerViewAdapter;
import com.example.keardea.fragment.ChatFragment;
import com.example.keardea.fragment.MessageFragment;
import com.example.keardea.R;
import com.example.keardea.model.Account;
import com.example.keardea.model.User;

public class AccountViewController extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //var
    private User user;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DrawerLayout drawer;
    private Button button;
    private static final String TAG = "AccountViewController";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_view_controller);

        button = findViewById(R.id.login_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TabBarController.class);
                startActivity(intent);
            }
        });

        setupDummyInformation();

        setupRecyclerView();
        setupDrawer(savedInstanceState);


    }

    private void setupRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerViewAdapter(user.getAccounts());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnCellClickListener(new RecyclerViewAdapter.OnCellClickListener() {
            @Override
            public void onCellClick(int position) {
                Account selectedAccount = user.getAccounts().get(position);

                Intent intent = new Intent(getApplicationContext(), TabBarController.class);
                intent.putExtra("selectedAccount", selectedAccount);
                startActivity(intent);
            }
        });
    }

    private void setupDummyInformation() {
        user = new User("Aske Funder Jensen");

        Account account1 = new Account("Opsparing", "opsparing", 10000);
        Account account2 = new Account("Forbrug", "forbrug", 10000);

        account2.initDummyData();

        user.addAccount(account1);
        user.addAccount(account2);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setupDrawer(Bundle savedInstanceState){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MessageFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_message);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MessageFragment()).commit();
                Log.d(TAG, "onNavigationItemSelected: nav_message");
                
                break;
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChatFragment()).commit();
                Log.d(TAG, "onNavigationItemSelected: nav chat");
                break;
            case R.id.nav_external:
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
