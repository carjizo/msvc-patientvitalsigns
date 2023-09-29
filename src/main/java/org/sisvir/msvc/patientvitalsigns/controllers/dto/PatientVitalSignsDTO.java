package org.sisvir.msvc.patientvitalsigns.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientVitalSignsDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private Integer heartRate;

    @NotNull
    @NotEmpty
    private Float temperature;

    @NotNull
    @NotEmpty
    private Long patientId;
}
