package com.example.keardea.adapter;

import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.keardea.HeaderDataImpl;
import com.example.keardea.R;
import com.example.keardea.model.Transaction;
import com.saber.stickyheader.stickyData.HeaderData;
import com.saber.stickyheader.stickyView.StickHeaderRecyclerView;

import java.util.zip.Inflater;

public class TransactionAdapter extends StickHeaderRecyclerView<Transaction, HeaderDataImpl> {


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        switch (viewType) {
            case HeaderDataImpl.HEADER_TYPE_1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_header, parent, false);
                return new HeaderViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_cell, parent, false);
                return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolder) {
            ((ViewHolder) viewHolder).bindData(position);
        } else if (viewHolder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) viewHolder).bindData(position);
        }
    }

    @Override
    public void bindHeaderData(View header, int headerPosition) {

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bindData(int position) {

        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bindData(int position) {

        }
    }

}
