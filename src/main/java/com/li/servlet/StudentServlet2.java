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
import com.li.utils.MD5;
import com.li.utils.PathUtils;

@WebServlet("/student2")
public class StudentServlet2 extends HttpServlet {

	
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
		}
		
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			DaoFactory.getInstance().getStudentDao().delete(Integer.parseInt(id));
			//ֱ���ض����б�ҳ��
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
			//ֱ���ض����б�ҳ��
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
			//ֱ���ض����б�ҳ��
			response.sendRedirect(PathUtils.getBasePath(request)+"student?method=list");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) {
		//��ǰҳ��
		Integer pageNo = getIntParameter(request, "pageNo");
		int pageSize = 10;
		//��pageNo����Ϊ�յ�ʱ����Ĭ��Ϊ��1ҳ
		if(pageNo == null) {
			pageNo = 1;
		}
		//�ܼ�¼����
		Long totalCount = 0L;
		try {
			totalCount = DaoFactory.getInstance().getStudentDao().count(null);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//��ҳ��
		Long totalPage = totalCount/pageSize;
		if(totalCount%pageSize!=0 || totalPage == 0) {
			totalPage++;
		}
		//��һҳ
		Integer nextPage = 0;
		if(nextPage<totalPage) {
			nextPage = pageNo+1;
		}else {
			nextPage = pageNo;
		}
		
		//��һҳ
		Integer prePage = 0;
		if(pageNo>1) {
			prePage = pageNo-1;
		}else {
			prePage = pageNo;
		}
		boolean isFirstPage = false;
		if(pageNo>1) {
			isFirstPage = false;
		}else {
			isFirstPage = true;
		}
		
		boolean isLastPage = false;
		if(pageNo<totalPage) {
			isLastPage = false;
		}else {
			isLastPage = true;
		}
		try {
			List<Student> list = null;//DaoFactory.getInstance().getStudentDao().list(null,pageNo,pageSize);
			request.setAttribute("list", list);
			request.setAttribute("pageSize", pageSize);
		
			request.setAttribute("isFirstPage", isFirstPage);//�Ƿ��һҳ
			request.setAttribute("isLastPage", isLastPage);//�Ƿ����ҳ
			
			request.setAttribute("totalCount", totalCount);
			request.setAttribute("totalPage",totalPage);
			request.setAttribute("prePage", prePage);
			request.setAttribute("nextPage", nextPage);
			request.setAttribute("pageNo", pageNo);
			request.getRequestDispatcher("page/student/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Integer getIntParameter(HttpServletRequest request,String name) {
		if(request.getParameter(name) != null) {
			return Integer.parseInt(request.getParameter(name));
		}else {
			return null;
		}
	}
	
	
	
}
