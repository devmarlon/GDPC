/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gardnerdenver.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Marlon
 */
public class DAOGdpc {

    protected DAOGdpc() {
    }

    public static Session getSession() {
        Session session = (Session) DAOGdpc.session.get();
        if (session == null) {
            session = sessionFactory.openSession();
            DAOGdpc.session.set(session);
        }
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            System.out.println("Não foi possível fazer rollback da transação");
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            System.out.println("Não foi possível fazer fechar a sessão");
        }
        DAOGdpc.session.set(null);
    }

    public static void close() {
        getSession().close();
        DAOGdpc.session.set(null);
    }
    private static final ThreadLocal session = new ThreadLocal();
    private static final SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
}
