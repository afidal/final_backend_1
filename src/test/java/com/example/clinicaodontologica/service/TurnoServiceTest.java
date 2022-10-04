package com.example.clinicaodontologica.service;

import com.example.clinicaodontologica.exceptions.BadRequestException;
import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.model.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class TurnoServiceTest {

    private static final Logger logger = Logger.getLogger(TurnoServiceTest.class);

    @Autowired
    IPacienteService pacienteService;
    @Autowired
    IOdontologoService odontologoService;
    @Autowired
    ITurnoService turnoService;

    public void cargarOdontologo() throws BadRequestException {
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("Ernesto");
        odontologoDTO.setApellido("Gimenez");
        odontologoDTO.setMatricula("MN66532");
        odontologoService.crearOdontologo(odontologoDTO);
    }

    public void cargarPaciente() throws BadRequestException {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNombre("Federica");
        pacienteDTO.setApellido("Ramos");
        pacienteDTO.setDni("29665111");
        pacienteDTO.setFechaIngreso(LocalDate.of(2022,10,10));
        pacienteDTO.setDomicilio(new Domicilio("La Pampa", "1134 7B", "CABA", "Buenos Aires"));
        pacienteDTO.setEmail("federamos@mail.com");
        pacienteService.crearPaciente(pacienteDTO);
    }

    @Test
    @Order(1)
    void crearTurno() throws ResourceNotFoundException, BadRequestException {
        cargarOdontologo();
        cargarPaciente();
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setFecha(LocalDate.of(2023,01,01));
        turnoDTO.setHora(new Time(18,00,00));
        OdontologoDTO odontologo = odontologoService.buscarOdontologo(1L);
        PacienteDTO paciente = pacienteService.buscarPaciente(1L);
        turnoDTO.setOdontologo(odontologo);
        turnoDTO.setPaciente(paciente);
        turnoService.crearTurno(turnoDTO);
        Assert.assertTrue(turnoService.buscarTurno(1L) != null);
        Assert.assertNotNull(turnoService.buscarTurno(1L));
    }

    @Test
    @Order(2)
    void buscarTurno() throws ResourceNotFoundException {
        TurnoDTO turno = turnoService.buscarTurno(1L);
        Assertions.assertNotNull(turno);
    }

    @Test
    @Order(3)
    void leerTodos() {
        Set<TurnoDTO> turnosDTO = turnoService.leerTodos();
        Assertions.assertFalse(turnosDTO.isEmpty());
        Assertions.assertTrue(turnosDTO.size() > 0);
        Assertions.assertTrue(turnosDTO.size() == 1);
        /*System.out.println("***Lista de Turnos***");
        for (TurnoDTO t : turnosDTO) {
            System.out.println(t.toString());
        }*/
    }

    @Test
    @Order(4)
    void eliminarTurno() throws ResourceNotFoundException {
        turnoService.eliminarTurno(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.buscarTurno(1L));
    }
}
