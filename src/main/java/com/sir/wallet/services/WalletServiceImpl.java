package com.sir.wallet.services;

import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

   private final WalletRepository walletRepository ;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Optional<Wallet> getWalletById(Long id) {
        return walletRepository.findById(id);
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet updateWallet(Long id, Wallet wallet) {
        return walletRepository.findById(id)
                .map(wallet1 ->{
            wallet1.setName(wallet.getName());
            wallet1.setBalance(wallet.getBalance());
            return walletRepository.save(wallet);
        }).orElseThrow(()-> new RuntimeException("wallet non trouve"));
    }

    @Override
    public String deleteWallet(Wallet wallet) {
       if (walletRepository.findById(wallet.getId())!=null)
           walletRepository.delete(wallet);

        return null;
    }

    @Override
    public Iterable<Wallet> getAllWallets() {

        return walletRepository.findAll();
    }
}
