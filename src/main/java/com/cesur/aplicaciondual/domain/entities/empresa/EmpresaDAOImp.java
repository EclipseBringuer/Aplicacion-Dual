package com.cesur.aplicaciondual.domain.entities.empresa;

import com.cesur.aplicaciondual.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class EmpresaDAOImp implements EmpresaDAO{

    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EmpresaDAOImp.class);

    @Override
    public ArrayList<Empresa> getAll() {
        return null;
    }

    @Override
    public Empresa get(Long id) {

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
        return null;
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
