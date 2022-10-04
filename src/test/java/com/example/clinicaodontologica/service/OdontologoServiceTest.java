package com.example.clinicaodontologica.service;

import com.example.clinicaodontologica.exceptions.BadRequestException;
import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.model.Odontologo;
import com.example.clinicaodontologica.model.OdontologoDTO;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private IOdontologoService odontologoService;

    private static final Logger logger = Logger.getLogger(OdontologoServiceTest.class);

    @Test
    @Order(1)
    void crearOdontologo() throws ResourceNotFoundException, BadRequestException {
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("Micaela");
        odontologoDTO.setApellido("Santos");
        odontologoDTO.setMatricula("MN44729");
        Odontologo odontologo = odontologoService.crearOdontologo(odontologoDTO);
        Assert.assertTrue(odontologoService.buscarOdontologo(odontologo.getId()) != null);
    }

    @Test
    @Order(2)
    void buscarOdontologo() throws ResourceNotFoundException {
        OdontologoDTO odontologoDTO1 = odontologoService.buscarOdontologo(1L);
        Assert.assertTrue(odontologoDTO1 != null);
    }

    @Test
    @Order(3)
    void modificarOdontologo() throws ResourceNotFoundException, BadRequestException {
        OdontologoDTO odontologoDTO1 = odontologoService.buscarOdontologo(1L);
        odontologoDTO1.setNombre("Luisa");
        Assert.assertEquals(odontologoDTO1.getNombre(), "Luisa");
        odontologoService.crearOdontologo(odontologoDTO1);
    }



    @Test
    @Order(4)
    void leerTodos() {
        Set<OdontologoDTO> odontologosDTO = odontologoService.leerTodos();
        Assertions.assertFalse(odontologosDTO.isEmpty());
        Assertions.assertTrue(odontologosDTO.size() == 1);
        Assertions.assertNotEquals(0, odontologoService.leerTodos().size());
//        System.out.println("***Lista de Odontólogos***");
//        for (OdontologoDTO o : odontologosDTO) {
//            System.out.println(o.toString());
//        }

    }

    @Test
    @Order(5)
    void eliminarOdontologo() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.buscarOdontologo(1L));

    }
}