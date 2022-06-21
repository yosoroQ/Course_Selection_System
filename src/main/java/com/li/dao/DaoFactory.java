package com.li.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DaoFactory {

	//类似于工厂+单例模式
	private static DaoFactory factory = new DaoFactory();

	private Map<String, Object> map = new ConcurrentHashMap<>();
	
	private DaoFactory() {

	}

	public static DaoFactory getInstance() {
		return factory;
	}
	
	public AdminDao getAdminDao() {
		AdminDao dao = (AdminDao) map.get("AdminDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new AdminDao();
			map.put("AdminDao", dao);
		}
		return dao;
	}
	
	public StudentDao getStudentDao() {
		StudentDao dao = (StudentDao) map.get("StudentDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new StudentDao();
			map.put("StudentDao", dao);
		}
		return dao;
	}
	public CourseDao getCourseDao() {
		CourseDao dao = (CourseDao) map.get("CourseDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new CourseDao();
			map.put("CourseDao", dao);
		}
		return dao;
	}
	public ScDao getScDao() {
		ScDao dao = (ScDao) map.get("ScDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new ScDao();
			map.put("ScDao", dao);
		}
		return dao;
	}

	public TeacherDao getTeacherDao() {
		TeacherDao dao = (TeacherDao) map.get("TeacherDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new TeacherDao();
			map.put("TeacherDao", dao);
		}
		return dao;
	}
	
	public static void main(String[] args) {
		System.out.println(DaoFactory.getInstance().getAdminDao());

	}
	
}
