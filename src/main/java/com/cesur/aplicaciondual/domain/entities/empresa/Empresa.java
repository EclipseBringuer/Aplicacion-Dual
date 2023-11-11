package com.cesur.aplicaciondual.domain.entities.empresa;

import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "empresa")
    private List<Alumno> alumnos;
    private String telefono;
    private String email;
    private String responsable;
    private String observaciones;
    private String nombre;

    public Empresa(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", alumnos=" + alumnos +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", responsable='" + responsable + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public void addAlumno(Alumno a){
        a.setEmpresa(this);
        alumnos.add(a);
    }

    public void removeAlumno(Alumno a){
        alumnos.remove(a);
        a.setEmpresa(null);
    }
}
