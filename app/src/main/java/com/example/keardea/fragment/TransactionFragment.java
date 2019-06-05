package com.example.keardea.fragment;

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
import android.widget.TextView;

import com.example.keardea.HeaderDataImpl;
import com.example.keardea.R;
import com.example.keardea.adapter.TransactionAdapter;
import com.example.keardea.model.Account;
import com.example.keardea.model.Transaction;

import java.util.List;


public class TransactionFragment extends Fragment {

    private static final String TAG = "TransactionFragment";

    RecyclerView recyclerView;
    Account selectedAccount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            selectedAccount = getArguments().getParcelable("selectedAccount");
        }
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        TextView tvAccountName = view.findViewById(R.id.account_name);

        TextView tvAccountSum = view.findViewById(R.id.account_sum);

        tvAccountName.setText(selectedAccount.getName());
        tvAccountSum.setText(String.format("%.2f", selectedAccount.getSum()));


        setupRecyclerView(view);
        return view;
    }


    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);

        TransactionAdapter adapter = new TransactionAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        setData(adapter);

        System.out.println(recyclerView);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setData(TransactionAdapter adapter) {
        HeaderDataImpl headerData = new HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_1, R.layout.transaction_header);

        Log.d(TAG, "setData: " + selectedAccount.getIncomingTransactions().size() + " " + selectedAccount.getPastTransactions().size());

        adapter.setHeaderAndData(selectedAccount.getIncomingTransactions(), headerData);
        adapter.setHeaderAndData(selectedAccount.getPastTransactions(), headerData);
    }
}

