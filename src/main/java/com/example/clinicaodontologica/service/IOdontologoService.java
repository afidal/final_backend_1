package com.example.clinicaodontologica.service;
import com.example.clinicaodontologica.exceptions.BadRequestException;
import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.model.Odontologo;
import com.example.clinicaodontologica.model.OdontologoDTO;

import java.util.Set;

public interface IOdontologoService {
    public Odontologo crearOdontologo(OdontologoDTO odontologoDTO) throws BadRequestException;
    public OdontologoDTO buscarOdontologo(Long id) throws ResourceNotFoundException;
    public Odontologo modificarOdontologo(OdontologoDTO odontologoDTO) throws ResourceNotFoundException;
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException;
    Set<OdontologoDTO> leerTodos();
}
