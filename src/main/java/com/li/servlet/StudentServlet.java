package com.li.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.li.dao.DaoFactory;
import com.li.entity.Sc;
import com.li.entity.Course;
import com.li.entity.Student;
import com.li.utils.MD5;
import com.li.utils.PageInfo;
import com.li.utils.PathUtils;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("======StudentServlet========");
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getParameter("method");
		if("list".equals(method)) {
			this.list(request, response);
		}else if("add".equals(method)) {
			this.add(request, response);
		}else if("edit".equals(method)) {
			this.findById(request, response);
		}else if("editsubmit".equals(method)) {
			this.editsubmit(request, response);
		}else if("delete".equals(method)) {
			this.delete(request, response);
		}else if("detail".equals(method)) {
			this.detail(request, response);
		}
		
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			DaoFactory.getInstance().getStudentDao().delete(Integer.parseInt(id));
			//直接重定向到列表页面
			response.sendRedirect(PathUtils.getBasePath(request)+"student?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private void editsubmit(HttpServletRequest request, HttpServletResponse response) {
		Integer stuId = Integer.parseInt(request.getParameter("stuId"));
		String stuNo = request.getParameter("stuNo");
		String stuName = request.getParameter("stuName");
		Student student = new Student();
		student.setStuName(stuName);
		student.setStuNo(stuNo);
		student.setStuId(stuId);
		try {
			DaoFactory.getInstance().getStudentDao().update(student);
			//直接重定向到列表页面
			response.sendRedirect(PathUtils.getBasePath(request)+"student?method=list");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void findById(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			Student student = DaoFactory.getInstance().getStudentDao().findById(Integer.parseInt(id));
			request.setAttribute("student", student);
			request.getRequestDispatcher("page/student/update.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private void detail(HttpServletRequest request, HttpServletResponse response) {
		Student entity = (Student)request.getSession().getAttribute("user");
		try {
			Student student = DaoFactory.getInstance().getStudentDao().findById(entity.getStuId());
			List<Sc> list = DaoFactory.getInstance().getScDao().listByStuId(entity.getStuId());
			request.setAttribute("student", student);
			for(Sc sc:list) {
				Course c  = DaoFactory.getInstance().getCourseDao().findById(sc.getcId());
				sc.setcName(c.getcName());
			}
			request.setAttribute("list", list);
			request.getRequestDispatcher("page/student/detail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	private void add(HttpServletRequest request, HttpServletResponse response) {
		String stuNo = request.getParameter("stuNo");
		String stuName = request.getParameter("stuName");
		String stuPwd = request.getParameter("stuPwd");
		Student student = new Student();
		student.setStuName(stuName);
		student.setStuNo(stuNo);
		student.setStuPwd(MD5.encrypByMd5(MD5.encrypByMd5(stuPwd)));
		try {
			DaoFactory.getInstance().getStudentDao().add(student);
			//直接重定向到列表页面
			response.sendRedirect(PathUtils.getBasePath(request)+"student?method=list");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//分页---------------------
	private void list(HttpServletRequest request, HttpServletResponse response) {
		//当前页码
		Integer pageNo = getIntParameter(request, "pageNo");
		Integer stuId = getIntParameter(request, "stuId");
		String stuName = request.getParameter("stuName");
		String stuNo = request.getParameter("stuNo");
		
		Student student = new Student();
		student.setStuId(stuId);
		student.setStuName(stuName);
		student.setStuNo(stuNo);
		
		//构造了一个pageInfo对象
		PageInfo<Student> pageInfo = new PageInfo<>(pageNo);
		try {
			pageInfo = DaoFactory.getInstance().getStudentDao().list(student,pageInfo);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			request.setAttribute("pageInfo", pageInfo);
			//回写到页面
			request.setAttribute("student", student);
			request.getRequestDispatcher("page/student/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Integer getIntParameter(HttpServletRequest request,String name) {
		if(StringUtils.isNoneBlank(request.getParameter(name))) {
			return Integer.parseInt(request.getParameter(name));
		}else {
			return null;
		}
	}
	
	
	
}
