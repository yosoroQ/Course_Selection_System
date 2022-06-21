<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>列表</title>
		<link  rel="stylesheet"  href="${basePath}static/css/styles.css" />
		<link rel="stylesheet"  href="${basePath}static/css/font-awesome-4.7.0/css/font-awesome.min.css" />
		<script  src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$('.remove').click(function(){
					if(confirm("确定要删除吗？")){
						window.location.href="${basePath}student?method=delete&id="+$(this).attr("keyword");
					}
				})
			})
		</script>
	</head>
	<body>
		<form action="${basePath}student?method=list" method="post">
			<div class="condition">
				ID：<input type="text" name="stuId" value="${student.stuId}">
				姓名：<input type="text" name="stuName" value="${student.stuName}">
				学号：<input type="text" name="stuNo" value="${student.stuNo}">
				<button>
					<i class="fa fa-search"></i>
					查询
				</button>
				<button type="button" onclick="window.location.href='page/student/add.jsp'">
					<i class="fa fa-plus"></i>
					新增
				</button>
			</div>
		</form>
		<form action="${basePath}student?method=list" id="tableList" method="post">
		<input type="hidden" name="pageNo" value="${pageInfo.pageNo}">
		<input type="hidden" name="stuId" value="${student.stuId}">
		<input type="hidden" name="stuName" value="${student.stuName}">
		<input type="hidden" name="stuNo" value="${student.stuNo}">
		<table class="tablelist">
			<thead>
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>学号</th>
					<th width="120px">操作</th>
				</tr>
			</thead>
			<c:forEach items="${pageInfo.list}" var="student">
			<tr>
				<td>${student.stuId}</td>
				<td>${student.stuName}</td>
				<td>${student.stuNo}</td>
				<td>
					<button class="edit" type="button" onclick="window.location.href='${basePath}student?method=edit&id=${student.stuId}'">
						<i class="fa fa-edit"></i>
						修改
					</button>
					<button class="remove" type="button" keyword="${student.stuId}">
						<i class="fa fa-remove"></i>
						删除
					</button>
				</td>
			</tr>
			</c:forEach>
		</table>
		<%@include file="../inc/page.jsp"%>
		</form>
	</body>
</html>
