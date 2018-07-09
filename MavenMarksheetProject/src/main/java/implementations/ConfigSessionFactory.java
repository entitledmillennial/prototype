/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zhee
 */
@Repository
public class ConfigSessionFactory {
    @Autowired
    SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }
    
    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public void create(Object o) {
        Session session = this.sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
           session.persist(o);
        } catch (HibernateException e) {
           System.out.println("Exception in create method"); 
        }
        tx.commit();
        session.close();
    }

    public void update(Object o) {
        Session session = this.sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.merge(o);
        } catch (HibernateException e) {
            System.out.println("Exception in update method"); 
        }
        tx.commit();
        session.close();
    }

    public void delete(Object o) {
        Session session = this.sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(o);
        } catch (HibernateException e) {
            System.out.println("Exception in delete method"); 
        }
        
        tx.commit();
        session.close();
    }
}
