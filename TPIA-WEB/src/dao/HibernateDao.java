package dao;

import java.util.List;

import hbt.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateDao{
	protected static HibernateDao instancia = null;
	protected static SessionFactory sf = null;
	protected static Session session = null;

	
	public static HibernateDao getInstancia(){
		if(instancia == null){
			instancia = new HibernateDao();
		} 
		return instancia;
	}
	
	protected HibernateDao()  {
		sf = HibernateUtil.getSessionFactory();
	}

	protected Session getSession(){
		if(session == null || !session.isOpen()){
			session = sf.openSession();
		}
		return session;
	}
	
	public void closeSession(){
		if (session.isOpen()) {
			session.close();
		}
	}
	
	public void persist(Object obj) {
		Session session = getSession();
		session.beginTransaction();
		session.persist(obj);		
		session.getTransaction().commit();
	}
	
	public void merge(Object obj) {
		Session session = getSession();
		session.beginTransaction();
		session.merge(obj);
		session.getTransaction().commit();
	}
	
	public void delete(Object obj) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(obj);		
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("rawtypes")
	public void persistList(List lista){
		Session session = getSession();
		session.beginTransaction();
		for(Object e : lista){
			session.saveOrUpdate(e);
		}
		session.flush();
		session.getTransaction().commit();
	}
	
	public void update(Object obj) {
		Session session = getSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
	}
	
	public void save(Object obj) {
		Session session = getSession();
		session.beginTransaction();
		session.save(obj);		
		session.getTransaction().commit();
	}
	
}