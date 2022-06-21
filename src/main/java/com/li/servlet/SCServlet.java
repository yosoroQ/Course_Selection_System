package com.li.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.li.entity.Course;
import com.li.entity.Sc;
import com.li.utils.MD5;
import com.li.utils.PageInfo;
import com.li.utils.PathUtils;

@WebServlet("/sc")
public class SCServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("select".equals(method)) {
			this.select(request, response);
		}else if("submit".equals(method)) {
			this.submit(request, response);
		}else if("tc".equals(method)) {
			this.teacher_course(request, response);
		}else if("cs".equals(method)) {
			this.course_student(request, response);
		}else if("score_submit".equals(method)) {
			this.score_submit(request, response);
		}
		
	}
	//�����ύ
	private void score_submit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer cId = getIntParameter(request, "cId");
		String[] stuIdArr = request.getParameterValues("stuId");
		String[] scoreArr = request.getParameterValues("score");
		try {
			DaoFactory.getInstance().getScDao().update(stuIdArr, scoreArr, cId);
			response.sendRedirect(PathUtils.getBasePath(request)+"sc?method=cs&cId="+cId+"&msg=1");
		} catch (SQLException e) {
			response.sendRedirect(PathUtils.getBasePath(request)+"sc?method=cs&cId="+cId+"&msg=0");
			e.printStackTrace();
		}
	}
	//ѡ�ε�ѧ����ѯ
	private void course_student(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1����ȡcid�Ĳ���
		Integer cId = getIntParameter(request, "cId");
		//2����ȡѧ���б�
		try {
			List<Student> list = DaoFactory.getInstance().getScDao().listStudentByCId(cId);
			request.setAttribute("list", list);
			request.setAttribute("cId", cId);
			request.getRequestDispatcher("page/sc/course_student.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//3��returnҳ��
		
	}
	
	private void teacher_course(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1����ȡ��ʦ��session�ж���tId
		Teacher teacher =(Teacher)request.getSession().getAttribute("user");
		Integer pageNo = getIntParameter(request, "pageNo");
		Course course = new Course();
		course.setTeacher(teacher);
		PageInfo<Course> pageInfo = new PageInfo<>(pageNo);
		//2����ѯ���̿γ��б�
		try {
			pageInfo = DaoFactory.getInstance().getCourseDao().list(course, pageInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//3������Ϣ���ص�jspҳ��
		request.setAttribute("pageInfo", pageInfo);
		request.getRequestDispatcher("page/sc/teacher_course.jsp").forward(request, response);
	}
	
	
	//����ѡ��
	private void submit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] cIds = request.getParameterValues("cId");
		List<Integer> cIdArray = new ArrayList<Integer>();
		for (String string : cIds) {
			cIdArray.add(Integer.parseInt(string));
		}
		Student student = (Student)request.getSession().getAttribute("user");
		try {
			int[] arr = DaoFactory.getInstance().getScDao().add(cIdArray, student.getStuId());
			if(arr.length == 0) {
				response.sendRedirect(PathUtils.getBasePath(request)+"sc?method=select&msg=0");
			}else {
				response.sendRedirect(PathUtils.getBasePath(request)+"sc?method=select&msg=1");
			}
		} catch (SQLException e) {
			response.sendRedirect(PathUtils.getBasePath(request)+"sc?method=select&msg=0");
		}
	}
	//ѡ����ת
	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageInfo<Course> info = new PageInfo<>(1);
		info.setPageSize(1000);
		try {
			info = DaoFactory.getInstance().getCourseDao().list(null, info);
			//��ȡ��¼��ѧ����Ϣ
			Student student = (Student)request.getSession().getAttribute("user");
			//��ȡ��ѡ�εĿγ�ID(cId)
			List<Sc> list = DaoFactory.getInstance().getScDao().listByStuId(student.getStuId());
			request.setAttribute("scs",list);
			request.setAttribute("courses", info.getList());
			request.getRequestDispatcher("page/sc/select.jsp").forward(request, response);
		} catch (SQLException e) {
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
