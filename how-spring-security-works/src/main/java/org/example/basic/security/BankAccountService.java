package org.example.basic.security;

import org.springframework.security.access.prepost.PostAuthorize;

public interface BankAccountService {

    @PostAuthorize("returnObject?.owner == authentication?.name")
    public BankAccount findById(long id);

    @PostAuthorize("returnObject?.owner == authentication?.name")
    public BankAccount findByAccountNumber(String accountNumber);
}
