package org.example.basic.security;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authorization.AuthorizationProxyFactory;
import org.springframework.security.authorization.method.AuthorizationAdvisorProxyFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class BankAccountServiceIntegrationTest {
    AuthorizationProxyFactory authorizationProxyFactory = AuthorizationAdvisorProxyFactory.withDefaults();
    BankAccountService bankAccountService = (BankAccountService) authorizationProxyFactory.proxy(new BankAccountServiceImpl());

    @WithMockUser(username = "andreas")
    @Test
    void findByIdWhenGrantedAccess() {
        BankAccount bankAccount = bankAccountService.findById(1L);
        assertThat(bankAccount).isNotNull();
    }

    @WithMockUser(username = "joe")
    @Test
    void findByIdWhenDeniedAccess() {
        assertThatExceptionOfType(AccessDeniedException.class).isThrownBy(() -> bankAccountService.findById(1L));
    }

    @WithMockUser(username = "joe")
    @Test
    void findByAccountNumberWhenGrantedAccess() {
        BankAccount bankAccount = bankAccountService.findByAccountNumber("1234");
        assertThat(bankAccount).isNotNull();
    }

    @WithMockUser(username = "andreas")
    @Test
    void findByAccountNumberWhenDeniedAccess() {
        assertThatExceptionOfType(AccessDeniedException.class).isThrownBy(() -> bankAccountService.findByAccountNumber("1234"));
    }

    @AfterEach
    void logout() {
        SecurityContextHolder.clearContext();
    }
}