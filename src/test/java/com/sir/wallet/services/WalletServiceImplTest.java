package com.sir.wallet.services;

import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class WalletServiceImplTest {
 @InjectMocks
 WalletServiceImpl walletService;
 @Mock
 WalletRepository walletRepository;
    @Test
    void getWalletById() {
        Wallet wallet =new Wallet(8l,"toto",200);
        when(walletRepository.save(wallet)).thenReturn(wallet);
        Wallet reponseGetAllId=walletService.saveWallet(wallet);
        assertNotNull(reponseGetAllId);
        walletRepository.findById(wallet.getId());
    }

    @Test
    void saveWallet() {
        //when
        Wallet wallet = new Wallet(1l,"don",2000);
        when(walletRepository.save(wallet)).thenReturn(wallet);
        //when
        Wallet reponse=walletService.saveWallet(wallet);
        //then
        assertEquals(1l,reponse.getId());
        verify(walletRepository,times(1)).save(wallet);
    }

    @Test
    void updateWallet() {
        Wallet wallet = new Wallet(2l,"bay",900) ;
        wallet.setName("koko");
        wallet.setBalance(3000);
        when(walletRepository.save(wallet)).thenReturn(wallet);
        Wallet  reponseUpdate=walletService.saveWallet(wallet);
        assertNotNull(reponseUpdate);
        assertEquals(reponseUpdate.getName(),wallet.getName());
        assertEquals(reponseUpdate.getBalance(),wallet.getBalance());
        verify(walletRepository,times(1)).save(wallet);

    }

    @Test
    void deleteWallet() {
        Wallet wallet = new Wallet(2l,"ka",1900);
        when(walletRepository.save(wallet)).thenReturn(wallet);
        Wallet reponseDelete=walletService.saveWallet(wallet);
        if(walletRepository.findById(wallet.getId())!=null)
            walletRepository.delete(wallet);


    }

    @Test
    void getAllWallets() {
        List<Wallet> wallets =new ArrayList<Wallet>();
        wallets.add(new Wallet(1l,"boubs",900));
        wallets.add(new Wallet(2l,"sow",8000));
        wallets.add(new Wallet(3l,"ka",700));
        wallets.add(new Wallet(4l,"dame",400));
        when(walletRepository.saveAll(wallets)).thenReturn(wallets);
        List<Wallet> walllet1 =(List <Wallet >) walletService.getAllWallets();


    }
}