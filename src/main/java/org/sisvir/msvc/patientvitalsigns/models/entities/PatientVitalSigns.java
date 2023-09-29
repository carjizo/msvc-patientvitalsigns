package org.sisvir.msvc.patientvitalsigns.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.time.ZoneId;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient_vital_signs")
public class PatientVitalSigns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "heart_rate")
    private Integer heartRate;

    @NotNull
    private Float temperature;

    @NotNull
    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        ZoneId zoneId = ZoneId.of("America/Lima"); // Zona horaria de Perú
        createdAt = OffsetDateTime.now(zoneId);
        updatedAt = OffsetDateTime.now(zoneId);
    }

    @PreUpdate
    protected void onUpdate() {
        ZoneId zoneId = ZoneId.of("America/Lima"); // Zona horaria de Perú
        updatedAt = OffsetDateTime.now(zoneId);
    }
}
