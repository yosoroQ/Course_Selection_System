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
	   ${msg}
			<form id="addForm" action="${basePath}pwd" method="post">
					<c:if test="${type == 2}">
						<input type="hidden" name="id" value="${user.id}">
						<input type="hidden" name="type" value="2">
					</c:if>
					<c:if test="${type == 1}">
						<input type="hidden" name="id" value="${user.tId}">
						<input type="hidden" name="type" value="1">
					</c:if>
					<c:if test="${type == 0}">
					<input type="hidden" name="id" value="${user.stuId}">
					<input type="hidden" name="type" value="0">
					</c:if>
				<table class="tableadd" style="width: 50%;">
					<tr>
						<td>原密码</td>
						<td><input type="password" name="pwd"></td>
					</tr>
					<tr>
						<td>新密码</td>
						<td style="color: red;"><input type="password" name="newPwd"></td>
					</tr>
					<tr>
						<td>确认密码</td>
						<td>
							<input type="password" name="newPwd2">
						</td>
					</tr>
					<tr>
						<td colspan="4" align="left">
							<button class="remove" type="submit">
								<i class="fa fa-save"></i>
								提交
							</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>