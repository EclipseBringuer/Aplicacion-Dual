package com.cesur.aplicaciondual.domain.entities.alumno;

import java.util.ArrayList;

public interface AlumnoDAO {
    public ArrayList<Alumno> getAll();
    public Alumno get(String dni);
    public Alumno save(Alumno data);
    public void update(Alumno data);
    public void delete(Alumno data);
}
