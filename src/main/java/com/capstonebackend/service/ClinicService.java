package com.capstonebackend.service;

import com.capstonebackend.dto.ClinicDto;
import com.capstonebackend.dto.PatientDto;
import com.capstonebackend.dto.PicDto;
import com.capstonebackend.entity.Account;
import com.capstonebackend.entity.Clinic;
import com.capstonebackend.entity.Patient;
import com.capstonebackend.entity.Pic;
import com.capstonebackend.repository.AccountRepository;
import com.capstonebackend.repository.ClinicRepository;
import com.capstonebackend.repository.PatientRepository;
import com.capstonebackend.repository.PicRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ClinicService {
    private final ClinicRepository clinicRepository;
    private final PicRepository picRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Long createClinic(ClinicDto clinicDto, Long patientId)
    {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if(optionalPatient.isEmpty())
        {
            throw new EntityNotFoundException("Patient Not Exist");
        }

        Clinic clinic = Clinic.builder()
                .clComment(clinicDto.getClComment())
                .clDate(clinicDto.getClDate())
                .result(clinicDto.getResult())
                .patient(optionalPatient.get())
                .build();
        return clinicRepository.save(clinic).getId();
    } // 진료기록 생성

    @Transactional
    public List<ClinicDto> readClinicByPatient(Long patientId)
    {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if(optionalPatient.isEmpty())
        {
            throw new EntityNotFoundException("Patient Not Exist");
        }

        List<Clinic> clinicList = clinicRepository.findByPatient(patientId);

        List<ClinicDto> clinicDtoList = new ArrayList<>();

        for(Clinic c: clinicList)
        {
            ClinicDto dto = ClinicDto.builder()
                    .clDate(c.getClDate())
                    //.clComment(c.getClComment()) // 선택 했을때만 보이게 바꿔도 될것같기도
                    .result(c.getResult())
                    //.patient(optionalPatient.get()) // 필요한지 모르겠음
                    .build();
            clinicDtoList.add(dto);
        }
        return clinicDtoList;

    } // 환자별 진료기록 출력

    @Transactional
    public ClinicDto readClinic(Long clinicId)
    {
        Optional<Clinic> optionalClinic = clinicRepository.findById(clinicId);
        if(optionalClinic.isEmpty())
            throw new EntityNotFoundException("Clinic Not Exist");

        Clinic clinic = optionalClinic.get();

        ClinicDto clinicDto = ClinicDto.builder()
                .clDate(clinic.getClDate())
                .clComment(clinic.getClComment())
                .result(clinic.getResult())
                .build();

        return clinicDto;
    } // 진료 기록 확인


    @Transactional
    public void deleteClinic(Long clinicId)
    {
        Optional<Clinic> optionalClinic = clinicRepository.findById(clinicId);
        if(optionalClinic.isEmpty())
            throw new EntityNotFoundException("Clinic Not Exist");
        List<Pic> picList = picRepository.findPicByClinic(clinicId);

        for(Pic p:  picList)
        {
            picRepository.delete(p);
        }
        clinicRepository.delete(optionalClinic.get());
    } // 진료 기록 삭제 , 관련 사진 기록 삭제

    @Transactional
    public Clinic updateClinic(Long clinicId, ClinicDto clinicDto)
    {
        Optional<Clinic> optionalClinic = clinicRepository.findById(clinicId);
        if(optionalClinic.isEmpty())
            throw new EntityNotFoundException("Clinic Not Exist");
        Clinic clinic = optionalClinic.get();
        clinic.updateClinic(clinicDto.getResult(), clinicDto.getClComment());
        return clinicRepository.save(clinic);
    } // 진료 수정

    @Transactional
    public Pic createPic(PicDto picDto, Long clinicId)
    {
        Optional<Clinic> optionalClinic = clinicRepository.findById(clinicId);
        if(optionalClinic.isEmpty())
        {
            throw new EntityNotFoundException("Clinic Not Exist");
        }
        Pic pic = Pic.builder()
                //.picUrl(picDto.picUrl) //??
                .clinic(optionalClinic.get())
                .build();
        return picRepository.save(pic);
    } // 사진 등록

    @Transactional
    public List<PicDto> readPicByClinic(Long clinicId)
    {
        Optional<Clinic> optionalClinic = clinicRepository.findById(clinicId);
        if(optionalClinic.isEmpty())
            throw new EntityNotFoundException("Clinic Not Exist");

        List<Pic> picList = picRepository.findPicByClinic(clinicId);
        if(picList.isEmpty())
            throw new EntityNotFoundException("Pic Not Exist");

        List<PicDto> picDtoList = new ArrayList<>();

        for(Pic p: picList)
        {
            PicDto dto = PicDto.builder()
                    .picUrl(p.getPicUrl())
                    .build();

            picDtoList.add(dto);
        }
        return picDtoList;
    } // 진료 별 사진 출력

    @Transactional
    public PicDto readPic(Long picId)
    {
        Optional<Pic> optionalPic = picRepository.findById(picId);
        if(optionalPic.isEmpty())
            throw new EntityNotFoundException("Pic Not Exist");
        Pic pic = optionalPic.get();
        PicDto picDto = PicDto.builder()
                .picUrl(pic.getPicUrl())
                .build();
        return picDto;
    } // 사진 출력

    @Transactional
    public void deletePic(Long picId)
    {
        Optional<Pic> optionalPic = picRepository.findById(picId);
        if(optionalPic.isEmpty())
            throw new EntityNotFoundException("Pic Not Exist");

        picRepository.delete(optionalPic.get());
    } // 사진 삭제
}
