package com.example.clinicaodontologica.service;

import com.example.clinicaodontologica.exceptions.BadRequestException;
import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.model.PacienteDTO;
import com.example.clinicaodontologica.repository.IPacienteRepository;
import com.example.clinicaodontologica.model.Paciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    ObjectMapper mapper;

    private Paciente guardarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente crearPaciente(PacienteDTO pacienteDTO) throws BadRequestException {
        if (pacienteDTO.getNombre() == null || pacienteDTO.getApellido() == null || pacienteDTO.getDni() == null || pacienteDTO.getEmail() == null || pacienteDTO.getFechaIngreso() == null || pacienteDTO.getDomicilio().getCalle() == null || pacienteDTO.getDomicilio().getNumero() == null || pacienteDTO.getDomicilio().getLocalidad() == null || pacienteDTO.getDomicilio().getProvincia() == null ) {
            throw new BadRequestException("Debe completar todos los datos del paciente para registrarlo correctamente en la base de datos");
        }
        return guardarPaciente(pacienteDTO);
    }

    @Override
    public PacienteDTO buscarPaciente(Long id) throws ResourceNotFoundException {

        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente == null) {
            throw new ResourceNotFoundException("No existe un paciente con el ID " + id);
        }
        return mapper.convertValue(paciente, PacienteDTO.class);
    }


    @Override
    public Paciente modificarPaciente(PacienteDTO pacienteDTO) throws ResourceNotFoundException{
        if(buscarPaciente(pacienteDTO.getId()) == null){
            throw new ResourceNotFoundException("No existe un paciente con el ID " + pacienteDTO.getId());
        }
        return guardarPaciente(pacienteDTO);
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if (buscarPaciente(id) == null) {
            throw new ResourceNotFoundException("No existe un paciente con el ID " + id);
        }
        pacienteRepository.deleteById(id);
    }

    @Override
    public Set<PacienteDTO> leerTodos() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        Set<PacienteDTO> pacientesDTO = new HashSet<>();

        for(Paciente paciente : pacientes) {
            pacientesDTO.add(mapper.convertValue(paciente, PacienteDTO.class));
        }
        return pacientesDTO;
    }
}
