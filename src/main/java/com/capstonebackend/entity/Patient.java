package com.capstonebackend.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String paName;

    @Column(nullable = false)
    private Integer paAge;

    @Column(nullable = false)
    private String paNum;

    @JoinColumn(name = "account_id")
    @ManyToOne
    Account account;

    public void updatePatient(String paName, Integer paAge, String paNUm)
    {
        this.paName = paName;
        this.paNum = paNum;
        this.paAge = paAge;
    }

    @Builder
    public Patient(String paName, Integer paAge, String paNum, Account account)
    {
        this.paName = paName;
        this.paAge = paAge;
        this.paNum = paNum;
        this.account = account;
    }
}
