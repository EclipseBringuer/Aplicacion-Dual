package com.cesur.aplicaciondual.domain.entities.alumno;

import com.cesur.aplicaciondual.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;

/**
 * Implementación de la interfaz AlumnoDAO que realiza operaciones CRUD para la entidad Alumno en la base de datos.
 */
public class AlumnoDAOImp implements AlumnoDAO{

    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AlumnoDAOImp.class);

    /**
     * Obtiene todos los alumnos de la base de datos (sin implementar actualmente).
     *
     * @return Lista de alumnos (actualmente nula).
     */
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

    /**
     * Guarda un nuevo alumno en la base de datos.
     *
     * @param data El alumno a guardar.
     * @return El alumno guardado.
     */
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

    /**
     * Actualiza un alumno existente en la base de datos.
     *
     * @param data El alumno con los nuevos datos.
     */
    @Override
    public void update(Alumno data) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            Transaction t = s.beginTransaction();

            Alumno vieja = s.get(Alumno.class, data.getDni());

            Alumno.merge(data, vieja);

            t.commit();

        }
    }

    /**
     * Elimina un alumno de la base de datos.
     *
     * @param data El alumno a eliminar.
     */
    @Override
    public void delete(Alumno data) {

        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {

            Transaction t = s.beginTransaction();

            s.remove(data);

            t.commit();

        }

    }

    /**
     * Obtiene un alumno por su cuenta de usuario (email y contraseña).
     *
     * @param email La dirección de correo electrónico del alumno.
     * @param pass  La contraseña del alumno.
     * @return El alumno encontrado en la base de datos, o null si no se encuentra.
     */
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
