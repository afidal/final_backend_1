package com.example.clinicaodontologica.service;
import com.example.clinicaodontologica.exceptions.BadRequestException;
import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.model.Turno;
import com.example.clinicaodontologica.model.TurnoDTO;

import java.util.Set;

public interface ITurnoService {
    public Turno crearTurno(TurnoDTO turnoDTO) throws BadRequestException;
    public TurnoDTO buscarTurno(Long id) throws ResourceNotFoundException;
    public Turno modificarTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException;
    public void eliminarTurno(Long id) throws ResourceNotFoundException;
    Set<TurnoDTO> leerTodos();
}
