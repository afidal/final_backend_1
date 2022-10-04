package com.example.clinicaodontologica.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
@ToString

@Entity
@Table(name="turnos")
public class Turno {

    @Id
    @SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    private Long idTurno;
    private LocalDate fecha;
    private Time hora;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "paciente_id",referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
    private Paciente paciente;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "odontologo_id",referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
    private Odontologo odontologo;

    public Turno() {
    }

    public Turno(Long idTurno, LocalDate fecha, Time hora, Paciente paciente, Odontologo odontologo) {
        this.idTurno = idTurno;
        this.fecha = fecha;
        this.hora = hora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public Turno(LocalDate fecha, Time hora, Paciente paciente, Odontologo odontologo) {
        this.fecha = fecha;
        this.hora = hora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

}
