<%@ page language="java"
	import="java.util.*, org.chenweifeng.servlet.*, org.chenweifeng.util.*"
	pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="common.jsp"%>
</head>

<body>
	<div class="container">
		<%@ include file="navbar.jsp"%>
		<div class="row">
			<div class="col-md-3">
				<%@ include file="left.jsp"%>
			</div>
			<div class="col-md-9">
				<div class="panel panel-info">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						查询分组情况
					</div>
					<div class="panel-body">
						<p style="color: red">${errorMsg}</p>
						<form class="form-horizontal" method="post"
							action="QueryGroupAction">
							<div class="form-group">
								<label for="textStuID" class="col-sm-2 control-label">
									学号 </label>
								<div class="col-sm-6">
									<input type="text" name="textStuID" class="form-control"
										id="textStuID" placeholder="请输入学号" required>
								</div>
							</div>
							<div class="form-group">
								<label for="textStuName" class="col-sm-2 control-label">
									姓名 </label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="textStuName"
										id="textStuName" placeholder="请输入姓名" required>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-default">查询</button>
								</div>
							</div>
						</form>
					</div>
				</div>

				<div class="panel panel-success">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						查询结果
					</div>
					<div class="panel-body">
						<!-- Table -->
						<table class="table">
							<thead>
								<tr>
									<th>学号</th>
									<th>姓名</th>
									<th>小组名称</th>
									<th>是否组长</th>

								</tr>
							</thead>
							<tbody>
								<%
									List<StuInfo> list = (List<StuInfo>) request.getAttribute("listSelected");

									if (list != null) {
										for (StuInfo stuInfo : list) {
								%>
								<tr>
									<td><%=stuInfo.getStuID()%></td>
									<td><%=stuInfo.getStuName()%></td>
									<td><%=stuInfo.getGroupName()%></td>
									<td><%=stuInfo.isLeader() ? "是" : ""%></td>
								</tr>
								<%
										}
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		updateClassNameOfLink('sideQueryGroup');
	</script>
</body>
</html>
