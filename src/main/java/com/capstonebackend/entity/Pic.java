package com.capstonebackend.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Pic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String picUrl;

    @JoinColumn(name = "clinic_id")
    @OneToOne
    Clinic clinic;

    @Builder
    public Pic(String picUrl, Clinic clinic) {
        this.picUrl = picUrl;
        this.clinic = clinic;
    }


}
