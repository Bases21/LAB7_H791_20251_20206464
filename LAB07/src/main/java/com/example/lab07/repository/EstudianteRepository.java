package com.example.lab07.repository;

import com.example.lab07.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByDni(String dni);
    boolean existsByDni(String dni);
    boolean existsByCodigoPucp(String codigoPucp);
}
