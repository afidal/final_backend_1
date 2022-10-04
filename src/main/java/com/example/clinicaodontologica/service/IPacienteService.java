package com.example.clinicaodontologica.service;

import com.example.clinicaodontologica.exceptions.BadRequestException;
import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.model.Paciente;
import com.example.clinicaodontologica.model.PacienteDTO;

import java.util.Set;

public interface IPacienteService {
    public Paciente crearPaciente(PacienteDTO pacienteDTO) throws BadRequestException;
    public PacienteDTO buscarPaciente(Long id) throws ResourceNotFoundException;
    public Paciente modificarPaciente(PacienteDTO pacienteDTO) throws ResourceNotFoundException;
    public void eliminarPaciente(Long id) throws ResourceNotFoundException;
    Set<PacienteDTO> leerTodos();
}
