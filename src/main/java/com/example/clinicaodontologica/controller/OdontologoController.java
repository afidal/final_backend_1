package com.example.clinicaodontologica.controller;

import com.example.clinicaodontologica.exceptions.BadRequestException;
import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.model.Odontologo;
import com.example.clinicaodontologica.model.OdontologoDTO;
import com.example.clinicaodontologica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/odontologos")

public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping()
    public ResponseEntity<Set<OdontologoDTO>> listarTodos() {
        return ResponseEntity.ok(odontologoService.leerTodos());
    }

    @GetMapping("/{id}")
    public OdontologoDTO buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return odontologoService.buscarOdontologo(id);
    }

    @PostMapping()
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody OdontologoDTO odontologoDTO) throws BadRequestException {
        return ResponseEntity.ok(odontologoService.crearOdontologo(odontologoDTO));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Odontologo> modificarOdontologo(@RequestBody OdontologoDTO odontologoDTO) throws ResourceNotFoundException{
        return ResponseEntity.ok(odontologoService.modificarOdontologo(odontologoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPorId(@PathVariable long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Se eliminó el odontólogo con el ID " + id);

    }

}