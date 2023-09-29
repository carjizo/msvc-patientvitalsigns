package org.sisvir.msvc.patientvitalsigns.repositories;

import org.sisvir.msvc.patientvitalsigns.models.entities.PatientVitalSigns;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientVitalSignsRepository extends CrudRepository<PatientVitalSigns, Long> {

    Optional<PatientVitalSigns> findByPatientId(Long patientId);
}
