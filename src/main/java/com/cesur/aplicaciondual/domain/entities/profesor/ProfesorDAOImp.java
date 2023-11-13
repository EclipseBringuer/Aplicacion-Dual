package com.cesur.aplicaciondual.domain.entities.profesor;

import com.cesur.aplicaciondual.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class ProfesorDAOImp implements ProfesorDAO{
    @Override
    public ArrayList<Profesor> getAll() {
        var salida = new ArrayList<Profesor>();
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Profesor> q = s.createQuery("from Profesor ", Profesor.class);
            salida = (ArrayList<Profesor>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Profesor get(Long id) {
        return null;
    }

    @Override
    public Profesor save(Profesor data) {
        return null;
    }

    @Override
    public void update(Profesor data) {

    }

    @Override
    public void delete(Profesor data) {

    }
}
