<%@ page language="java" import="java.util.*, org.chenweifeng.servlet.*, org.chenweifeng.util.*"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
</head>

<body>
	<div class="container">
		<%@ include file="navbar.jsp"%>
		<div class="row">
			<div class="col-md-3">
				<%@ include file="teaLeft.jsp"%>
			</div>
			<div class="col-md-9">
				<%@ include file="teaPwd.jsp"%>

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
							<c:forEach items="${listSelected}" var="item">
								<tr>
									<td>${item.stuID }</td>
									<td>${item.stuName }</td>
									<td><a href="teaQueryProgress.jsp?group=${item.groupName}">${item.groupName}</a></td>
									<td>${item.leader ? "是" : ""}</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		updateClassNameOfLink('sideQueryGroup');
		
		document.getElementById('hiddenRequestURL').value="TeaQueryGroupAction";
	</script>
</body>
</html>
