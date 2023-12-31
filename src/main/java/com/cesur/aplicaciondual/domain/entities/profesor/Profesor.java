package com.cesur.aplicaciondual.domain.entities.profesor;

import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "profesor")
public class Profesor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellidos;
    private String email;
    private String pass;
    private String imagen;

    @OneToMany(mappedBy = "profesor", fetch = FetchType.EAGER)
    private List<Alumno> alumnos;

    public Profesor() {
    }

    public static void merge(Profesor vieja, Profesor nueva) {
        vieja.setAlumnos(nueva.getAlumnos());
        vieja.setApellidos(nueva.getApellidos());
        vieja.setEmail(nueva.getEmail());
        vieja.setNombre(nueva.getNombre());
        vieja.setPass(nueva.getPass());
        vieja.setId(nueva.getId());
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", alumnos=" + alumnos +
                '}';
    }

    public void addAlumno(Alumno a) {
        a.setProfesor(this);
        alumnos.add(a);
    }

    public void removeAlumno(Alumno a) {
        alumnos.remove(a);
        a.setProfesor(null);
    }

}
