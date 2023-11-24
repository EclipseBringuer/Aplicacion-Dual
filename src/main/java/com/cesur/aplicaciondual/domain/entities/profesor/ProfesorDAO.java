package com.cesur.aplicaciondual.domain.entities.profesor;

import java.util.ArrayList;

public interface ProfesorDAO {
    public ArrayList<Profesor> getAll();
    public Profesor get(Integer id);
    public Profesor save(Profesor data);
    public void update(Profesor data);
    public void delete(Profesor data);
}
