package com.capstonebackend.dto;

import com.capstonebackend.entity.Clinic;
import lombok.Builder;

public class PicDto {
    private Long id;

    private String picUrl;

    private Clinic clinic;

    @Builder
    public PicDto(String picUrl, Clinic clinic)
    {
        this.picUrl = picUrl;
        this.clinic = clinic;
    }
}
