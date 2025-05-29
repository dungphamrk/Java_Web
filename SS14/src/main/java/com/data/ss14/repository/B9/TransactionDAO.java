package com.data.ss14.repository.B9;

import com.data.ss14.model.B9.Transaction;

import java.util.List;

public interface TransactionDAO {
    void addTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    void deleteTransaction(int index);
}

