package org.example.basic.security;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountServiceImplTest {
    BankAccountServiceImpl bankAccountService = new BankAccountServiceImpl();

    void login(String username, String password) {
        Authentication auth = new TestingAuthenticationToken(username, password, "ROLE_USER");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    void findByIdWhenGrantedAccess() {
        login("andreas", "password");
        BankAccount bankAccount = bankAccountService.findById(1L);
        assertThat(bankAccount).isNotNull();
    }

    @Test
    void findByIdWhenDeniedAccess() {
        login("joe", "secret");
        assertThatExceptionOfType(AccessDeniedException.class).isThrownBy(() -> bankAccountService.findById(1L));
    }

    @Test
    void findByAccountNumberWhenGrantedAccess() {
        login("joe", "secret");
        BankAccount bankAccount = bankAccountService.findByAccountNumber("1234");
        assertThat(bankAccount).isNotNull();
    }

    @Test
    void findByAccountNumberWhenDeniedAccess() {
        login("andreas", "password");
        assertThatExceptionOfType(AccessDeniedException.class).isThrownBy(() -> bankAccountService.findByAccountNumber("1234"));
    }

    @AfterEach
    void logout() {
        SecurityContextHolder.clearContext();
    }
}