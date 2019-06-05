package com.example.keardea.adapter;

import android.graphics.Color;
import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.keardea.HeaderDataImpl;
import com.example.keardea.R;
import com.example.keardea.model.Transaction;
import com.saber.stickyheader.stickyData.HeaderData;
import com.saber.stickyheader.stickyView.StickHeaderRecyclerView;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

public class TransactionAdapter extends StickHeaderRecyclerView<Transaction, HeaderDataImpl> {

    private static final String TAG = "TransactionAdapter";

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
        Log.d(TAG, "bindHeaderData: ");
        TextView tvTitle = header.findViewById(R.id.tv_header);
        if (headerPosition == 0) {
            tvTitle.setText("Kommende betalinger");
        } else {
            tvTitle.setText("Ældre");
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bindData(int position) {
            Log.d(TAG, "bindData: ViewHolder");
            Transaction currentTransaction = getDataInPosition(position);

            TextView tvTransactionTitle = itemView.findViewById(R.id.title);
            TextView tvTransactionSource = itemView.findViewById(R.id.source);
            TextView tvTransactionSum = itemView.findViewById(R.id.sum);

            tvTransactionTitle.setText(currentTransaction.getTitle());
            tvTransactionSource.setText(currentTransaction.getSource());

            double sum = currentTransaction.getSum();
            if (sum > 0) {
                tvTransactionSum.setTextColor(Color.parseColor("#42f498"));
            }
            tvTransactionSum.setText(String.format("%.2f", sum));


        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;


        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            Log.d(TAG, "HeaderViewHolder: hello");
            tvTitle = itemView.findViewById(R.id.tv_header);

        }

        void bindData(int position) {

            Log.d(TAG, "bindData: HeaderViewHolder");

            if (position == 0) {
                tvTitle.setText("Kommende betalinger");
            } else {
                tvTitle.setText("Ældre");
            }

        }
    }


}
