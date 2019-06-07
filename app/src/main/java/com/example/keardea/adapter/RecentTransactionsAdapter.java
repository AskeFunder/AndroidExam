package com.example.keardea.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keardea.HeaderDataImpl;
import com.example.keardea.R;
import com.example.keardea.model.Transaction;
import com.saber.stickyheader.stickyView.StickHeaderRecyclerView;

public class RecentTransactionsAdapter extends StickHeaderRecyclerView<Transaction, HeaderDataImpl> {

    private static final String TAG = "TransactionAdapter";

    OnCellClickListener mListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        switch (viewType) {
            case HeaderDataImpl.HEADER_TYPE_1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_header, parent, false);
                return new HeaderViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_recent_transaction, parent, false);
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

        tvTitle.setText("Tidligere anvendt");
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onCellClick(position);
                        }
                    }
                }
            });
        }

        void bindData(int position) {
            Log.d(TAG, "bindData: ViewHolder");
            Transaction currentTransaction = getDataInPosition(position);

            TextView tvName = itemView.findViewById(R.id.name);
            TextView tvAccountNumber = itemView.findViewById(R.id.account_number);

            tvName.setText(currentTransaction.getTitle());
            tvAccountNumber.setText(currentTransaction.getAccountNumber());
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
                tvTitle.setText("Tidligere anvendt");
            } else {
                tvTitle.setText("Ældre");
            }

        }
    }

    public interface OnCellClickListener {
        void onCellClick(int position);

    }

    public void setOnCellClickListener(RecentTransactionsAdapter.OnCellClickListener listener) {
        mListener = listener;
    }


}
