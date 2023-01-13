package com.ba.boost.dao;

import java.util.ArrayList;
import java.util.List;

import com.ba.boost.entity.User;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

public class UserDao implements IDatabaseCrud<User> {

	@Override
	public List<User> retrive() {
		ArrayList<User> retriveUsers = null;
		try (Session session = databaseConnection();) {

			String hql = "SELECT p FROM User AS p";

			TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
			retriveUsers = (ArrayList<User>) typedQuery.getResultList();

			for (User p : retriveUsers) {
				System.out.println(p);
			}

		} catch (Exception e) {
			System.out.println("Some problem occured while RETRIVING all of USER from DB.");
		}
		return retriveUsers;
	}

	@Override
	public User update(long id, User entity) {
		User updateUser = find(id);

		try (Session session = databaseConnection();) {
			updateUser.setEmail(entity.getEmail());
			updateUser.setPassword(entity.getPassword());
			updateUser.setUserDetail(entity.getUserDetail());
			updateUser.setRole(entity.getRole());

			session.getTransaction().begin();
			session.merge(updateUser);
			session.getTransaction().commit();
			
			System.out.println("User data was UPDATED to DB at this " + session);
		} catch (Exception e) {
			System.out.println("Some problem occured while UPDATİNG USER to DB");
		}
		return updateUser;

	}

	@Override
	public User delete(long id) {
		User deleteUser = find(id);
		try (Session session = databaseConnection();) {
			if (deleteUser != null) {
				session.getTransaction().begin();
				session.remove(deleteUser);
				session.getTransaction().commit();

				System.out.println("User whose " + id + " was deleted.");

			}
		} catch (Exception e) {
			System.out.println("Some problem occured while DELETING USER from DB");
		}
		return deleteUser;

	}

	@Override
	public User find(long id) {
		User findUser = null;
		try (Session session = databaseConnection();) {
			findUser = session.find(User.class, id);
			if (findUser != null) {
				System.out.println(findUser);
			} else {
				System.out.println("User cannot FOUND.");
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while FINDING USER.");
		}

		return findUser;
	}

}
