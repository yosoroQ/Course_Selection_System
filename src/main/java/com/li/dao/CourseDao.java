package com.li.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.li.entity.Course;
import com.li.entity.Teacher;
import com.li.utils.PageInfo;
import com.li.utils.PropertiesUtils;

public class CourseDao {
	public void add(Course course) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into course(cname,tid) values(?,?)";
		queryRunner.update(sql, course.getcName(), course.getTeacher().gettId());
	}

	public void delete(Integer cid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from course where cid = ?";
		queryRunner.update(sql, cid);
	}

	public void update(Course course) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "update course set cname = ?,tid = ? where cid = ?";
		queryRunner.update(sql, course.getcName(), course.getTeacher().gettId(), course.getcId());
	}

	public PageInfo<Course> list(Course course,PageInfo<Course> pageInfo) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String _sql = "";
		List<Object> _list = new ArrayList<Object>();
		if(course != null && course.getcId() != null) {
			_sql += " and CID = ?";
			_list.add(course.getcId());
		}
		if(course != null && StringUtils.isNoneBlank(course.getcName())) {
			_sql += " and CNAME like ?";
			_list.add("%"+course.getcName()+"%");
		}
		if(course != null && StringUtils.isNoneBlank(course.getTeacher().gettName())) {
			_sql += " and tname like ?";
			_list.add("%"+course.getTeacher().gettName()+"%");
		}
		if(course != null && StringUtils.isNoneBlank(course.getTeacher().getUserName())) {
			_sql += " and USERNAME like ?";
			_list.add("%"+course.getTeacher().getUserName()+"%");
		}
		if(course != null && course.getTeacher().gettId() != null) {
			_sql += " and course.tid  = ?";
			_list.add(course.getTeacher().gettId());
		}
		//_list转数组
		Object[] arr = new Object[_list.size()];
		for (int i=0;i<_list.size();i++) {
			arr[i] = _list.get(i);
		}
		//TODO 
		String sql = "select course.*,teacher.tname,teacher.userName from course,teacher where course.tid = teacher.tId "+_sql+" limit  "+(pageInfo.getPageNo()-1)*pageInfo.getPageSize()+" , "+pageInfo.getPageSize();
		System.out.println(sql);
		System.out.println(Arrays.toString(arr));
		//List<Course> list = queryRunner.query(sql, new BeanListHandler<>(Course.class),arr);
		List<Map<String, Object>> Maplist = queryRunner.query(sql, new MapListHandler(),arr);
		//设置list集合
		List<Course> list = new ArrayList<>();
		//list<Map> -->list<Course>转换过程
		for (Map map :Maplist) {
			Course entity = new Course();
			entity.setcId(Integer.parseInt(map.get("cId")+""));
			entity.setcName(map.get("cName")+"");
			Teacher teacher = new Teacher();
			teacher.settId(Integer.parseInt(map.get("tId")+""));
			teacher.settName(map.get("tName")+"");
			teacher.setUserName(map.get("userName")+"");
			entity.setTeacher(teacher);
			list.add(entity);
		}
		pageInfo.setList(list);
		pageInfo.setTotalCount(this.count(course)); 
		return pageInfo;
	}

	
	public Long count(Course course) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String _sql = "";
		List<Object> _list = new ArrayList<Object>();
		if(course != null && course.getcId() != null) {
			_sql += " and CID = ?";
			_list.add(course.getcId());
		}
		if(course != null && StringUtils.isNoneBlank(course.getcName())) {
			_sql += " and CNAME like ?";
			_list.add("%"+course.getcName()+"%");
		}
		if(course != null && course.getTeacher().gettName() != null) {
			_sql += " and tname = ?";
			_list.add(course.getTeacher().gettName());
		}
		if(course != null && course.getTeacher().gettId() != null) {
			_sql += " and course.tid  = ?";
			_list.add(course.getTeacher().gettId());
		}
		//_list转数组
		Object[] arr = new Object[_list.size()];
		for (int i=0;i<_list.size();i++) {
			arr[i] = _list.get(i);
		}
		String sql = "select count(*) from course,teacher where course.tid = teacher.tId "+_sql;
		Long count = (Long)queryRunner.query(sql, new ScalarHandler(),arr);
		return count;
	}
	

	public Course findById(Integer cid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from course where cid = ?";
		Map<String, Object> map = queryRunner.query(sql, new MapHandler(),cid);
		Course course = new Course();
		course.setcId(Integer.parseInt(map.get("cId")+""));
		course.setcName(map.get("cName")+"");
		//二次查询老师信息设置关联
		Integer tid = Integer.parseInt(map.get("tId")+"");
		Teacher teacher =DaoFactory.getInstance().getTeacherDao().findById(tid);
		course.setTeacher(teacher);
		return course;
	}
}
