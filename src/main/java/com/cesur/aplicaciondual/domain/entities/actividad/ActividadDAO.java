package com.cesur.aplicaciondual.domain.entities.actividad;

import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;

import java.util.ArrayList;

public interface ActividadDAO {
    public ArrayList<Actividad> getAll(Alumno alum);
    public Actividad get(Integer id);
    public Actividad save(Actividad data);
    public void update(Actividad data);
    public void delete(Actividad data);
}
