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
import com.li.entity.Student;
import com.li.entity.Teacher;
import com.li.utils.MD5;
import com.li.utils.PageInfo;
import com.li.utils.PathUtils;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		}
		
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			DaoFactory.getInstance().getTeacherDao().delete(Integer.parseInt(id));
			//直接重定向到列表页面
			response.sendRedirect(PathUtils.getBasePath(request)+"teacher?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private void editsubmit(HttpServletRequest request, HttpServletResponse response) {
		Integer tId = Integer.parseInt(request.getParameter("tId"));
		String tName = request.getParameter("tName");
		String userName = request.getParameter("userName");
		Teacher teacher = new Teacher();
		teacher.settId(tId);
		teacher.settName(tName);
		teacher.setUserName(userName);
		try {
			DaoFactory.getInstance().getTeacherDao().update(teacher);
			//直接重定向到列表页面
			response.sendRedirect(PathUtils.getBasePath(request)+"teacher?method=list");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void findById(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			Teacher teacher = DaoFactory.getInstance().getTeacherDao().findById(Integer.parseInt(id));
			request.setAttribute("teacher", teacher);
			request.getRequestDispatcher("page/teacher/update.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	private void add(HttpServletRequest request, HttpServletResponse response) {
		String tName = request.getParameter("tName");
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		Teacher teacher = new Teacher();
		teacher.setUserName(userName);
		teacher.settName(tName);
		teacher.setPwd(MD5.encrypByMd5(MD5.encrypByMd5(pwd)));
		try {
			DaoFactory.getInstance().getTeacherDao().add(teacher);
			//直接重定向到列表页面
			response.sendRedirect(PathUtils.getBasePath(request)+"teacher?method=list");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) {
		//当前页码
		Integer pageNo = getIntParameter(request, "pageNo");
		//查询条件
		Integer tId = getIntParameter(request, "tId");
		String tName = request.getParameter("tName");
		String userName = request.getParameter("userName");
		
		Teacher teacher = new Teacher();
		teacher.setUserName(userName);
		teacher.settName(tName);
		teacher.settId(tId);
		
		//构造了一个pageInfo对象
		PageInfo<Teacher> pageInfo = new PageInfo<>(pageNo);
		try {
			pageInfo = DaoFactory.getInstance().getTeacherDao().list(teacher,pageInfo);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			request.setAttribute("pageInfo", pageInfo);
			//回写到页面
			request.setAttribute("teacher", teacher);
			request.getRequestDispatcher("page/teacher/list.jsp").forward(request, response);
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
