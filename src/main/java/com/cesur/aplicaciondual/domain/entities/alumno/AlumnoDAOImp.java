package com.cesur.aplicaciondual.domain.entities.alumno;

import com.cesur.aplicaciondual.domain.HibernateUtil;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import com.cesur.aplicaciondual.domain.entities.profesor.Profesor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;

public class AlumnoDAOImp implements AlumnoDAO{

    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AlumnoDAOImp.class);

    @Override
    public ArrayList<Alumno> getAll() {
        return null;
    }

    /**
     * Metodo para traer un alumno de la base de datos
     * @param dni dni del alumno
     * @return el alumno de la base de datos
     * @author samu_
     */
    @Override
    public Alumno get(String dni) {
        Alumno u = null;

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            Query<Alumno> q = s.createQuery("FROM Alumno where dni=:n", Alumno.class);

            q.setParameter("n", dni);

            try {

                u = q.getSingleResult();

                LOG.info("Alumno traido correctamente");

            } catch (Exception e) {

                LOG.error(e.getMessage());

            }

        } catch (Exception e) {

            LOG.error(e.getMessage());

        }

        return u;
    }

    @Override
    public Alumno save(Alumno data) {
        Alumno salida = null;

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
    public void update(Alumno data) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            Transaction t = s.beginTransaction();

            Alumno vieja = s.get(Alumno.class, data.getDni());

            Alumno.merge(vieja, data);

            t.commit();

        }
    }

    @Override
    public void delete(Alumno data) {

    }

    public Alumno getByAccount(String email, String pass) {
        Alumno salida = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Alumno> q = s.createQuery("FROM Alumno a WHERE a.email=:em AND a.pass=:ps", Alumno.class);
            q.setParameter("em", email);
            q.setParameter("ps", pass);
            salida = q.getSingleResult();
            LOG.info("Alumno traido correctamente");
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return salida;
    }
}
