package com.capstonebackend.dto;

import com.capstonebackend.entity.Patient;
import com.capstonebackend.entity.enums.Result;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClinicDto {
    private Long id;

    private LocalDate clDate;

    private Result result;

    private String clComment;

    private Patient patient;

    @Builder
    public ClinicDto(LocalDate clDate, Result result, String clComment, Patient patient)
    {
        this.clDate = clDate;
        this.result =result;
        this.clComment = clComment;
        this.patient = patient;
    }
}
