package com.capstonebackend.entity;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPass;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private Long userNum;

    @Builder
    public Account(String userId, String userPass, String userName, Long userNum)
    {
        this.userId = userId;
        this.userPass = userPass;
        this.userName = userName;
        this.userNum = userNum;
    }

}
