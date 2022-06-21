<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>列表</title>
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet"  href="css/font-awesome-4.7.0/css/font-awesome.min.css" />
		<script  src="js/jquery.min.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="condition">
			用户名：<input type="text">
			用户名：<input type="text">
			用户名：<input type="text">
			<button>
				<i class="fa fa-search"></i>
				查询
			</button>
			<button onclick="window.location.href='add.html'">
				<i class="fa fa-plus"></i>
				新增
			</button>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th>姓名</th>
					<th>姓名</th>
					<th>姓名</th>
					<th>姓名</th>
					<th width="120px">操作</th>
				</tr>
			</thead>
			<tr>
				<td>姓名</td>
				<td>姓名</td>
				<td>姓名</td>
				<td>姓名</td>
				<td>
					<button class="edit">
						<i class="fa fa-edit"></i>
						修改
					</button>
					<button class="remove">
						<i class="fa fa-remove"></i>
						删除
					</button>
				</td>
			</tr>
			<tr>
				<td>姓名</td>
				<td>姓名</td>
				<td>姓名</td>
				<td>姓名</td>
				<td>
					<button class="edit">
						<i class="fa fa-edit"></i>
						修改
					</button>
					<button class="remove">
						<i class="fa fa-remove"></i>
						删除
					</button>
				</td>
			</tr>
			<tr>
				<td>姓名</td>
				<td>姓名</td>
				<td>姓名</td>
				<td>姓名</td>
				<td>
					<button class="edit">
						<i class="fa fa-edit"></i>
						修改
					</button>
					<button class="remove">
						<i class="fa fa-remove"></i>
						删除
					</button>
				</td>
			</tr>
			<tr>
				<td>姓名</td>
				<td>姓名</td>
				<td>姓名</td>
				<td>姓名</td>
				<td>
					<button class="edit">
						<i class="fa fa-edit"></i>
						修改
					</button>
					<button class="remove">
						<i class="fa fa-remove"></i>
						删除
					</button>
				</td>
			</tr>
			<tr>
				<td>姓名</td>
				<td>姓名</td>
				<td>姓名</td>
				<td>姓名</td>
				<td>
					<button class="edit">
						<i class="fa fa-edit"></i>
						修改
					</button>
					<button class="remove">
						<i class="fa fa-remove"></i>
						删除
					</button>
				</td>
			</tr>
		</table>
		<table class="page">
			<td>
				<button>首页</button>
				<button>上一页</button>
				<button>下一页</button>
				<button>尾页</button>
				<input  type="text"  class="page-no"  name="pageNo" />
				<button>转</button>
			</td>
		</table>
	</body>
</html>
