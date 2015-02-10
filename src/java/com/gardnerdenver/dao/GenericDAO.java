package com.gardnerdenver.dao;

import com.gardnerdenver.bean.UserItemFactoryBean;
import com.gardnerdenver.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.faces.context.FacesContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

abstract class GenericDAO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

//	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JSFCrudPU");
    protected static EntityManagerFactory emf = null;
    protected EntityManager em;
//    protected EntityTransaction tx;
//    protected String local = "server.gestorweb.com.br";
//    protected String local = "localhost";
    protected String database;
    protected String usuario;
    protected String senha;

    protected Map properties = new HashMap();

    public GenericDAO(String bancoExe, Class<T> entityClass, boolean create) {

//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpSession sessionhttp = null;
//        if (context != null) {
//            sessionhttp = (HttpSession) context.getExternalContext().getSession(true);
//        }
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

        this.entityClass = entityClass;
     //        Map properties = new HashMap();

        //        String auxDatabase = "gdpc";
        String auxDatabase;
        if (session == null) {
            auxDatabase = "gdpc";
        } else {
            auxDatabase = (String) session.getAttribute("database");
        }
        String auxUsuario = "varkon";
        String auxSenha = "qwert1234";

        //teste
        //        bancoExe = null;
        if (auxDatabase != null && !create) {
            bancoExe = auxDatabase;
        }
        if (bancoExe != null) {
            database = bancoExe;
        } else {
            database = auxDatabase;
            System.out.println("Erro ao conectar com o banco de dados");
            return;
        }

        if (database.equalsIgnoreCase("gdpc")) {
            System.out.println("Erro banco de dados dgpc");
            return;
        }
        //        if (auxUsuario != null) {
        usuario = auxUsuario;
        //        }
        //        if (auxSenha != null) {
        senha = auxSenha;
        //        }
        UserItemFactoryBean.numConn++;

        properties.put("exclude-unlisted-classes", "true");
        properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://" + Util.local + ":3306/" + database);// + "?zeroDateTimeBehavior=convertToNull");
        properties.put("hibernate.connection.url", "jdbc:mysql://" + Util.local + ":3306/" + database);// + "?zeroDateTimeBehavior=convertToNull");
        properties.put("javax.persistence.jdbc.user", usuario);
        properties.put("javax.persistence.jdbc.password", senha);

        if (emf != null && !create) {
            String urlDbtemp = "";
            String urlDb = emf.getProperties().get("javax.persistence.jdbc.url").toString();
            urlDbtemp += urlDb.substring(28);
            create = !database.equalsIgnoreCase(urlDbtemp);
        }

        //        if (emf == null || create || !emf.isOpen()) {
        emf = Persistence.createEntityManagerFactory("PU", properties);
        //        }

    }

//    public GenericDAO(String bancoExe, Class<T> entityClass, boolean create) {
//
//        //try {
//        String auxUsuario = "varkon";
//        String auxSenha = "qwert1234";
//        Map properties = new HashMap();
//
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
//
//        String auxDatabase = (String) session.getAttribute("db");
//
//        if (auxDatabase != null) {
//            database = auxDatabase;
//        }
//        if (auxUsuario != null) {
//            usuario = auxUsuario;
//        }
//        if (auxSenha != null) {
//            senha = auxSenha;
//        }
//
//        //Configure the internal EclipseLink connection pool
//        properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
//        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://" + Util.local + ":3306/" + database);
//        properties.put("javax.persistence.jdbc.user", usuario);
//        properties.put("javax.persistence.jdbc.password", senha);
//        properties.put("javax.persistence.ddl-generation", "create-tables");
//        properties.put("exclude-unlisted-classes", "true");
//
//        emf = Persistence.createEntityManagerFactory("PU", properties);
//
//    }
    private Class<T> entityClass;

    public void begin() {
//        emf = Persistence.createEntityManagerFactory("PU", properties);
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    public void commit() {
        em.getTransaction().commit();
    }

    public void rollback() {
        em.getTransaction().rollback();
    }

    public void close() {
        em.close();
        ///
//        emf.close();
    }

    public void commitAndClose() {
        commit();
        close();
    }

    public void flush() {
        em.flush();
    }

    public void joinTransaction() {
//        em = emf.createEntityManager();
        em.joinTransaction();
    }

    public void save(T entity) {
        em.persist(entity);
    }

    public void delete(Object id, Class<T> classe) {
        T entityToBeRemoved = em.getReference(classe, id);

        em.remove(entityToBeRemoved);
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public T find(int entityID) {
        return em.find(entityClass, entityID);

    }

    public T findReferenceOnly(int entityID) {
        return em.getReference(entityClass, entityID);
    }

    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    // Using the unchecked because JPA does not have a
    // query.getSingleResult()<T> method
    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;

        try {
            Query query = em.createNamedQuery(namedQuery);

            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            result = (T) query.getSingleResult();

        } catch (NoResultException e) {
            System.out.println("Nenhum resultado encontrado para consulta nomeada: " + namedQuery);
        } catch (Exception e) {
            System.out.println("Erro ao executar consulta: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    protected T findOneResultQuery(String qry, Map<String, Object> parameters) {
        T result = null;

        try {
            Session sessionD = (Session) em.getDelegate();
            org.hibernate.Query query = sessionD.createQuery(qry);
//            Query query = em.createQuery(qry);

            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryHBParameters(query, parameters);
            }

            result = (T) query.uniqueResult();

            sessionD.disconnect();
            sessionD.close();

        } catch (NoResultException e) {
            System.out.println("Nenhum resultado encontrado para consulta nomeada: " + qry);
        } catch (Exception e) {
            System.out.println("Erro ao executar consulta: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    protected List<T> findListResult(String namedQuery, Map<String, Object> parameters) {
        List<T> result = new ArrayList<>();

        try {
            Query query = em.createNamedQuery(namedQuery);

            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            result = query.getResultList();

        } catch (NoResultException e) {
            System.out.println("Nenhum resultado encontrado para consulta nomeada: " + namedQuery);
        } catch (Exception e) {
            System.out.println("Erro ao executar consulta: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    public boolean insert(T obj) {
        //EntityManager em = getEntityManager();
        //EntityManager em = null;
        try {
            em = emf.createEntityManager();

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(obj);
            tx.commit();

//            em.getTransaction().begin();
//            em.persist(obj);
//            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    private void populateQueryHBParameters(org.hibernate.Query query, Map<String, Object> parameters) {
        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

}
