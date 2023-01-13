package com.ba.boost.util;

import com.ba.boost.entity.Role;
import com.ba.boost.entity.Rule;
import com.ba.boost.entity.User;
import com.ba.boost.entity.UserDetail;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (HibernateUtil.sessionFactory == null) {
			HibernateUtil.sessionFactory = createSessionFactory();
		}
		return sessionFactory;
	}

	private static SessionFactory createSessionFactory() {
		Configuration config = new Configuration();
         /*
          * We should add out entitis here, with addAnnotatedClass method.
          */
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(UserDetail.class);
		config.addAnnotatedClass(Role.class);
		config.addAnnotatedClass(Rule.class);

		SessionFactory factory = config.configure("hibernate.cfg.xml").buildSessionFactory();
		System.out.println("Connection Successful...");
		return factory;

	}
}
