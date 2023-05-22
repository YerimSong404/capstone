package com.capstonebackend.dto;

import com.capstonebackend.entity.Account;
import lombok.Builder;
import lombok.Data;

@Data
public class PatientDto {
    private Long id;

    private String paName;

    private Integer paAge;

    private String paNum;

    private Account account;

    @Builder
    public PatientDto(String paName, Integer paAge, String paNum, Account account)
    {
        this.paName = paName;
        this.paAge = paAge;
        this.paNum = paNum;
        this.account = account;
    }
}
