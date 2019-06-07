package com.example.keardea.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keardea.HeaderDataImpl;
import com.example.keardea.R;
import com.example.keardea.adapter.RecentTransactionsAdapter;
import com.example.keardea.adapter.TransactionAdapter;
import com.example.keardea.model.Account;
import com.example.keardea.model.Transaction;
import com.example.keardea.model.User;

import java.util.ArrayList;
import java.util.List;

import co.ceryle.segmentedbutton.SegmentedButtonGroup;
import info.hoang8f.android.segmented.SegmentedGroup;

public class PayOtherFragment extends Fragment {

    Account selectedAccount;
    User user;

    private static final String TAG = "PayOtherFragment";

    private RecyclerView recyclerView;
    private EditText etName;
    private EditText etRegNumber;
    private EditText etAccountNumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_payothers, container, false);

        etName = view.findViewById(R.id.name);
        etRegNumber = view.findViewById(R.id.reg_number);
        etAccountNumber = view.findViewById(R.id.account_number);

        if (getArguments() != null) {
            System.out.println("getArguments was not null");
            selectedAccount = getArguments().getParcelable("selectedAccount");
            user = getArguments().getParcelable("user");


            TextView tvSum = view.findViewById(R.id.sum);
            tvSum.setText(selectedAccount.getName() + "(" + String.format("%.2f", selectedAccount.getSum()) + ")");
        }

        if (selectedAccount != null) {
            setupRecyclerView(view, new ArrayList<Transaction>(user.getFrequentPersons()));
        }

        SegmentedGroup segmented = (SegmentedGroup) view.findViewById(R.id.segmented_group);
        segmented.setTintColor(R.color.design_default_color_primary_dark);
        
        

        RadioButton button1 = view.findViewById(R.id.button21);
        
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //load in all non +71 account numbers
                Log.d(TAG, "onClick: button1Tapped");
                setupRecyclerView(getView(), new ArrayList<Transaction>(user.getFrequentPersons()));
            }
        });
        
        RadioButton button2 = view.findViewById(R.id.button22);
        
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //load in all +71 account numbers only
                Log.d(TAG, "onClick: button2Tapped");
                setupRecyclerView(getView(), new ArrayList<Transaction>(user.getFrequentBills()));
            }
        });
        
        button1.setChecked(true);

        return view;
    }

    private void setupRecyclerView(View view, final List<Transaction> recentTransactions) {
        recyclerView = view.findViewById(R.id.recycler_view);

        RecentTransactionsAdapter adapter = new RecentTransactionsAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        setData(adapter, recentTransactions);

        System.out.println(recyclerView);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnCellClickListener(new RecentTransactionsAdapter.OnCellClickListener() {
            @Override
            public void onCellClick(int position) {
                Transaction selectedTranaction = recentTransactions.get(position - 1);

                etName.setText(selectedTranaction.getTitle());
                etAccountNumber.setText(selectedTranaction.getAccountNumber());
                etRegNumber.setText(selectedTranaction.getRegNumber());
            }
        });
    }

    private void setData(RecentTransactionsAdapter adapter, List<Transaction> recentTransactions) {
        HeaderDataImpl headerData = new HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_1, R.layout.transaction_header);



            Log.d(TAG, "setData: " + selectedAccount.getIncomingTransactions().size() + " " + selectedAccount.getPastTransactions().size());

            Log.d(TAG, "setData: user " + user);

            adapter.setHeaderAndData(recentTransactions, headerData);
    }
}
