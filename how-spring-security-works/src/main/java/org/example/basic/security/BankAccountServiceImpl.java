package org.example.basic.security;

import java.math.BigDecimal;

public class BankAccountServiceImpl implements BankAccountService {
    @Override
    public BankAccount findById(long id) {
        BankAccount bankAccount = new BankAccount(id, "andreas", "123", BigDecimal.valueOf(500L));
        /*Principal principal = SecurityContextHolder.getContext().getAuthentication();
        if (!bankAccount.getOwner().equals(principal.getName())) {
            throw new AccessDeniedException("Access denied for " + principal.getName());
        }*/
        return bankAccount;
    }

    @Override
    public BankAccount findByAccountNumber(String accountNumber) {
        BankAccount bankAccount = new BankAccount(99L, "joe", accountNumber, BigDecimal.valueOf(1000L));
        /*Principal principal = SecurityContextHolder.getContext().getAuthentication();
        if (!bankAccount.getOwner().equals(principal.getName())) {
            throw new AccessDeniedException("Access denied for " + principal.getName());
        }*/
        return bankAccount;
    }
}
