package org.sisvir.msvc.patientvitalsigns.services;

import org.sisvir.msvc.patientvitalsigns.models.entities.PatientVitalSigns;

import java.util.List;
import java.util.Optional;

public interface PatientVitalSignsService {

    List<PatientVitalSigns> findAll();

    Optional<PatientVitalSigns> findById(Long id);

    Optional<PatientVitalSigns> findByPatientId(Long patientId);

    void create(PatientVitalSigns vitalSigns);

    void deleteById(Long id);
}
