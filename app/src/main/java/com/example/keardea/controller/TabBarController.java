package com.example.keardea.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.keardea.R;
import com.example.keardea.fragment.PayYouFragment;
import com.example.keardea.fragment.PayOtherFragment;
import com.example.keardea.fragment.TransactionFragment;
import com.example.keardea.model.Account;
import com.example.keardea.model.User;

public class TabBarController extends AppCompatActivity {

    private static final String TAG = "TabBarController";

    RecyclerView recyclerView;

    private User user;
    private Account selectedAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        user = intent.getParcelableExtra("user");
        selectedAccount = intent.getParcelableExtra("selectedAccount");

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
                    Bundle bundle = new Bundle();

                    switch (menuItem.getItemId()) {
                        case R.id.nav_pay_you:
                            selectedFragment = new PayYouFragment();
                            break;
                        case R.id.nav_pay_others:
                            selectedFragment = new PayOtherFragment();

                            System.out.println("user " + user);


                            bundle.putParcelable("selectedAccount", selectedAccount);
                            bundle.putParcelable("user", user);
                            selectedFragment.setArguments(bundle);
                            break;
                        case R.id.nav_transactions:
                            selectedFragment = new TransactionFragment();
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
