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
						window.location.href="${basePath}teacher?method=delete&id="+$(this).attr("keyword");
					}
				})
			})
		</script>
	</head>
	<body>
		<form action="${basePath}teacher?method=list" method="post">
			<div class="condition">
				ID：<input type="text" name="tId" value="${teacher.tId}">
				姓名：<input type="text" name="tName" value="${teacher.tName}">
				账号：<input type="text" name="userName" value="${teacher.userName}">
				<button>
					<i class="fa fa-search"></i>
					查询
				</button>
				<button type="button"  onclick="window.location.href='page/teacher/add.jsp'">
					<i class="fa fa-plus"></i>
					新增
				</button>
			</div>
		</form>
		<form action="${basePath}teacher?method=list" id="tableList" method="post">
		<input type="hidden" name="pageNo" value="${pageInfo.pageNo}">
		<input type="hidden" name="tId" value="${teacher.tId}">
		<input type="hidden" name="tName" value="${teacher.tName}">
		<input type="hidden" name="userName" value="${teacher.userName}">
		<table class="tablelist">
			<thead>
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>账号</th>
					<th width="120px">操作</th>
				</tr>
			</thead>
			<c:forEach items="${pageInfo.list}" var="teacher">
			<tr>
				<td>${teacher.tId}</td>
				<td>${teacher.tName}</td>
				<td>${teacher.userName}</td>
				<td>
					<button class="edit" type="button" onclick="window.location.href='${basePath}teacher?method=edit&id=${teacher.tId}'">
						<i class="fa fa-edit"></i>
						修改
					</button>
					<button class="remove" type="button" keyword="${teacher.tId}">
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
