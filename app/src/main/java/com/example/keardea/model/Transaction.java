package com.example.keardea.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.saber.stickyheader.stickyData.StickyMainData;

import java.util.Date;

public class Transaction implements StickyMainData, Parcelable {

    private String title;
    private Date date;
    private double sum;
    private String accountNumber;
    private String regNumber;
    private String source;
    private int viewType;

    public Transaction(String title, Date date, double sum, String accountNumber, String regNumber, String source) {
        this.title = title;
        this.date = date;
        this.sum = sum;
        this.accountNumber = accountNumber;
        this.regNumber = regNumber;
        this.source = source;
        this.viewType = -1;
    }

    public Transaction(String title, Date date, double sum, String accountNumber, String regNumber, String source, Boolean header) {
        this.title = title;
        this.date = date;
        this.sum = sum;
        this.accountNumber = accountNumber;
        this.regNumber = regNumber;
        this.source = source;
        if (header == true) {
            this.viewType = 1;
        } else {
            this.viewType = -1;
        }
    }

    protected Transaction(Parcel in) {
        title = in.readString();
        sum = in.readDouble();
        accountNumber = in.readString();
        regNumber = in.readString();
        source = in.readString();
        viewType = in.readInt();
        date = new Date(in.readLong());
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    public int getViewType() {
        return viewType;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public double getSum() {
        return sum;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getSource() {
        return source;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeDouble(sum);
        dest.writeString(accountNumber);
        dest.writeString(regNumber);
        dest.writeString(source);
        dest.writeInt(viewType);
        dest.writeLong(date.getTime());
    }
}
