package org.sisvir.msvc.patientvitalsigns.controllers;


import org.sisvir.msvc.patientvitalsigns.controllers.dto.PatientVitalSignsDTO;
import org.sisvir.msvc.patientvitalsigns.models.entities.PatientVitalSigns;
import org.sisvir.msvc.patientvitalsigns.services.PatientVitalSignsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/patient-vital-signs")
public class PatientVitalSignsController {

    @Autowired
    private PatientVitalSignsService vitalSignsService;

    // SI ESTÁ IMPLEMENTADO
    @GetMapping("/getAll")
    public ResponseEntity<?> listar() {

        List<PatientVitalSignsDTO> vitalSignsDTOList = vitalSignsService.findAll()
                .stream()
                .map(vitalSigns -> PatientVitalSignsDTO.builder()
                        .id(vitalSigns.getId())
                        .heartRate(vitalSigns.getHeartRate())
                        .temperature(vitalSigns.getTemperature())
                        .patientId(vitalSigns.getPatientId())
                        .build())
                .toList();
        return ResponseEntity.ok(vitalSignsDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailById(@PathVariable Long id) {

        Optional<PatientVitalSigns> vitalSignsOptional = vitalSignsService.findById(id);
        if (vitalSignsOptional.isPresent()) {
            PatientVitalSigns vitalSigns = vitalSignsOptional.get();
            PatientVitalSignsDTO vitalSignsDTO = PatientVitalSignsDTO.builder()
                    .id(vitalSigns.getId())
                    .heartRate(vitalSigns.getHeartRate())
                    .temperature(vitalSigns.getTemperature())
                    .patientId(vitalSigns.getPatientId())
                    .build();
            return ResponseEntity.ok(vitalSignsDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> crear(@Valid @RequestBody PatientVitalSignsDTO vitalSignsDTO, BindingResult result) throws URISyntaxException {

        if (vitalSignsDTO.getHeartRate() == null) {
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("mensaje", "El campo heartRate no debe estar vacío!"));
        }

        if (vitalSignsDTO.getTemperature() == null) {
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("mensaje", "El campo temperature no debe estar vacío!"));
        }

        if (vitalSignsDTO.getPatientId() == null) {
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("mensaje", "El campo patientId no debe estar vacío!"));
        }

        if (result.hasErrors()) {
            return validate(result);
        }

        vitalSignsService.create(PatientVitalSigns.builder()
                .heartRate(vitalSignsDTO.getHeartRate())
                .temperature(vitalSignsDTO.getTemperature())
                .patientId(vitalSignsDTO.getPatientId())
                .build());
        return ResponseEntity.created(new URI("/patient-vital-signs/create")).build();
    }

    // SI ESTÁ IMPLEMENTADO
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@Valid @RequestBody PatientVitalSignsDTO vitalSignsDTO, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validate(result);
        }


        Optional<PatientVitalSigns> vitalSignsOptional = vitalSignsService.findById(id);
        if (vitalSignsOptional.isPresent()) {
            PatientVitalSigns vitalSigns = vitalSignsOptional.get();
            vitalSigns.setHeartRate(vitalSignsDTO.getHeartRate());
            vitalSigns.setTemperature(vitalSignsDTO.getTemperature());
            vitalSigns.setPatientId(vitalSignsDTO.getPatientId());
            vitalSignsService.create(vitalSigns);
            return ResponseEntity.ok("Registro Actualizado");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Optional<PatientVitalSigns> vitalSignsOptional = vitalSignsService.findById(id);
        if (vitalSignsOptional.isPresent()) {
            vitalSignsService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
