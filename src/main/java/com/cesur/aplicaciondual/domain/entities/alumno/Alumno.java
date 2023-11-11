package com.cesur.aplicaciondual.domain.entities.alumno;

import com.cesur.aplicaciondual.domain.entities.profesor.Profesor;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {
    @Id
    private String dni;
    @ManyToOne
    @JoinColumn(name = "id_tutor")
    private Profesor tutor;
}
