package com.capstonebackend.service;

import com.capstonebackend.dto.AccountDto;
import com.capstonebackend.entity.Account;
import com.capstonebackend.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Long SignUp(AccountDto accountDto)
    {
        Account account = Account.builder()
                .userId(accountDto.getUserid())
                .userPass(accountDto.getUserPass())
                .userName(accountDto.getUserName())
                .userNum(accountDto.getUserNum())
                .build();

        return accountRepository.save(account).getId();
    }

    @Transactional
    public void deleteAccount(Long accountId)
    {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isEmpty())
        {
            log.error("Account Not Exist");
            throw new EntityNotFoundException("Empty Account");
        }
        accountRepository.delete(optionalAccount.get());
    }
}
