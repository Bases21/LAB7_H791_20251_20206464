package com.example.lab07.service;

import com.example.lab07.entity.Estudiante;
import com.example.lab07.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> buscarPorDni(String dni) {
        return estudianteRepository.findByDni(dni);
    }

    public Estudiante registrar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Optional<Estudiante> obtenerPorId(Long id) {
        return estudianteRepository.findById(id);
    }

    public void eliminarLogico(Long id) {
        Estudiante est = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        est.setEstado(false);
        estudianteRepository.save(est);
    }


    public Estudiante actualizar(Long id, Estudiante nuevo) {
        return estudianteRepository.findById(id).map(est -> {
            if (nuevo.getNombres() != null) est.setNombres(nuevo.getNombres());
            if (nuevo.getApellidos() != null) est.setApellidos(nuevo.getApellidos());
            if (nuevo.getCorreoPersonal() != null) est.setCorreoPersonal(nuevo.getCorreoPersonal());
            if (nuevo.getTelefono() != null) est.setTelefono(nuevo.getTelefono());
            if (nuevo.getDireccion() != null) est.setDireccion(nuevo.getDireccion());
            if (nuevo.getCarrera() != null) est.setCarrera(nuevo.getCarrera());
            return estudianteRepository.save(est);
        }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }
    public Estudiante actualizarParcial(Long id, Map<String, Object> campos) {
        Estudiante est = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        if (campos.containsKey("telefono")) {
            est.setTelefono(campos.get("telefono").toString());
        }

        if (campos.containsKey("direccion")) {
            est.setDireccion(campos.get("direccion").toString());
        }

        if (campos.containsKey("correoPersonal")) {
            est.setCorreoPersonal(campos.get("correoPersonal").toString());
        }

        if (campos.containsKey("carrera")) {
            est.setCarrera(campos.get("carrera").toString());
        }

        if (campos.containsKey("estado") || campos.containsKey("fechaRegistro") || campos.containsKey("ultimaActualizacion")) {
            throw new IllegalArgumentException("No se puede modificar estado, fechaRegistro ni ultimaActualizacion");
        }

        return estudianteRepository.save(est);
    }


}
