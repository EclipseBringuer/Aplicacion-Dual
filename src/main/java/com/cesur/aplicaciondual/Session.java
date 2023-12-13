package com.cesur.aplicaciondual;

import com.cesur.aplicaciondual.domain.entities.actividad.Actividad;
import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import com.cesur.aplicaciondual.domain.entities.profesor.Profesor;

public class Session {

    private static Profesor profesor;
    private static Alumno alumno;
    private static Actividad actividad;
    private static Empresa empresa;


    public static Profesor getProfesor() {
        return profesor;
    }

    public static void setProfesor(Profesor profesor) {
        Session.profesor = profesor;
    }

    public static Alumno getAlumno() {
        return alumno;
    }

    public static void setAlumno(Alumno alumno) {
        Session.alumno = alumno;
    }

    public static Actividad getActividad() {
        return actividad;
    }

    public static void setActividad(Actividad actividad) {
        Session.actividad = actividad;
    }

    public static Empresa getEmpresa() {
        return empresa;
    }

    public static void setEmpresa(Empresa empresa) {
        Session.empresa = empresa;
    }


}
