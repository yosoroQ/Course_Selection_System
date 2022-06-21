<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="utf-8">
<link  rel="stylesheet"  href="${basePath}static/css/styles.css" />
		<link rel="stylesheet"  href="${basePath}static/css/font-awesome-4.7.0/css/font-awesome.min.css" />
		<script  src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
		<script  src="${basePath}static/js/jquery-validation-1.14.0/jquery.validate.js" type="text/javascript"></script>
		<script  src="${basePath}static/js/jquery-validation-1.14.0/localization/messages_zh.js" type="text/javascript"></script>
</head>
<body style="height: 100%; margin: 0">
<div class="add">
	<table class="tablelist" style="width: 50%;margin-left:50px">
		<c:if test="${type == 2}">
			<tr>
				<td width="120px">ID</td>
				<td>${user.id}</td>
			</tr>
			<tr>
				<td width="120px">用户名</td>
				<td>${user.userName}</td>
			</tr>
			<tr>
				<td width="120px">姓名</td>
				<td>${user.name}</td>
			</tr>
		</c:if>
		<c:if test="${type == 1}">
			<tr>
				<td width="120px">ID</td>
				<td>${user.tId}</td>
			</tr>
			<tr>
				<td width="120px">用户名</td>
				<td>${user.userName}</td>
			</tr>
			<tr>
				<td width="120px">姓名</td>
				<td>${user.tName}</td>
			</tr>
		</c:if>
		<c:if test="${type == 0}">
			<tr>
				<td width="120px">ID</td>
				<td>${user.stuId}</td>
			</tr>
			<tr>
				<td width="120px">学号</td>
				<td>${user.stuNo}</td>
			</tr>
			<tr>
				<td width="120px">姓名</td>
				<td>${user.stuName}</td>
			</tr>
		</c:if>
		
	</table>
	</div>
</body>
</html>