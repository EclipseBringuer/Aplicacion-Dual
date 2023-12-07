package com.cesur.aplicaciondual.domain.entities.actividad;

import com.cesur.aplicaciondual.domain.HibernateUtil;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class ActividadDAOImp implements ActividadDAO{
    @Override
    public ArrayList<Actividad> getAll() {
        return null;
    }

    @Override
    public Actividad get(Integer id) {
        return null;
    }

    @Override
    public Actividad save(Actividad data) {
        return null;
    }

    @Override
    public void update(Actividad data) {

    }

    @Override
    public void delete(Actividad data) {

        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {

            Transaction t = s.beginTransaction();

            s.remove(data);

            t.commit();

        }

    }
}
