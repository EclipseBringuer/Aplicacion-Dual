package com.cesur.aplicaciondual.domain.entities.empresa;

import java.util.List;

public interface EmpresaDAO {
    public List<Empresa> getAll();
    public Empresa get(Integer id);
    public Empresa save(Empresa data);
    public void update(Empresa data);
    public void delete(Empresa data);
}
