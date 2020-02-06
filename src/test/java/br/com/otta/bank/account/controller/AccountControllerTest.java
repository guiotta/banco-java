package br.com.otta.bank.account.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.BDDMockito.given;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.google.common.collect.Lists;

import br.com.otta.bank.account.model.AccountInformation;
import br.com.otta.bank.account.service.AccountService;

/**
 * Testes unitários do componente {@link accountController}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
    @Mock
    private AccountService service;
    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountInformation accountInformation;

    @BeforeEach
    protected void setUp() throws Exception {
        given(service.findAll()).willReturn(Lists.newArrayList(accountInformation));
    }

    @Test
    public void shouldCorrectlyCallServiceWhenFindAllIsCalled() throws Exception {
        // given
        // when
        ResponseEntity<Collection<AccountInformation>> actualValue = accountController.findAll();
        // then
        assertThat(actualValue.getBody(), contains(accountInformation));
    }

}
