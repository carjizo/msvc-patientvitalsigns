package org.sisvir.msvc.patientvitalsigns.services.impl;

import org.sisvir.msvc.patientvitalsigns.models.entities.PatientVitalSigns;
import org.sisvir.msvc.patientvitalsigns.persistence.PatientVitalSignsDAO;
import org.sisvir.msvc.patientvitalsigns.services.PatientVitalSignsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientVitalSignsServiceImpl implements PatientVitalSignsService {

    @Autowired
    private PatientVitalSignsDAO vitalSignsDAO;

    @Override
    public List<PatientVitalSigns> findAll() {
        return vitalSignsDAO.findAll();
    }

    @Override
    public Optional<PatientVitalSigns> findById(Long id) {
        return vitalSignsDAO.findById(id);
    }

    @Override
    public Optional<PatientVitalSigns> findByPatientId(Long patientId) {
        return vitalSignsDAO.findByPatientId(patientId);
    }

    @Override
    public void create(PatientVitalSigns vitalSigns) {
        vitalSignsDAO.create(vitalSigns);
    }

    @Override
    public void deleteById(Long id) {
        vitalSignsDAO.deleteById(id);
    }
}
