package com.example.lab07.controller;

import com.example.lab07.entity.Estudiante;
import com.example.lab07.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // GET /api/estudiantes
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listarEstudiantes() {
        List<Estudiante> lista = estudianteService.listarTodos();
        List<Map<String, Object>> respuesta = new ArrayList<>();

        for (Estudiante e : lista) {
            Map<String, Object> m = new HashMap<>();
            m.put("id", e.getId());
            m.put("nombres", e.getNombres());
            m.put("apellidos", e.getApellidos());
            m.put("dni", e.getDni());
            m.put("codigoPucp", e.getCodigoPucp());
            m.put("sexo", e.getSexo());
            m.put("correoInstitucional", e.getCorreoInstitucional());
            m.put("correoPersonal", e.getCorreoPersonal());
            m.put("telefono", e.getTelefono());
            m.put("direccion", e.getDireccion());
            m.put("departamento", e.getDepartamento());
            m.put("provincia", e.getProvincia());
            m.put("carrera", e.getCarrera());
            m.put("estado", e.getEstado() ? "Activo" : "Inactivo");
            respuesta.add(m);
        }

        return ResponseEntity.ok(respuesta);
    }

    // GET /api/estudiantes/{dni}
    @GetMapping("/{dni}")
    public ResponseEntity<Map<String, Object>> buscarPorDni(@PathVariable String dni) {
        Estudiante e = estudianteService.buscarPorDni(dni)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con DNI: " + dni));

        Map<String, Object> m = new HashMap<>();
        m.put("id", e.getId());
        m.put("nombres", e.getNombres());
        m.put("apellidos", e.getApellidos());
        m.put("dni", e.getDni());
        m.put("codigoPucp", e.getCodigoPucp());
        m.put("sexo", e.getSexo());
        m.put("correoInstitucional", e.getCorreoInstitucional());
        m.put("correoPersonal", e.getCorreoPersonal());
        m.put("telefono", e.getTelefono());
        m.put("direccion", e.getDireccion());
        m.put("departamento", e.getDepartamento());
        m.put("provincia", e.getProvincia());
        m.put("carrera", e.getCarrera());
        m.put("estado", e.getEstado() ? "Activo" : "Inactivo");

        return ResponseEntity.ok(m);
    }

    // POST /api/estudiantes
    @PostMapping
    public ResponseEntity<Estudiante> registrar(@Valid @RequestBody Estudiante estudiante) {
        Estudiante nuevo = estudianteService.registrar(estudiante);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    // PUT /api/estudiantes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        try {
            Estudiante actualizado = estudianteService.actualizarParcial(id, campos);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }


    // DELETE lógico /api/estudiantes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            estudianteService.eliminarLogico(id);
            return ResponseEntity.ok("Estudiante desactivado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

}
