package com.data.ss14.service.B9;

import com.data.ss14.repository.B9.TransactionDAO;
import com.data.ss14.model.B9.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDAO transactionDAO;

    @Override
    public void addTransaction(Transaction transaction) {
        transactionDAO.addTransaction(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    @Override
    public void deleteTransaction(int index) {
        transactionDAO.deleteTransaction(index);
    }
}

