package com.capstonebackend.repository;

import com.capstonebackend.dto.PicDto;
import com.capstonebackend.entity.Pic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PicRepository extends JpaRepository<Pic, Long> {
    List<Pic> findPicByClinic(Long clinicId);
}
