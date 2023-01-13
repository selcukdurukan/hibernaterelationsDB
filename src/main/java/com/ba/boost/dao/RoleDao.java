package com.ba.boost.dao;

import java.util.ArrayList;
import java.util.List;

import com.ba.boost.entity.Role;
import com.ba.boost.entity.Role;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

public class RoleDao implements IDatabaseCrud<Role>{

	@Override
	public List<Role> retrive() {
		ArrayList<Role> retriveRoles = null;
		try (Session session = databaseConnection();) {

			String hql = "SELECT p FROM Role AS p";

			TypedQuery<Role> typedQuery = session.createQuery(hql, Role.class);
			retriveRoles = (ArrayList<Role>) typedQuery.getResultList();

			for (Role p : retriveRoles) {
				System.out.println(p);
			}

		} catch (Exception e) {
			System.out.println("Some problem occured while RETRIVING all of ROLE from DB.");
		}
		return retriveRoles;
	}

	@Override
	public Role update(long id, Role entity) {
		Role updateRole = find(id);

		try (Session session = databaseConnection();) {
			updateRole.setRoleName(entity.getRoleName());
			updateRole.setDescription(entity.getDescription());
			
			session.getTransaction().begin();
			session.merge(updateRole);
			session.getTransaction().commit();
			
			System.out.println("ROLE data was UPDATED to DB at this " + session);
		} catch (Exception e) {
			System.out.println("Some problem occured while UPDATİNG ROLE to DB");
		}
		return updateRole;

	}

	@Override
	public Role delete(long id) {
		Role deleteRole = find(id);
		try (Session session = databaseConnection();) {
			if (deleteRole != null) {
				session.getTransaction().begin();
				session.remove(deleteRole);
				session.getTransaction().commit();

				System.out.println("ROLE whose " + id + " was deleted.");

			}
		} catch (Exception e) {
			System.out.println("Some problem occured while DELETING ROLE from DB");
		}
		return deleteRole;

	}

	@Override
	public Role find(long id) {
		Role findRole = null;
		try (Session session = databaseConnection();) {
			findRole = session.find(Role.class, id);
			if (findRole != null) {
				System.out.println(findRole);
			} else {
				System.out.println("ROLE cannot FOUND.");
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while FINDING ROLE.");
		}

		return findRole;
	}

}
