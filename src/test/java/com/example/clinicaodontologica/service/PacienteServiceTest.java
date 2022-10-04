package com.example.clinicaodontologica.service;

import com.example.clinicaodontologica.exceptions.BadRequestException;
import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.model.Domicilio;
import com.example.clinicaodontologica.model.PacienteDTO;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private IPacienteService pacienteService;

    private static final Logger logger = Logger.getLogger(PacienteServiceTest.class);

    @Test
    @Order(1)
    void crearPaciente() throws ResourceNotFoundException, BadRequestException {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNombre("Marcos");
        pacienteDTO.setApellido("Luna");
        pacienteDTO.setDni("30777221");
        pacienteDTO.setFechaIngreso(LocalDate.of(2022,01,01));
        pacienteDTO.setDomicilio(new Domicilio("Besares", "2864 3F", "CABA", "Buenos Aires"));
        pacienteDTO.setEmail("marcosluna@mail.com");
        pacienteService.crearPaciente(pacienteDTO);
        Assert.assertTrue(pacienteService.buscarPaciente(1L) != null);
        Assert.assertNotNull(pacienteService.buscarPaciente(1L));
    }

    @Test
    @Order(2)
    void buscarPaciente() throws ResourceNotFoundException {
        PacienteDTO pacienteDTO = pacienteService.buscarPaciente(1L);
        Assert.assertTrue(pacienteDTO != null);
        Assert.assertNotNull(pacienteDTO);
    }

    @Test
    @Order(3)
    void modificarPaciente() throws ResourceNotFoundException, BadRequestException {
        PacienteDTO pacienteDTO1 = pacienteService.buscarPaciente(1L);
        pacienteDTO1.setApellido("Rocco");
        pacienteService.crearPaciente(pacienteDTO1);
        Assert.assertEquals(pacienteDTO1.getApellido(), "Rocco");
    }

    @Test
    @Order(4)
    void leerTodos() {
        Set<PacienteDTO> pacientesDTO = pacienteService.leerTodos();
        Assertions.assertFalse(pacientesDTO.isEmpty());
        Assert.assertTrue(pacientesDTO.size() > 0);
        Assert.assertTrue(pacientesDTO.size() == 1);
       /*System.out.println("***Lista de Pacientes***");
        for (PacienteDTO p : pacientesDTO) {
            System.out.println(p.toString());
        }*/
    }

    @Test
    @Order(5)
    void eliminarPaciente() throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> pacienteService.buscarPaciente(1L));
    }


}