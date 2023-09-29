package org.sisvir.msvc.patientvitalsigns.persistence.impl;

import org.sisvir.msvc.patientvitalsigns.models.entities.PatientVitalSigns;
import org.sisvir.msvc.patientvitalsigns.persistence.PatientVitalSignsDAO;
import org.sisvir.msvc.patientvitalsigns.repositories.PatientVitalSignsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class PatientVitalSignsDAOImpl implements PatientVitalSignsDAO {

    @Autowired
    private PatientVitalSignsRepository vitalSignsRepository;


    @Override
    @Transactional(readOnly = true)
    public List<PatientVitalSigns> findAll() {
        return (List<PatientVitalSigns>) vitalSignsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientVitalSigns> findById(Long id) {
        return vitalSignsRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientVitalSigns> findByPatientId(Long patientId) {
        return vitalSignsRepository.findByPatientId(patientId);
    }

    @Override
    @Transactional
    public void create(PatientVitalSigns vitalSigns) {
        vitalSignsRepository.save(vitalSigns);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        vitalSignsRepository.deleteById(id);
    }
}
