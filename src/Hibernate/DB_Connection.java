package Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.*;

public class DB_Connection {

	
	static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	static Session s;

	
	public static Session getSession() {
            s = sf.openSession();
		s.beginTransaction();
		return s;
	}
	
	public static void endSession() {
		s.getTransaction().commit();
		s.close();
	}
        
        
	
	
}
