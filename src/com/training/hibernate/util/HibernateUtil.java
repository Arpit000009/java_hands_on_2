package com.training.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionfactory = 
			new Configuration().configure().buildSessionFactory();
	public static SessionFactory getSessionFactory() {
		return sessionfactory;
	}
}
