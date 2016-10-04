/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.dao.impl;

import ita.dao.OglasDao;
import ita.domein.Brend;
import ita.domein.Model;
import ita.domein.Oglas;
import ita.domein.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dragan
 */
@Repository
public class OglasDaoImpl implements OglasDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() throws HibernateException {
        Session sess = getSessionFactory().getCurrentSession();
        if (sess == null) {
            sess = getSessionFactory().openSession();
        }
//      Session sess = getSessionFactory().openSession();
        return sess;
    }

    @Override
    public void saveOglas(Oglas oglas) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        getSession().save(oglas);
        transaction.commit();
    }

    @Override
    public List<Brend> getAllBrebd() {
        List<Brend> brends = new ArrayList<Brend>();
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        brends = session.createCriteria(Brend.class).list();
        transaction.commit();
        return brends;
    }

    @Override
    public List<Model> getAllModel() {
        List<Model> models = new ArrayList<Model>();
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        models = session.createCriteria(Model.class).list();
        transaction.commit();
        return models;
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Oglas> getAllOglas() {
        List<Oglas> oglasi = new ArrayList<Oglas>();
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        oglasi = session.createCriteria(Oglas.class).list();
        transaction.commit();
        return oglasi;
    }

    public List<Oglas> getAllOglasById(int id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.getNamedQuery("getAllOglasById")
                .setInteger("id", id);
        List<Oglas> oglasi = query.list();
        transaction.commit();
        return oglasi;
    }

    @Override
    public List<Model> getAllModelByBrend(String brend) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.getNamedQuery("getAllModelByBrend")
                .setString("brend", brend);
        List<Model> modelLista = query.list();
        transaction.commit();
        return modelLista;
    }

    public List<Oglas> status() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.getNamedQuery("status")
                .setString("nije_odobren", "nije_odobren");
        List<Oglas> zaOdobrenjeOglasi = query.list();
        transaction.commit();
        return zaOdobrenjeOglasi;
    }

    public List<Oglas> OglasiHome() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.getNamedQuery("oglasiHome")
                .setString("odobren", "odobren");
        List<Oglas> oglasiHome = query.list();
        transaction.commit();
        return oglasiHome;
    }

    @Override
    public void deleteOglas(Oglas oglas) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        getSession().delete(oglas);
        transaction.commit();
    }

    @Override
    public Oglas getOglasById(Integer id) {
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        Oglas oglas = (Oglas) session.get(Oglas.class, id);
        transaction.commit();
        return oglas;
    }

    @Override
    public void updateOglas(Oglas oglas) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        getSession().update(oglas);
        transaction.commit();
    }

    @Override
    public List<Oglas> pretragaHome(Oglas oglas) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        List<Oglas> pretragaHome = new ArrayList<>();
        Query query;

        if (oglas.getBrend().getNaziv() == "" && oglas.getModel().getNaziv() == "") {
            query = session.getNamedQuery("pretragaCena")
                    .setFloat("cena", oglas.getCena())
                    .setString("odobren", "odobren");
            pretragaHome = query.list();
            transaction.commit();
        }

        if (oglas.getCena() != 0) {

            query = session.getNamedQuery("pretragaHome")
                    .setInteger("brend", oglas.getBrend().getBrend_id())
                    .setInteger("model", oglas.getModel().getModel_id())
                    .setFloat("cena", oglas.getCena())
                    .setString("odobren", "odobren");
            pretragaHome = query.list();
            transaction.commit();

        } else {
            oglas.setCena(1000000);

            query = session.getNamedQuery("pretragaHome")
                    .setInteger("brend", oglas.getBrend().getBrend_id())
                    .setInteger("model", oglas.getModel().getModel_id())
                    .setFloat("cena", oglas.getCena())
                    .setString("odobren", "odobren");
            pretragaHome = query.list();
            transaction.commit();

        }

        return pretragaHome;
    }

}
