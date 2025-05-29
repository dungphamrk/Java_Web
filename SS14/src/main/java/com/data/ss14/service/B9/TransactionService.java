package com.data.ss14.service.B9;

import com.data.ss14.model.B9.Transaction;

import java.util.List;

public interface TransactionService {
    void addTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    void deleteTransaction(int index);
}
