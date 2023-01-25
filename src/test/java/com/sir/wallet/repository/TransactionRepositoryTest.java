package com.sir.wallet.repository;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.services.TransactionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")

class TransactionRepositoryTest {
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    @Mock
    TransactionService transactionService;
    @Test
    public void Trans(){
        Wallet wallet = walletRepository.save(new Wallet(1L,"boubs", 1200));
        Transaction transaction = new Transaction();
        transaction.setType("normal");
        transaction.setAmount(1200);
        transaction.setWallet(wallet);
        transactionRepository.save(transaction);
        assertEquals(1200,transaction.getAmount());
    }
    @Test
    public void getTransactionById(){

        //Given
        Wallet wallet = walletRepository.save(new Wallet(1L,"boubs", 1200));
        Transaction transaction = transactionRepository.save(new Transaction(wallet, 1200, "normal"));
        //When
        Optional<Transaction> transaction1 = transactionRepository.findById(transaction.getId());
        //Then
        assertNotNull(transaction1);
        assertEquals(transaction.getAmount(), transaction1.get().getAmount());
        assertEquals(transaction.getId(), transaction1.get().getId());
    }
    @Test
    public void update(){
        //Given
        Wallet wallet = walletRepository.save(new Wallet(1L,"boubs", 1200));
        Transaction transaction = transactionRepository.save(new Transaction(wallet, 1500, "normal"));
        transaction.setAmount(300);
        transaction.setType("anormal");

        //When
        Transaction transaction1 = transactionRepository.save(transaction);
        //Then
        assertEquals(300, transaction1.getAmount());
        assertEquals("anormal", transaction1.getType());
        assertEquals(transaction.getWallet(), transaction1.getWallet());
    }
    @Test
    public void deleteTransaction(){
        Wallet wallet  =  walletRepository.save(new Wallet(1L, "boubs", 1200));
        Transaction transaction= transactionRepository.save(new Transaction(wallet, 1200, "normal"));
        transactionRepository.delete(transactionRepository.findById(transaction.getId()).get());

        Transaction transaction1=null;

        Optional<Transaction> transactionOptional = transactionRepository.findById(transaction.getId());

        if (transactionOptional.isPresent()){
            transaction1 = transactionOptional.get();
        }
        Assertions.assertThat(transaction1).isNull();
    }
    @Test
    public void getAllTransaction(){
        Wallet wallet  =  walletRepository.save(new Wallet(1L, "boubs", 1200));
        Wallet wallet1  =  walletRepository.save(new Wallet(2L, "diallo", 1500));
        List<Transaction> trans = new ArrayList<Transaction>();
        trans.add(new Transaction(wallet,1200, "boubs"));
        trans.add(new Transaction(wallet1, 2000,"abdoulaye"));
        transactionRepository.saveAll(trans);
        List<Transaction> transactionList1 = (List<Transaction>) transactionRepository.findAll();
        assertTrue(transactionList1.size() >=2);
    }
   /* @Test
    public void debiter(){
        Wallet wallet1= walletRepository.save(new Wallet(1L, "compte1", 1500));
        Transaction transaction1= transactionRepository.save(new Transaction(wallet1, 200, "normal"));
        transactionService.debiter(transaction1, wallet1);
        assertEquals(1300, wallet1.getBalance());

    }*/

}