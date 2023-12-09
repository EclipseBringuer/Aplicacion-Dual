package com.cesur.aplicaciondual.domain.entities.actividad;

import com.cesur.aplicaciondual.domain.HibernateUtil;
import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;
import com.cesur.aplicaciondual.domain.entities.alumno.AlumnoDAOImp;
import com.cesur.aplicaciondual.domain.entities.profesor.Profesor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class ActividadDAOImp implements ActividadDAO{

    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ActividadDAOImp.class);
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

        Actividad salida = null;

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();

            s.persist(data);

            salida = data;

            t.commit();

            LOG.info("Guardado correctamente: " + data);

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        return salida;
    }

    @Override
    public void update(Actividad nueva) {

        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {

            Transaction t = s.beginTransaction();

            Actividad vieja = s.get(Actividad.class, nueva.getId_actividad());

            Actividad.merge(nueva, vieja);

            t.commit();

        }



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
