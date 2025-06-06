package com.example.lab07.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String nombres;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String apellidos;

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    @Column(unique = true)
    private String dni;

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    @Column(unique = true)
    private String codigoPucp;

    @Past
    @NotNull
    private LocalDate fechaNacimiento;

    @NotBlank
    @Pattern(regexp = "^[MF]$")
    private String sexo;

    @Email
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@pucp\\.edu\\.pe$")
    private String correoInstitucional;

    @Email
    private String correoPersonal;

    @Pattern(regexp = "^9\\d{8}$")
    private String telefono;

    @Size(max = 100)
    private String direccion;

    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String departamento;

    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String provincia;

    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String carrera;

    private LocalDateTime fechaRegistro;
    private LocalDateTime ultimaActualizacion;
    private Boolean estado = true;

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
        this.ultimaActualizacion = null;
        this.estado = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.ultimaActualizacion = LocalDateTime.now();
    }
}
