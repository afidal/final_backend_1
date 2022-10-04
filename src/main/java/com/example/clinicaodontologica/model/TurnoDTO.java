package com.example.clinicaodontologica.model;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter

public class TurnoDTO {

    private Long idTurno;
    private LocalDate fecha;
    private Time hora;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;

    @Override
    public String toString() {
        return "Turno con ID" + idTurno + ". Fecha: " + fecha + ". Hora: " + hora + ". " + paciente + ". " + odontologo;
    }
}
