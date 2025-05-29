package com.data.ss14.repository.B9;

import com.data.ss14.model.B9.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
    private final List<Transaction> transactionList = new ArrayList<>();

    @Override
    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionList;
    }

    @Override
    public void deleteTransaction(int index) {
        if (index >= 0 && index < transactionList.size()) {
            transactionList.remove(index);
        }
    }
}

