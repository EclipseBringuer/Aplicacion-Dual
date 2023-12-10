package com.cesur.aplicaciondual.domain.entities.alumno;

import com.cesur.aplicaciondual.domain.entities.actividad.Actividad;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import com.cesur.aplicaciondual.domain.entities.profesor.Profesor;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa la entidad Alumno en la base de datos.
 */
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
    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<Actividad> actividades;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "pass")
    private String pass;
    @Column(name = "fecha_nac")
    private Date fecha_nac;
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

    /**
     * Constructor predeterminado de la clase Alumno.
     */
    public Alumno() {
    }

    /**
     * Obtiene el DNI del alumno.
     *
     * @return El DNI del alumno.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del alumno.
     *
     * @param dni El nuevo DNI del alumno.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el profesor tutor del alumno.
     *
     * @return El profesor tutor del alumno.
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     * Establece el profesor tutor del alumno.
     *
     * @param profesor El nuevo profesor tutor del alumno.
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    /**
     * Obtiene la empresa del alumno.
     *
     * @return La empresa del alumno.
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * Establece la empresa del alumno.
     *
     * @param empresa La nueva empresa del alumno.
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * Obtiene la lista de actividades del alumno.
     *
     * @return La lista de actividades del alumno.
     */
    public List<Actividad> getActividades() {
        return actividades;
    }

    /**
     * Establece la lista de actividades del alumno.
     *
     * @param actividades La nueva lista de actividades del alumno.
     */
    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    /**
     * Obtiene el nombre del alumno.
     *
     * @return El nombre del alumno.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del alumno.
     *
     * @param nombre El nuevo nombre del alumno.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del alumno.
     *
     * @return Los apellidos del alumno.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del alumno.
     *
     * @param apellidos Los nuevos apellidos del alumno.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene la contraseña del alumno.
     *
     * @return La contraseña del alumno.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Establece la contraseña del alumno.
     *
     * @param pass La nueva contraseña del alumno.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Obtiene la fecha de nacimiento del alumno.
     *
     * @return La fecha de nacimiento del alumno.
     */
    public Date getFecha_nac() {
        return fecha_nac;
    }

    /**
     * Establece la fecha de nacimiento del alumno.
     *
     * @param fecha_nac La nueva fecha de nacimiento del alumno.
     */
    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    /**
     * Obtiene la dirección de correo electrónico del alumno.
     *
     * @return La dirección de correo electrónico del alumno.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece la dirección de correo electrónico del alumno.
     *
     * @param email La nueva dirección de correo electrónico del alumno.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el número de teléfono del alumno.
     *
     * @return El número de teléfono del alumno.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del alumno.
     *
     * @param telefono El nuevo número de teléfono del alumno.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el número de horas de la modalidad dual del alumno.
     *
     * @return El número de horas de la modalidad dual del alumno.
     */
    public Integer getDual() {
        return dual;
    }

    /**
     * Establece el número de horas de la modalidad dual del alumno.
     *
     * @param num_horas_dual El nuevo número de horas de la modalidad dual del alumno.
     */
    public void setDual(Integer num_horas_dual) {
        this.dual = num_horas_dual;
    }

    /**
     * Obtiene el número de horas de la FCT del alumno.
     *
     * @return El número de horas de la FCT del alumno.
     */
    public Integer getFct() {
        return fct;
    }

    /**
     * Establece el número de horas de la FCT del alumno.
     *
     * @param num_horas_fct El nuevo número de horas de la FCT del alumno.
     */
    public void setFct(Integer num_horas_fct) {
        this.fct = num_horas_fct;
    }

    /**
     * Obtiene las observaciones relacionadas con el alumno.
     *
     * @return Las observaciones relacionadas con el alumno.
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece las observaciones relacionadas con el alumno.
     *
     * @param observaciones Las nuevas observaciones relacionadas con el alumno.
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Representación de cadena del objeto Alumno.
     *
     * @return La representación de cadena del objeto Alumno.
     */
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

    /**
     * Agrega una actividad a la lista de actividades del alumno.
     *
     * @param a La actividad a agregar.
     */
    public void addActividad(Actividad a) {
        a.setAlumno(this);
        actividades.add(a);
    }

    /**
     * Elimina una actividad de la lista de actividades del alumno.
     *
     * @param a La actividad a eliminar.
     */
    public void removeActividad(Actividad a) {
        actividades.remove(a);
        a.setAlumno(null);
    }

    /**
     * Método estático que fusiona los datos de un alumno de origen en un alumno de destino.
     *
     * @param origen  El alumno de origen.
     * @param destino El alumno de destino.
     */
    public static void merge(Alumno origen, Alumno destino) {
        destino.setProfesor(origen.getProfesor());
        destino.setEmpresa(origen.getEmpresa());
        destino.setNombre(origen.getNombre());
        destino.setObservaciones(origen.getObservaciones());
        destino.setDni(origen.getDni());
        destino.setApellidos(origen.getApellidos());
        destino.setDual(origen.getDual());
        destino.setFct(origen.getFct());
        destino.setEmail(origen.getEmail());
        destino.setFecha_nac(origen.getFecha_nac());
        destino.setPass(origen.getPass());
        destino.setTelefono(origen.getTelefono());
    }
}
