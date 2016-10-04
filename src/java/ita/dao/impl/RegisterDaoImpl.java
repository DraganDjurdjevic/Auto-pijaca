/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.dao.impl;

import ita.dao.RegisterDao;
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
public class RegisterDaoImpl implements RegisterDao {

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
    public void getRegister(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        getSession().save(user);
        transaction.commit();
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
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        users = session.createCriteria(User.class).list();
        transaction.commit();
        return users;
    }

    @Override
    public User getUserById(Integer id) {
       Session session = getSession();
        
        Transaction transaction = session.beginTransaction();
        User u = (User) session.get(User.class, id);
        transaction.commit(); 
        
        return u;
    }

    @Override
    public void updateUser(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        getSession().update(user);
        transaction.commit();
        
    }

    @Override
    public void deleteUser(User user) {
        List<Oglas> oglasi = getAllOglasById(user.getUser_id());
        for(Oglas oglas : oglasi){
            System.out.println("IDDDD : " + oglas.getOglas_id());
            deleteOglas(oglas);
        }
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        getSession().delete(user);
        transaction.commit();
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
        public void deleteOglas(Oglas oglas) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        getSession().delete(oglas);
        transaction.commit();
    }


}
