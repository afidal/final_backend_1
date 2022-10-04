package com.example.clinicaodontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString

@Entity
@Table(name = "odontologos")
public class Odontologo {

    @Id
    @SequenceGenerator(name = "odontologo_sequence", sequenceName = "odontologo_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;
    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Odontologo() {
    }

    public Odontologo(Long id, String nombre, String apellido, String matricula) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
    }

}
