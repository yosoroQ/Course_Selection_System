<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				$("#addForm").validate({
					rules:{
						tName:{required:true},
						userName:"required",
						pwd:{
							required:true,
							rangelength:[6,15]
						}
					}
				});
			});
		</script>
	</head>
	<body>
		<div class="add">
			<form id="addForm" action="${basePath}teacher?method=add" method="post">
				<table class="tableadd" style="width: 50%;">
					<tr>
						<td>姓名</td>
						<td><input type="text" name="tName"></td>
					</tr>
					<tr>
						<td>账号</td>
						<td style="color: red;"><input type="text" name="userName"></td>
					</tr>
					<tr>
						<td>密码</td>
						<td>
							<input type="password" name="pwd" value="123456">
							初始密码为123456
						</td>
					</tr>
					<tr>
						<td colspan="4" align="left">
							<button class="edit" type="button" onclick="window.history.back(-1);">
								<i class="fa fa-arrow-left"></i>
								返回
							</button>
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
