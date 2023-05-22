package com.capstonebackend.controller;

import com.capstonebackend.dto.AccountDto;
import com.capstonebackend.service.AccountService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@JsonAutoDetect
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class testController {
    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody AccountDto accountDto)
    {
        Long accountId = accountService.SignUp(accountDto);
        return ResponseEntity.ok().body(accountId);
    }
}
