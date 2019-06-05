package com.example.keardea.controller;

import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.keardea.HeaderDataImpl;
import com.example.keardea.R;
import com.example.keardea.adapter.TransactionAdapter;
import com.example.keardea.fragment.ChatFragment;
import com.example.keardea.fragment.MessageFragment;
import com.example.keardea.fragment.TransactionFragment;
import com.example.keardea.model.Account;
import com.example.keardea.model.Transaction;
import com.example.keardea.model.User;
import com.saber.stickyheader.stickyData.HeaderData;

import java.util.ArrayList;
import java.util.List;

public class TabBarController extends AppCompatActivity {

    private static final String TAG = "TabBarController";

    RecyclerView recyclerView;

    private User user;
    private Account selectedAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectedAccount = getIntent().getParcelableExtra("selectedAccount");

        Log.d(TAG, "onCreate: " + selectedAccount.getTransactions().size() + " " + selectedAccount.getPastTransactions().size());
        Log.d(TAG, "onCreate: " + selectedAccount.getName());

        setContentView(R.layout.activity_tab_bar_controller);
        setupBottomNavBar();
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new ChatFragment();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new MessageFragment();
                            break;
                        case R.id.nav_transactions:
                            selectedFragment = new TransactionFragment();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("selectedAccount", selectedAccount);
                            selectedFragment.setArguments(bundle);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    private void setupBottomNavBar() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }

}
