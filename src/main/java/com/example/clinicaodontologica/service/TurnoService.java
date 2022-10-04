
package com.example.clinicaodontologica.service;

import com.example.clinicaodontologica.exceptions.BadRequestException;
import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.model.*;
import com.example.clinicaodontologica.repository.IOdontologoRepository;
import com.example.clinicaodontologica.repository.IPacienteRepository;
import com.example.clinicaodontologica.repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TurnoService implements ITurnoService {

    @Autowired
    private ITurnoRepository turnoRepository;

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    private Turno guardarTurno(TurnoDTO turnoDTO)  {
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        return turnoRepository.save(turno);
    }

    @Override
    public Turno crearTurno(TurnoDTO turnoDTO) throws BadRequestException {
        Optional<Paciente> paciente = pacienteRepository.findById(turnoDTO.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turnoDTO.getOdontologo().getId());
        if (paciente.isEmpty() || odontologo.isEmpty()) {
            throw new BadRequestException("El paciente y el odontológo que se asignan a un turno deben estar previamente registrados en la base de datos.");
        }
        return guardarTurno(turnoDTO);
    }

    @Override
    public TurnoDTO buscarTurno(Long id) throws ResourceNotFoundException {
        Turno turno = turnoRepository.findById(id).orElse(null);
        if(turno == null) {
            throw new ResourceNotFoundException("No existe un turno con el ID " + id);
        }
        return mapper.convertValue(turno, TurnoDTO.class);
    }

    @Override
    public Turno modificarTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException{
        if (buscarTurno(turnoDTO.getIdTurno()) == null) {
            throw new ResourceNotFoundException("No existe un turno con el ID " + turnoDTO.getIdTurno());
        }

        return guardarTurno(turnoDTO);
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if(buscarTurno(id) == null) {
            throw new ResourceNotFoundException("No existe un turno con el ID " + id);
        }
        turnoRepository.deleteById(id);
    }

    @Override
    public Set<TurnoDTO> leerTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        Set<TurnoDTO> turnosDTO = new HashSet<>();

        for (Turno turno : turnos) {
            turnosDTO.add(mapper.convertValue(turno, TurnoDTO.class));
        }
        return turnosDTO;
    }
}
