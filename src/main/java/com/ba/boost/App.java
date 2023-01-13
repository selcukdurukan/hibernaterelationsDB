package com.ba.boost;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ba.boost.dao.RoleDao;
import com.ba.boost.dao.RuleDao;
import com.ba.boost.dao.UserDao;
import com.ba.boost.entity.Gender;
import com.ba.boost.entity.Role;
import com.ba.boost.entity.Rule;
import com.ba.boost.entity.User;
import com.ba.boost.entity.UserDetail;

import org.apache.commons.io.IOUtils;

public class App {

	public static void main(String[] args) {

//		UserDao userDao = new UserDao();
//
//		Role r1 = new Role("student", "student at bilgeadam");
//
//		UserDetail ud1 = new UserDetail("Selçuk", "Durukan", "From Beykent", Gender.MAN);
//		User u1 = new User("selcuk@durukan.com", "1545", ud1, r1);
//
//		userDao.create(u1);

		/*
		 * Biz yukarıda bu şekilde RoleDao.create ve UserDetail.create olmadan data
		 * yaratabiliriz çünkü bunların cascade type'ını persist olarak ayarladık.
		 */

		RoleDao roleDao = new RoleDao();
		Role role1 = new Role("Student", "Student at Bilgeadam");
		Role role2 = new Role("Teacher", "Teacher at Bilgeadam");

		RuleDao ruleDao = new RuleDao();
		Rule rule1 = new Rule("Login", "Login site with profile");
		Rule rule2 = new Rule("Give Note ", "Give note to students");
		ruleDao.create(rule1);
		ruleDao.create(rule2);

		List<Rule> teacherRoles = new ArrayList<Rule>();
		teacherRoles.add(rule1);
		teacherRoles.add(rule2);
		role2.setRules(teacherRoles);

		List<Rule> studentRoles = new ArrayList<Rule>();
		studentRoles.add(rule1);
		role1.setRules(studentRoles);

		roleDao.create(role1);
		roleDao.create(role2);

		UserDao userDao = new UserDao();
		FileInputStream input = null;
		try {
			input = new FileInputStream(new File(
					"C:\\Users\\selcu\\Desktop\\JWS\\MyBAWS\\Database-hibernaterelations\\userphotos\\selcuk.jpg"));
			byte[] selcukPicture = IOUtils.toByteArray(input);
			UserDetail ud1 = new UserDetail("Selçuk", "Durukan", "From Beykent", Gender.MAN, selcukPicture);
			User u1 = new User("selcuk@durukan.com", "1545", ud1, role1);
			userDao.create(u1);

			input = new FileInputStream(new File(
					"C:\\Users\\selcu\\Desktop\\JWS\\MyBAWS\\Database-hibernaterelations\\userphotos\\hilal.jpg"));
			byte[] hilalPicture = IOUtils.toByteArray(input);
			UserDetail ud2 = new UserDetail("Hilal", "Korkmaz", "From Odtu", Gender.WOMAN, hilalPicture);
			User u2 = new User("hilal@korkmaz.com", "4585", ud2, role1);
			userDao.create(u2);

			input = new FileInputStream(new File(
					"C:\\Users\\selcu\\Desktop\\JWS\\MyBAWS\\Database-hibernaterelations\\userphotos\\cagri.jpg"));
			byte[] cagriPicture = IOUtils.toByteArray(input);
			UserDetail ud3 = new UserDetail("Cagri", "Turkmen", "From Itu", Gender.MAN, cagriPicture);
			User u3 = new User("cagri@turkmen.com", "6532", ud3, role2);
			userDao.create(u3);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
