<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>新增</title>
		<link  rel="stylesheet"  href="${basePath}static/css/styles.css" />
		<link rel="stylesheet"  href="${basePath}static/css/font-awesome-4.7.0/css/font-awesome.min.css" />
		<script  src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
		<script  src="${basePath}static/js/jquery-validation-1.14.0/jquery.validate.js" type="text/javascript"></script>
		<script  src="${basePath}static/js/jquery-validation-1.14.0/localization/messages_zh.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$("#editForm").validate({
					rules:{
						stuNo:{
							required:true,
							digits:true
						},
						stuName:"required",
						stuPwd:{
							required:true,
							rangelength:[6,15]
						}
					}
					/* messages:{
						stuPwd:{
							rangelength:$.validator.format( "Please enter a value between {0} and {1} characters long.")
						}
					} */
				});
			});
		</script>
	</head>
	<body>
		<div class="add">
			<table style="width: 50%;">
				<tr>
					<td height="50px" width="150px">学号</td>
					<td>
						${student.stuNo}
					</td>
				</tr>
				<tr>
					<td height="50px">姓名</td>
					<td style="color: red;">
					${student.stuName}
					</td>
				</tr>
			</table>
			<table class="tablelist" style="width: 50%;">
			<c:forEach items="${list}" var="sc">
			<tr>
				<td>${sc.cName}</td>
				<td>${sc.score}</td>
			</tr>
			</c:forEach>
		</table>
		</div>
	</body>
</html>
