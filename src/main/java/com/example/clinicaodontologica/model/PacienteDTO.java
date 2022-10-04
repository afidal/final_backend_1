package com.example.clinicaodontologica.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class PacienteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaIngreso;
    private Domicilio domicilio;
    private String email;

    @Override
    public String toString() {
        return "Paciente ID = " + id + ". Nombre completo: " + nombre + " " +  apellido + ". DNI: " + dni + ". Email: " + email + ". Domicilio: " + domicilio;
    }
}

