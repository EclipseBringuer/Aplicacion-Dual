package com.cesur.aplicaciondual.domain.entities.alumno;

import com.cesur.aplicaciondual.domain.HibernateUtil;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import com.cesur.aplicaciondual.domain.entities.empresa.EmpresaDAOImp;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class AlumnoDAOImp implements AlumnoDAO{

    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AlumnoDAOImp.class);

    @Override
    public ArrayList<Alumno> getAll() {
        return null;
    }

    @Override
    public Alumno get(Integer id) {
        Alumno u = null;

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            Query<Alumno> q = s.createQuery("FROM Alumno where id=:n", Alumno.class);

            q.setParameter("n", id);

            try {

                u = q.getSingleResult();

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
        return null;
    }

    @Override
    public void update(Alumno data) {

    }

    @Override
    public void delete(Alumno data) {

    }
}
