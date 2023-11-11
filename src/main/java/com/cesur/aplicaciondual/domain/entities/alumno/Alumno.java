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
    private String dni;
    @ManyToOne
    @JoinColumn(name = "id_tutor")
    private Profesor tutor;
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
    @OneToMany(mappedBy = "alumno")
    private List<Actividad> actividades;
    private String nombre;
    private String apellidos;
    private String pass;
    private String fecha_nac;
    private String email;
    private String telefono;
    private Integer num_horas_dual;
    private Integer num_horas_fct;
    private String observaciones;

    public Alumno(){}

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Profesor getTutor() {
        return tutor;
    }

    public void setTutor(Profesor tutor) {
        this.tutor = tutor;
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

    public Integer getNum_horas_dual() {
        return num_horas_dual;
    }

    public void setNum_horas_dual(Integer num_horas_dual) {
        this.num_horas_dual = num_horas_dual;
    }

    public Integer getNum_horas_fct() {
        return num_horas_fct;
    }

    public void setNum_horas_fct(Integer num_horas_fct) {
        this.num_horas_fct = num_horas_fct;
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
                ", tutor=" + tutor.getNombre() +
                ", empresa=" + empresa.getNombre() +
                ", actividades=" + actividades +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", pass='" + pass + '\'' +
                ", fecha_nac='" + fecha_nac + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", num_horas_dual=" + num_horas_dual +
                ", num_horas_fct=" + num_horas_fct +
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
