package com.example.clinicaodontologica.controller;

import com.example.clinicaodontologica.exceptions.BadRequestException;
import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.model.Turno;
import com.example.clinicaodontologica.model.TurnoDTO;
import com.example.clinicaodontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    //private static final Logger logger = Logger.getLogger(TurnoController.class);

    @Autowired
    private TurnoService turnoService;

    @PostMapping
    public ResponseEntity<?> crearTurno(@RequestBody TurnoDTO turnoDTO) throws BadRequestException {
        turnoService.crearTurno(turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public TurnoDTO buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return turnoService.buscarTurno(id);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Turno> modificarTurno(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(turnoService.modificarTurno(turnoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPorId(@PathVariable Long id) throws ResourceNotFoundException, BadRequestException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Se eliminó el turno con el ID " + id);
    }

    @GetMapping
    public ResponseEntity<Set<TurnoDTO>> listarTodos() {
        return ResponseEntity.ok(turnoService.leerTodos());
    }

}
