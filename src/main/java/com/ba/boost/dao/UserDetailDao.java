package com.ba.boost.dao;

import java.util.ArrayList;
import java.util.List;

import com.ba.boost.entity.UserDetail;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

public class UserDetailDao implements IDatabaseCrud<UserDetail>{

	@Override
	public List<UserDetail> retrive() {
		ArrayList<UserDetail> retriveUserDetails = null;
		try (Session session = databaseConnection();) {

			String hql = "SELECT p FROM UserDetail AS p";

			TypedQuery<UserDetail> typedQuery = session.createQuery(hql, UserDetail.class);
			retriveUserDetails = (ArrayList<UserDetail>) typedQuery.getResultList();

			for (UserDetail p : retriveUserDetails) {
				System.out.println(p);
			}

		} catch (Exception e) {
			System.out.println("Some problem occured while RETRIVING all of USERDETAILS from DB.");
		}
		return retriveUserDetails;
	}

	@Override
	public UserDetail update(long id, UserDetail entity) {
		UserDetail updateUserDetail = find(id);

		try (Session session = databaseConnection();) {
			updateUserDetail.setFirstName(entity.getFirstName());
			updateUserDetail.setLastName(entity.getLastName());
			updateUserDetail.setBiography(entity.getBiography());
			updateUserDetail.setGender(entity.getGender());
			updateUserDetail.setPicture(entity.getPicture());

			session.getTransaction().begin();
			session.merge(updateUserDetail);
			session.getTransaction().commit();
			
			System.out.println("USERDETAIL data was UPDATED to DB at this " + session);
		} catch (Exception e) {
			System.out.println("Some problem occured while UPDATİNG USERDETAIL to DB");
		}
		return updateUserDetail;

	}

	@Override
	public UserDetail delete(long id) {
		UserDetail deleteUserDetail = find(id);
		try (Session session = databaseConnection();) {
			if (deleteUserDetail != null) {
				session.getTransaction().begin();
				session.remove(deleteUserDetail);
				session.getTransaction().commit();

				System.out.println("USERDETAIL whose " + id + " was deleted.");

			}
		} catch (Exception e) {
			System.out.println("Some problem occured while DELETING USERDETAIL from DB");
		}
		return deleteUserDetail;

	}

	@Override
	public UserDetail find(long id) {
		UserDetail findUserDetail = null;
		try (Session session = databaseConnection();) {
			findUserDetail = session.find(UserDetail.class, id);
			if (findUserDetail != null) {
				System.out.println(findUserDetail);
			} else {
				System.out.println("USERDETAIL cannot FOUND.");
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while FINDING USERDETAIL.");
		}

		return findUserDetail;
	}

}
