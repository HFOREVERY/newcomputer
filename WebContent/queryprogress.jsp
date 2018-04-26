<%@ page language="java" import="java.util.*, org.chenweifeng.servlet.*, org.chenweifeng.util.*"
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
						查询所在分组
					</div>
					<div class="panel-body">
						<p style="color: red">${errorMsg}</p>
						<form class="form-horizontal" method="post"
							action="QueryProgressAction">
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
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						已提交报告(最近20条)
					</div>
					<div class="panel-body">
						<p style="color: red">${errorMsg2}</p>
						<form class="form-horizontal" method="post"
							action="DeleteReportAction">
							<!-- Table -->
							<table class="table">
								<thead>
									<tr>
										<th>姓名</th>
										<th>进度报告</th>
										<th>提交时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<%
									List<ReportInfo> list = (List<ReportInfo>) request.getAttribute("listReports");
									String stuID = (String) request.getAttribute("currentStuID");

									if (list != null && stuID != null) {
										for (ReportInfo reportInfo : list) {
									%>
									<tr>
										<td><%=reportInfo.getStuName()%></td>
										<td><%=reportInfo.getReportContent()%></td>
										<td><%=reportInfo.getReportTimestamp().toString()%></td>
										<td>
											<%
												if (stuID.equals(reportInfo.getStuID())) {
											%><input
												type="submit" class="btn btn-default" value="删除" name=<%="reportID-"+reportInfo.getReportID() %> />
											<%
												}
											%>
										</td>
									</tr>
									<%
										}
									}
									%>
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		updateClassNameOfLink('sideQueryProgress');
		function deleteReportJS() {
			alert("hha");
		}
	</script>
</body>
</html>
