package com.capstonebackend.entity;

import com.capstonebackend.entity.enums.Result;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate clDate;

    @Column(nullable = false)
    @Enumerated
    private Result result;

    @Column(nullable = true)
    private String clComment;

    @JoinColumn(name = "patient_id")
    @ManyToMany
    Patient patient;

    public void updateClinic(Result result, String clComment)
    {
        this.result = result;
        this.clComment = clComment;
    }

    @Builder
    public Clinic(LocalDate clDate, Result result, String clComment, Patient patient)
    {
        this.clDate = clDate;
        this.result = result;
        this.clComment = clComment;
        this.patient = patient;
    }

}
