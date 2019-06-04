package com.example.keardea.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.keardea.R;
import com.example.keardea.model.Account;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Account> mAccountList;
    private OnCellClickListener mListener;

    public interface OnCellClickListener {
        void onCellClick(int position);

    }

    public void setOnCellClickListener(OnCellClickListener listener) {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mSum;
        public TextView mAccountType;
        public TextView mAccountName;

        public ViewHolder(@NonNull View itemView, final OnCellClickListener listener) {
            super(itemView);

            mSum = itemView.findViewById(R.id.sum);
            mAccountName = itemView.findViewById(R.id.account_name);
            mAccountType = itemView.findViewById(R.id.account_type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onCellClick(position);
                        }
                    }
                }
            });
        }
    }

    public RecyclerViewAdapter(ArrayList<Account> accountList) {
        this.mAccountList = accountList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recyclerview_cell, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Account currentAccount = mAccountList.get(i);

        viewHolder.mAccountType.setText(currentAccount.getType());
        viewHolder.mAccountName.setText(currentAccount.getName());
        viewHolder.mSum.setText(String.format("%.2f", currentAccount.getSum()));
    }

    @Override
    public int getItemCount() {
        return mAccountList.size();
    }
    }
