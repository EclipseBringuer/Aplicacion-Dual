package com.cesur.aplicaciondual.domain.entities.alumno;

import com.cesur.aplicaciondual.domain.entities.actividad.Actividad;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import com.cesur.aplicaciondual.domain.entities.profesor.Profesor;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {
    @Id
    @Column(name = "dni")
    private String dni;
    @ManyToOne
    @JoinColumn(name = "id_tutor")
    private Profesor profesor;
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
    @OneToMany(mappedBy = "alumno",fetch = FetchType.EAGER)
    private List<Actividad> actividades;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "pass")
    private String pass;
    @Column(name = "fecha_nac")
    private String fecha_nac;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "num_horas_dual")
    private Integer dual;
    @Column(name = "num_horas_fct")
    private Integer fct;
    @Column(name = "observaciones")
    private String observaciones;

    public Alumno(){}

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getDual() {
        return dual;
    }

    public void setDual(Integer num_horas_dual) {
        this.dual = num_horas_dual;
    }

    public Integer getFct() {
        return fct;
    }

    public void setFct(Integer num_horas_fct) {
        this.fct = num_horas_fct;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "dni='" + dni + '\'' +
                ", tutor=" + profesor.getNombre() +
                ", empresa=" + empresa.getNombre() +
                ", actividades=" + actividades +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", pass='" + pass + '\'' +
                ", fecha_nac='" + fecha_nac + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", num_horas_dual=" + dual +
                ", num_horas_fct=" + fct +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }

    public void addActividad(Actividad a){
        a.setAlumno(this);
        actividades.add(a);
    }

    public void removeActividad(Actividad a){
        actividades.remove(a);
        a.setAlumno(null);
    }

}
