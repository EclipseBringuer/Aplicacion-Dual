package com.cesur.aplicaciondual.domain.entities.actividad;

import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "actividad")
public class Actividad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_actividad;
    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;
    private String fecha;
    private String tipo_practica;
    private Integer horas_realizadas;
    private String actividad_realizada;
    private String observaciones;

    public Actividad(){}

    public Integer getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(Integer id_actividad) {
        this.id_actividad = id_actividad;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo_practica() {
        return tipo_practica;
    }

    public void setTipo_practica(String tipo_practica) {
        this.tipo_practica = tipo_practica;
    }

    public Integer getHoras_realizadas() {
        return horas_realizadas;
    }

    public void setHoras_realizadas(Integer horas_realizadas) {
        this.horas_realizadas = horas_realizadas;
    }

    public String getActividad_realizada() {
        return actividad_realizada;
    }

    public void setActividad_realizada(String actividad_realizada) {
        this.actividad_realizada = actividad_realizada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


    @Override
    public String toString() {
        return "Actividad{" +
                "id_actividad=" + id_actividad +
                ", alumno=" + alumno.getNombre() +
                ", fecha='" + fecha + '\'' +
                ", tipo_practica='" + tipo_practica + '\'' +
                ", horas_realizadas=" + horas_realizadas +
                ", actividad_realizada='" + actividad_realizada + '\'' +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }



    public static void merge(Actividad origen, Actividad destino) {
        destino.setAlumno(origen.getAlumno());
        destino.setFecha(origen.getFecha());
        destino.setId_actividad(origen.getId_actividad());
        destino.setObservaciones(origen.getObservaciones());
        destino.setTipo_practica(origen.getTipo_practica());
        destino.setActividad_realizada(origen.getActividad_realizada());
        destino.setHoras_realizadas(origen.getHoras_realizadas());
    }
}
