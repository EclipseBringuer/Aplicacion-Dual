package com.cesur.aplicaciondual.domain.entities.empresa;

import com.cesur.aplicaciondual.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class EmpresaDAOImp implements EmpresaDAO {

    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EmpresaDAOImp.class);

    @Override
    public List<Empresa> getAll() {
        List<Empresa> salida = new ArrayList<>();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Empresa> q = s.createQuery("FROM Empresa", Empresa.class);

            try {
                salida = q.getResultList();
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }

        return salida;
    }

    @Override
    public Empresa get(Integer id) {

        Empresa u = null;

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            Query<Empresa> q = s.createQuery("FROM Empresa where id=:n", Empresa.class);

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
    public Empresa save(Empresa data) {

        Empresa empSave = null;

        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();

            s.persist(data);

            empSave = data;

            t.commit();

            LOG.info("Guardado correctamente: " + data);

        } catch (Exception e) {

            LOG.error(e.getMessage());

        }

        return empSave;

    }

    @Override
    public void update(Empresa nueva) {

        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {

            Transaction t = s.beginTransaction();

            Empresa vieja = s.get(Empresa.class, nueva.getId());

            Empresa.merge(vieja, nueva);

            t.commit();

        }

    }

    @Override
    public void delete(Empresa data) {

    }
}
