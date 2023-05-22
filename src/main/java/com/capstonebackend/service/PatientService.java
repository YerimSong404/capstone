package com.capstonebackend.service;

import com.capstonebackend.dto.PatientDto;
import com.capstonebackend.entity.Account;
import com.capstonebackend.entity.Clinic;
import com.capstonebackend.entity.Patient;
import com.capstonebackend.entity.Pic;
import com.capstonebackend.repository.AccountRepository;
import com.capstonebackend.repository.ClinicRepository;
import com.capstonebackend.repository.PatientRepository;
import com.capstonebackend.repository.PicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {

    private final AccountRepository accountRepository;
    private final PatientRepository patientRepository;

    private final ClinicRepository clinicRepository;
    private final PicRepository picRepository;

    @Transactional
    public Long createPatient(PatientDto patientDto, Long accountId)
    {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isEmpty())
        {
            throw new EntityNotFoundException("Account Not Exist");
        }
        Patient patient = Patient.builder()
                .paName(patientDto.getPaName())
                .paAge(patientDto.getPaAge())
                .paNum(patientDto.getPaNum())
                .account(optionalAccount.get())
                .build();
        return patientRepository.save(patient).getId();
    } // 환자 등록

    @Transactional
    public PatientDto readPatient(Long patientId)
    {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if(optionalPatient.isEmpty())
            throw new EntityNotFoundException("Patient Not Exsit");

        Patient patient = optionalPatient.get();

        PatientDto patientDto = PatientDto.builder()
                .paName(patient.getPaName())
                .paAge(patient.getPaAge())
                .paNum(patient.getPaNum())
                .build();
        return patientDto;
    } // 환자 정보 읽기


    @Transactional
    public Patient updatePatient(Long patientId, PatientDto patientDto)
    {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if(optionalPatient.isEmpty())
            throw new EntityNotFoundException("Patient Not Exist");
        Patient patient = optionalPatient.get();
        patient.updatePatient(patientDto.getPaName(), patientDto.getPaAge(), patientDto.getPaNum());
        return patientRepository.save(patient);
    } // 환자정보 수정


    @Transactional
    public List<PatientDto> readPatientByAccount(Long AccountId)
    {
        List<Patient> patientList = patientRepository.findByAccount(AccountId);
        if(patientList.isEmpty())
        {
            throw new EntityNotFoundException("Patient Not Exist");
        }
        List<PatientDto> patientDtoList = new ArrayList<>();

        for(Patient p : patientList)
        {
            PatientDto dto = PatientDto.builder()
                    .paName(p.getPaName())
                    .paAge(p.getPaAge())
                    .paNum(p.getPaNum())
                    .build();
            patientDtoList.add(dto);
        }
        return patientDtoList;
    } // 계좌별 환자 출력

    @Transactional
    public List<PatientDto> readPatientByName(String patientName)
    {
        List<Patient> patientList = patientRepository.findByPaName(patientName);
        if(patientList.isEmpty())
        {
            throw new EntityNotFoundException("Patient Not Exist");
        }
        List<PatientDto> patientDtoList = new ArrayList<>();

        for(Patient p: patientList)
        {
            PatientDto dto = PatientDto.builder()
                    .paName(p.getPaName())
                    .paAge(p.getPaAge())
                    .paNum(p.getPaNum())
                    .build();
            patientDtoList.add(dto);
        }
        return patientDtoList;
    } // 이름으로 환자 검색


    @Transactional
    public void deletePatient(Long patientId)
    {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if(optionalPatient.isEmpty())
        {
            throw new EntityNotFoundException("Patient Not Found");
        }

        Patient patient = optionalPatient.get();
        List<Clinic> clinicList = clinicRepository.findByPatient(patientId);
        for(Clinic c: clinicList)
        {
            List<Pic> picList = picRepository.findPicByClinic(c.getId());
            for(Pic p: picList)
            {
                picRepository.delete(p);
            }
            clinicRepository.delete(c);
        }
    } // 사진 삭제 -> 클리닉 삭제 -> 환자 삭제
}
