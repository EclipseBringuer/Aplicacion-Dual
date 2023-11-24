package com.cesur.aplicaciondual.domain.entities.profesor;

import com.cesur.aplicaciondual.domain.HibernateUtil;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import com.cesur.aplicaciondual.domain.entities.empresa.EmpresaDAOImp;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class ProfesorDAOImp implements ProfesorDAO {

    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EmpresaDAOImp.class);

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
    public Profesor get(Integer id) {

        Profesor p = null;

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            Query<Profesor> q = s.createQuery("FROM Profesor where id=:n", Profesor.class);

            q.setParameter("n", id);

            try {

                p = q.getSingleResult();

                LOG.info("Profesor obtenido correctamente");

            } catch (Exception e) {
                LOG.error(e.getMessage());
            }

        } catch (Exception e) {

            LOG.error(e.getMessage());

        }

        return p;
    }

    @Override
    public Profesor save(Profesor data) {
        return null;
    }

    @Override
    public void update(Profesor nueva) {

        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {

            Transaction t = s.beginTransaction();

            Profesor vieja = s.get(Profesor.class, nueva.getId());

            Profesor.merge(vieja, nueva);

            t.commit();

        }
    }

    @Override
    public void delete(Profesor data) {

    }
}
