package com.cesur.aplicaciondual.domain.entities.actividad;

import java.util.ArrayList;

public interface ActividadDAO {
    public ArrayList<Actividad> getAll();
    public Actividad get(Integer id);
    public Actividad save(Actividad data);
    public void update(Actividad data);
    public void delete(Actividad data);
}
