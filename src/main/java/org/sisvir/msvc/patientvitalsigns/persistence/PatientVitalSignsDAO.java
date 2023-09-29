package org.sisvir.msvc.patientvitalsigns.persistence;

import org.sisvir.msvc.patientvitalsigns.models.entities.PatientVitalSigns;

import java.util.List;
import java.util.Optional;

public interface PatientVitalSignsDAO {

    List<PatientVitalSigns> findAll();

    Optional<PatientVitalSigns> findById(Long id);

    Optional<PatientVitalSigns> findByPatientId(Long patientId);

    void create(PatientVitalSigns vitalSigns);

    void deleteById(Long id);
}
