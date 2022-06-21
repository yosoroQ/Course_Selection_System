package com.li.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.li.entity.Sc;
import com.li.entity.Student;
import com.li.utils.PropertiesUtils;

public class ScDao {

	
	public int[] add(List<Integer> cIdArray,Integer stuId) throws SQLException {
		DataSource dataSource = PropertiesUtils.getDataSource();
		Connection connection = dataSource.getConnection();
		connection.setAutoCommit(false);
		QueryRunner queryRunner = new QueryRunner(dataSource);
		String _sql = "delete from sc where stuId = ?";
		queryRunner.update(connection,_sql, stuId);
//		System.out.println(1/0);
		Object[][] object = new Object[cIdArray.size()][2];
		//将cIdArray与stuID保存为二维数组
		for(int i=0;i<cIdArray.size();i++) {
			object[i][0] = stuId;
			object[i][1] = cIdArray.get(i);
		}
		String sql = "insert into sc(stuId,cid) values(?,?)";
		int[] arr = queryRunner.batch(connection,sql, object);
		connection.commit();
		return arr;
	}

	public void delete(Integer scId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from sc where scId = ?";
		queryRunner.update(sql, scId);
	}

	public void update(String[] stuIdArr,String[] scoreArr,Integer cId) throws SQLException {
		
		DataSource dataSource = PropertiesUtils.getDataSource();
		Connection connection = dataSource.getConnection();
		connection.setAutoCommit(false);
		QueryRunner queryRunner = new QueryRunner(dataSource);
		Object[][] objects = new Object[stuIdArr.length][3];
		for(int i=0;i<stuIdArr.length;i++) {
			objects[i][0] = Integer.parseInt(scoreArr[i]==null?"0":scoreArr[i]) ;
			objects[i][1] = cId;
			objects[i][2] = Integer.parseInt(stuIdArr[i]);
		}
		String sql = "update sc set score = ? where cId = ? and stuId = ?";
		queryRunner.batch(sql,objects);
		connection.commit();
	}

	public List<Sc> list(Sc sc) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from sc";
		List<Sc> list = queryRunner.query(sql, new BeanListHandler<>(Sc.class));
		return list;
	}
	
	public List<Sc> listByStuId(Integer stuId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from sc where stuId = ?";
		List<Sc> list = queryRunner.query(sql, new BeanListHandler<>(Sc.class),stuId);
		return list;
	}

	public Sc findById(Integer scId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from sc where SCID = ?";
		Sc sc = queryRunner.query(sql,new BeanHandler<>(Sc.class),scId);
		return sc;
	}
	
	public List<Student> listStudentByCId(Integer cId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select student.*,score from sc,student where sc.stuId = student.stuId and  cId = ?";
		List<Student> list = queryRunner.query(sql, new BeanListHandler<>(Student.class),cId);
		return list;
	}
	
	public List<Map<String, Object>> query_range() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select course.cid,cname,ifnull(bad,0) bad,ifnull(common,0) common,ifnull(good,0) good,ifnull(best,0) best" + 
				" from course" + 
				" left join (" + 
				" select cid,count(*) bad from sc where score<60 group by cid" + 
				" ) A on course.cid = A.cid" + 
				" left join (" + 
				" select cid,count(*) common from sc where score>=60 and score<=70 group by cid" + 
				" ) B on  course.cid = B.cid" + 
				" left join(" + 
				" select cid,count(*) good from sc where score>70 and score<=85 group by cid" + 
				" ) C on course.cid = C.cid" + 
				" left join (" + 
				" select cid,count(*) best from sc where score>85 and score<=100 group by cid" + 
				" ) D on course.cid =D.cid ";
		
		List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler());
		return list;
	}
	
	
	//及格率
	public List<Map<String, Object>> query_jgl() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select A.cid,(" + 
				" select cname from course where A.cid = course.cid " + 
				" ) cname,jgnum,allnum,round(jgnum/allnum,2)*100 jgl from (" + 
				" select cid, count(*) jgnum from sc where score>=60 group by cid " + 
				" ) A,(" + 
				" select cid, count(*) allnum from sc group by cid " + 
				" ) B where A.cid = B.cid ";
		List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler());
		return list;
	}
	
	public List<Map<String, Object>> query_rangeByTid(Integer tId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select course.cid,cname,ifnull(bad,0) bad,ifnull(common,0) common,ifnull(good,0) good,ifnull(best,0) best" + 
				" from course" + 
				" left join (" + 
				" select cid,count(*) bad from sc where score<60 group by cid" + 
				" ) A on course.cid = A.cid" + 
				" left join (" + 
				" select cid,count(*) common from sc where score>=60 and score<=70 group by cid" + 
				" ) B on  course.cid = B.cid" + 
				" left join(" + 
				" select cid,count(*) good from sc where score>70 and score<=85 group by cid" + 
				" ) C on course.cid = C.cid" + 
				" left join (" + 
				" select cid,count(*) best from sc where score>85 and score<=100 group by cid" + 
				" ) D on course.cid =D.cid where tid = ?";
		
		List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(),tId);
		return list;
	}
	
	public List<Map<String, Object>> query_jglByTid(Integer tId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from course,(" + 
				" select A.cid,jgnum,allnum,round(jgnum/allnum,2)*100 jgl from (" + 
				" select cid, count(*) jgnum from sc where score>=60 group by cid " + 
				" ) A,(" + 
				" select cid, count(*) allnum from sc group by cid " + 
				" ) B where A.cid = B.cid" + 
				" ) C where course.cid = C.cid " + 
				" and tid = ?";
		List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(),tId);
		return list;
	}
	public List<Map<String, Object>> top5() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select stuId,(select stuName from student where student.stuId = sc.stuId ) stuName,sum(score) sumx from sc group by stuId order by sumx desc  limit 0,5";
		List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler());
		return list;
	}
	
}
