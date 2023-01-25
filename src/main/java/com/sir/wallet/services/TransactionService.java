package com.sir.wallet.services;

import com.sir.wallet.model.Transaction;

import java.util.Optional;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction );

    Optional<Transaction> getTransactionById(Long id);

    //Transaction updateTransaction(Transaction transaction);

    //Transaction updateTransaction(Transaction transaction, Long id);

   // void deleteTransaction(Transaction transaction);

    //String deleteTransaction(Long id);

    //void deleteTransaction(Transaction transaction, long id);

    Transaction updateTransaction(Transaction transaction, long id);

    void deleteTransaction(Transaction transaction);

    Iterable<Transaction> getAllTransactions();
}
