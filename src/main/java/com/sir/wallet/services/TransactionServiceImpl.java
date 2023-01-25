package com.sir.wallet.services;
import com.sir.wallet.repository.TransactionRepository;


import com.sir.wallet.model.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction, long id) {
        return transactionRepository.findById(id)
                .map(transaction1 ->{
                    transaction1.setAmount(transaction.getAmount());
                    transaction1.setType(transaction.getType());
                    return transactionRepository.save(transaction1);
                }).orElseThrow(()-> new RuntimeException("transaction non trouve"));
    }@Override
    public void deleteTransaction(Transaction transaction) {
        if (transactionRepository.findById(transaction.getId())!=null)
        transactionRepository.delete(transaction);
    }
    @Override
    public Iterable<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
