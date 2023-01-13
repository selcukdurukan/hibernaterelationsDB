package com.ba.boost.dao;

import java.util.ArrayList;
import java.util.List;

import com.ba.boost.entity.Rule;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

public class RuleDao implements IDatabaseCrud<Rule> {

	@Override
	public List<Rule> retrive() {
		ArrayList<Rule> retriveRules = null;
		try (Session session = databaseConnection();) {

			String hql = "SELECT p FROM Rule AS p";

			TypedQuery<Rule> typedQuery = session.createQuery(hql, Rule.class);
			retriveRules = (ArrayList<Rule>) typedQuery.getResultList();

			for (Rule p : retriveRules) {
				System.out.println(p);
			}

		} catch (Exception e) {
			System.out.println("Some problem occured while RETRIVING all of RULE from DB.");
		}
		return retriveRules;
	}

	@Override
	public Rule update(long id, Rule entity) {
		Rule updateRule = find(id);

		try (Session session = databaseConnection();) {
			updateRule.setRuleName(entity.getRuleName());
			updateRule.setDescription(entity.getDescription());
			
			session.getTransaction().begin();
			session.merge(updateRule);
			session.getTransaction().commit();
			
			System.out.println("RULE data was UPDATED to DB at this " + session);
		} catch (Exception e) {
			System.out.println("Some problem occured while UPDATİNG RULE to DB");
		}
		return updateRule;

	}

	@Override
	public Rule delete(long id) {
		Rule deleteRule = find(id);
		try (Session session = databaseConnection();) {
			if (deleteRule != null) {
				session.getTransaction().begin();
				session.remove(deleteRule);
				session.getTransaction().commit();

				System.out.println("RULE whose " + id + " was deleted.");

			}
		} catch (Exception e) {
			System.out.println("Some problem occured while DELETING RULE from DB");
		}
		return deleteRule;

	}

	@Override
	public Rule find(long id) {
		Rule findRule = null;
		try (Session session = databaseConnection();) {
			findRule = session.find(Rule.class, id);
			if (findRule != null) {
				System.out.println(findRule);
			} else {
				System.out.println("RULE cannot FOUND.");
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while FINDING RULE.");
		}

		return findRule;
	}

}
