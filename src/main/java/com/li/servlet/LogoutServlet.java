package com.li.servlet;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.li.dao.AdminDao;
import com.li.dao.DaoFactory;
import com.li.dao.StudentDao;
import com.li.dao.TeacherDao;
import com.li.entity.Admin;
import com.li.entity.Student;
import com.li.entity.Teacher;
import com.li.utils.MD5;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		resp.sendRedirect("login.jsp");
	}
	
}