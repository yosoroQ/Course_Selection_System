package com.li.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.li.entity.Student;
import com.li.utils.PageInfo;
import com.li.utils.PropertiesUtils;

public class StudentDao {

	public void add(Student student) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into student(stuName,stuNo,stuPwd) values(?,?,?)";
		queryRunner.update(sql, student.getStuName(), student.getStuNo(),student.getStuPwd());
	}

	public void delete(Integer stuId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from student where stuId = ?";
		queryRunner.update(sql, stuId);
	}

	public void update(Student student) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "update  student set stuName = ?,stuNo = ? where stuId = ? ";
		queryRunner.update(sql, student.getStuName(),student.getStuNo(),student.getStuId());
	}

	//分页DAO改造
	public PageInfo<Student> list(Student student,PageInfo<Student> pageInfo) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String _sql = "";
		List<Object> _list = new ArrayList<Object>();
		if(student.getStuId() != null) {
			_sql += " and STUID = ?";
			_list.add(student.getStuId());
		}
		if(StringUtils.isNoneBlank(student.getStuName())) {
			_sql += " and STUNAME like ?";
			_list.add("%"+student.getStuName()+"%");
		}
		if(StringUtils.isNoneBlank(student.getStuNo())) {
			_sql += " and STUNO like ?";
			_list.add("%"+student.getStuNo()+"%");
		}
		//_list转数组
		Object[] arr = new Object[_list.size()];
		for (int i=0;i<_list.size();i++) {
			arr[i] = _list.get(i);
		}
		String sql = "select * from student where 1=1 "+_sql+" limit  "+(pageInfo.getPageNo()-1)*pageInfo.getPageSize()+" , "+pageInfo.getPageSize();
		System.out.println(sql);
		System.out.println(Arrays.toString(arr));
		List<Student> list = queryRunner.query(sql, new BeanListHandler<>(Student.class),arr);
		
		pageInfo.setList(list);
		pageInfo.setTotalCount(this.count(student)); 
		return pageInfo;
	}
	
	public Long count(Student student) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String _sql = "";
		List<Object> _list = new ArrayList<Object>();
		if(student.getStuId() != null) {
			_sql += " and STUID = ?";
			_list.add(student.getStuId());
		}
		if(StringUtils.isNoneBlank(student.getStuName())) {
			_sql += " and STUNAME like ?";
			_list.add("%"+student.getStuName()+"%");
		}
		if(StringUtils.isNoneBlank(student.getStuNo())) {
			_sql += " and STUNO like ?";
			_list.add("%"+student.getStuNo()+"%");
		}
		//_list转数组
		Object[] arr = new Object[_list.size()];
		for (int i=0;i<_list.size();i++) {
			arr[i] = _list.get(i);
		}
		String sql = "select count(*) from student where 1=1 "+_sql;
		Long count = (Long)queryRunner.query(sql,new ScalarHandler(),arr);
		return count;
	}

	public Student findById(Integer sId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from student where STUID = ?";
		Student student = queryRunner.query(sql,new BeanHandler<>(Student.class),sId);
		return student;
	}
	
	public Student login(String stuNo,String stuPwd) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from student where STUNO = ? and STUPWD = ?";
		Student student = queryRunner.query(sql,new BeanHandler<>(Student.class),stuNo,stuPwd);
		return student;
	}
	
	public void update(String pwd,Integer stuId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "update  student set stuPwd = ? where stuId = ? ";
		queryRunner.update(sql, pwd,stuId);
	}
	
	
}
