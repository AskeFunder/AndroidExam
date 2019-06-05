package com.example.keardea.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account implements Parcelable {

    private String name;
    private String type;
    private double sum;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Account(String name, String type, double sum) {
        this.name = name;
        this.type = type;
        this.sum = sum;
    }

    protected Account(Parcel in) {
        name = in.readString();
        type = in.readString();
        sum = in.readDouble();
        transactions = in.createTypedArrayList(Transaction.CREATOR);
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public void addTransaction(Transaction transaction) {
        this.sum += transaction.getSum();

        this.transactions.add(transaction);
    }

    public void addTransaction(String source, String title, String dateString,
                               double sum, String regNumber, String accountNumber) {

        Date date = null;

        try {
            date = new SimpleDateFormat("dd-MM-yy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Transaction transaction = new Transaction(title, date, sum, accountNumber, regNumber, source);
        this.sum += transaction.getSum();
        this.transactions.add(transaction);
    }

    public void initDummyData() {
        this.addTransaction("Dankort", "Rema1000", "21-05-2019", -429.95, "", "");
        this.addTransaction("Dankort", "Netto", "21-05-2019", -39, "", "");
        this.addTransaction("Overførsel", "Frederik Nagel", "21-05-2019", 100, "", "");
        this.addTransaction("Dankort", "Rema1000", "21-05-2019", -429.95, "", "");
        this.addTransaction("Dankort", "Rema1000", "21-05-2019", -429.95, "", "");
        this.addTransaction("Dankort", "Rema1000", "21-05-2019", -429.95, "", "");
        this.addTransaction("Dankort", "Rema1000", "21-05-2019", -429.95, "", "");
        this.addTransaction("Dankort", "Rema1000", "21-05-2019", -429.95, "", "");
        this.addTransaction("Dankort", "Rema1000", "21-05-2019", -429.95, "", "");
        this.addTransaction("Dankort", "Rema1000", "21-05-2019", -429.95, "", "");
        this.addTransaction("Dankort", "Rema1000", "21-05-2019", -429.95, "", "");
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getSum() {
        return sum;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transaction> getIncomingTransactions() {
        ArrayList<Transaction> incomingTransactions = new ArrayList<>();

        for (Transaction transaction : this.transactions) {
            if (transaction.getDate().compareTo(new Date()) > 0 ){
                incomingTransactions.add(transaction);
            }
        }
        return incomingTransactions;
    }

    public List<Transaction> getPastTransactions() {
        ArrayList<Transaction> pastTransactions = new ArrayList<>();

        for (Transaction transaction : this.transactions) {
            if (transaction.getDate().compareTo(new Date()) <= 0 ){
                pastTransactions.add(transaction);
            }
        }
        return pastTransactions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeDouble(sum);
        dest.writeTypedList(transactions);
    }
}
