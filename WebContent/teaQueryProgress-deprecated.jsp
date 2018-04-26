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

										if (list != null) {
											for (ReportInfo reportInfo : list) {
									%>
									<tr>
										<td><%=reportInfo.getStuName()%></td>
										<td><%=reportInfo.getReportContent()%></td>
										<td><%=reportInfo.getReportTimestamp().toString()%></td>
										<td><input type="submit" class="btn btn-default"
											value="删除" name=<%="reportID-" + reportInfo.getReportID()%> />
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

		document.getElementById('hiddenRequestURL').value = "TeaQueryProgressAction";
	</script>
</body>
</html>
