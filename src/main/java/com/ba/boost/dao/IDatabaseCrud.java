package com.ba.boost.dao;

import java.util.List;

import com.ba.boost.util.HibernateUtil;

import org.hibernate.Session;

public interface IDatabaseCrud<T> {

	default void create(T entity) {
		try (Session session = databaseConnection();) {
			session.getTransaction().begin();

			session.persist(entity);

			session.getTransaction().commit();
			System.out.println("Data is added to DB.");
		} catch (Exception e) {
			System.out.println("Some problem occured while ADDING data to DB.");
		}
	}

	List<T> retrive();

	T update(long id, T entity);

	T delete(long id);

	T find(long id);

	default Session databaseConnection() {
		return HibernateUtil.getSessionFactory().openSession();
	}

}
