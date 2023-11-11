package com.cesur.aplicaciondual.domain.entities.empresa;

import java.util.ArrayList;

public interface EmpresaDAO {
    public ArrayList<Empresa> getAll();
    public Empresa get(Long id);
    public Empresa save(Empresa data);
    public void update(Empresa data);
    public void delete(Empresa data);
}
