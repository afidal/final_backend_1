package com.example.clinicaodontologica.service;

import com.example.clinicaodontologica.exceptions.BadRequestException;
import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.model.Odontologo;
import com.example.clinicaodontologica.model.OdontologoDTO;
import com.example.clinicaodontologica.repository.IOdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService implements IOdontologoService {

    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

   private Odontologo guardarOdontologo(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo crearOdontologo(OdontologoDTO odontologoDTO) throws BadRequestException {
       if (odontologoDTO.getNombre() == null || odontologoDTO.getApellido() == null || odontologoDTO.getMatricula() == null) {
           throw new BadRequestException("Debe completar todos los datos del odontólogo");
       }
       return guardarOdontologo(odontologoDTO);
    }

    @Override

    public OdontologoDTO buscarOdontologo(Long id) throws ResourceNotFoundException{
        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);
        if(odontologo == null) {
            throw new ResourceNotFoundException("No existe un odontólogo con el ID " + id);
        }
        return mapper.convertValue(odontologo, OdontologoDTO.class);
    }


    @Override
    public Odontologo modificarOdontologo(OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
       if (buscarOdontologo(odontologoDTO.getId()) == null){
           throw new ResourceNotFoundException("No existe un odontólogo con el ID " + odontologoDTO.getId());
       }
        return guardarOdontologo(odontologoDTO);
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (buscarOdontologo(id) == null) {
            throw new ResourceNotFoundException("No existe un odontólogo con el ID " + id);
        }
        odontologoRepository.deleteById(id);
    }

    @Override
    public Set<OdontologoDTO> leerTodos() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        Set<OdontologoDTO> odontologosDTO = new HashSet<>();
        for (Odontologo odontologo : odontologos) {
            odontologosDTO.add(mapper.convertValue(odontologo, OdontologoDTO.class));
        }
        return odontologosDTO;
    }
}
