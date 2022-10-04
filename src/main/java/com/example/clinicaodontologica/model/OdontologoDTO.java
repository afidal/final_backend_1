package com.example.clinicaodontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

public class OdontologoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    @Override
    public String toString() {
        return "Odontologo ID = " + id + ". Matricula: " + matricula + ". Nombre Completo: " + nombre + " " + apellido + ".";
    }
}