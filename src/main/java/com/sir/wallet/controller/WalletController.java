package com.sir.wallet.controller;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.services.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/wallets/{id}")
    public Optional<Wallet> getWallet(@PathVariable long id) {

        return walletService.getWalletById(id);
    }

    @PutMapping("/wallets/{id}")
    public Wallet updateWallet(@PathVariable long id, @RequestBody Wallet wallet) {
        return walletService.updateWallet(id, wallet);
    }
    @DeleteMapping("/wallets/{id}")
    public String deleteWallet(@PathVariable Wallet wallet){
        return walletService.deleteWallet(wallet);
    }
    @PostMapping("/Ajouter")
    public Wallet saveWallet(@PathVariable Wallet wallet){
        return walletService.saveWallet(wallet);
    }
    @GetMapping("/wallets")
    public Iterable<Wallet> getAllWallet() {
        return walletService.getAllWallets();
    }
}
