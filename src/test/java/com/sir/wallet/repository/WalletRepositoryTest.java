package com.sir.wallet.repository;

import com.sir.wallet.model.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WalletRepositoryTest {
    @Autowired
    WalletRepository walletRepository;

    @Test
    void saveWallet() {
        Wallet wallet = new Wallet(1L, "compte", 2000);
        Wallet reponseWallet = walletRepository.save(wallet);
        assertNotNull(reponseWallet);
        assertEquals(reponseWallet.getName(), wallet.getName());
    }

    @Test
    void updateWallet() {
        Wallet wallet = walletRepository.save(new Wallet(2l, "epargne", 40000));
        wallet.setName("porte");
        wallet.setBalance(700);
        Wallet reponseUpdate = walletRepository.save(wallet);
        assertNotNull(reponseUpdate);
        assertEquals(reponseUpdate.getName(), wallet.getName());
        assertEquals(reponseUpdate.getBalance(), wallet.getBalance());
    }

    @Test
    void deleteWallet() {
        Wallet wallet = walletRepository.save(new Wallet(3l, "tata", 300));

        if(walletRepository.findById(wallet.getId())!=null)
            walletRepository.delete(wallet);
    }
    @Test
    void getAllWallet(){
        List<Wallet> wallets = new ArrayList<Wallet>();
        wallets.add(new Wallet(1L,"boubs", 150));
        wallets.add(new Wallet(2L, "Abdoulaye",1024));
        wallets.add(new Wallet(4L, "baye", 16724));
        walletRepository.saveAll(wallets);

        List<Wallet> wallets1 = (List<Wallet>) walletRepository.findAll();
        assertTrue(wallets1.size() > 2
        );

    }
}