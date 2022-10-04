package com.example.clinicaodontologica.controller;

import com.example.clinicaodontologica.exceptions.BadRequestException;
import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.model.Paciente;
import com.example.clinicaodontologica.model.PacienteDTO;
import com.example.clinicaodontologica.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    //private static final Logger logger = Logger.getLogger(PacienteController.class);

    @Autowired
    private PacienteService pacienteService;


    @PostMapping
    public ResponseEntity<Paciente> crearPaciente(@RequestBody PacienteDTO pacienteDTO) throws BadRequestException {
        return ResponseEntity.ok(pacienteService.crearPaciente(pacienteDTO));
    }

    @GetMapping("/{id}")
    public PacienteDTO buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return pacienteService.buscarPaciente(id);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Paciente> modificarPaciente(@RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundException{
       return ResponseEntity.ok(pacienteService.modificarPaciente(pacienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPorId(@PathVariable Long id) throws ResourceNotFoundException {
            pacienteService.eliminarPaciente(id);
           return ResponseEntity.ok("Se eliminó el paciente con el ID " + id);
    }

    @GetMapping
    public ResponseEntity<Set<PacienteDTO>> listarTodos() {
        return ResponseEntity.ok(pacienteService.leerTodos());
    }
}

