package com.cesur.aplicaciondual.domain.entities.actividad;

import com.cesur.aplicaciondual.domain.HibernateUtil;
import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;
import com.cesur.aplicaciondual.domain.entities.alumno.AlumnoDAOImp;
import com.cesur.aplicaciondual.domain.entities.profesor.Profesor;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class ActividadDAOImp implements ActividadDAO{

    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ActividadDAOImp.class);

    /**
     * Metodo para traerte todas las tareas de un Alumno
     * @param alumno Alumno del que queremos las tareas
     * @return lista con las tareas del alumno
     * @author samu_
     */
    @Override
    public ArrayList<Actividad> getAll(Alumno alumno) {

        ArrayList<Actividad> lista;

        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {

            Transaction t = s.beginTransaction();

            Query<Actividad> q= s.createQuery("from Actividad where alumno=:alum", Actividad.class);

            q.setParameter("alum",alumno);

            lista = (ArrayList<Actividad>) q.getResultList();

            t.commit();

        }


        return lista;
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
