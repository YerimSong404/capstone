package com.capstonebackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AccountDto {

    private Long id;

    private String userid;
    private String userPass;

    private String userName;

    private Long userNum;

    @Builder
    public AccountDto(String userPass, String userName, Long userNum)
    {
        this.userPass = userPass;
        this.userName = userName;
        this.userNum = userNum;
    }
}

