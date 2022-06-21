package com.li.entity;

/**
 * course: N-->teacher ：1
 * @author Administrator
 *
 */
public class Course {

	private Integer cId;
	private String cName;
	//老师所教这门课程
	private Teacher teacher;

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	
}
