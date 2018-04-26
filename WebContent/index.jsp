<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

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
						分组
					</div>
					<div class="panel-body">
						<p style="color: red">${requestScope.errorMsg}</p>
						<form class="form-horizontal" method="post" action="JoinGroupAction">
							<div class="form-group">
								<label for="textStuID" class="col-sm-2 control-label">
									学号 </label>
								<div class="col-sm-6">
									<input type="text" name="textStuID" class="form-control"
										id="textStuID" placeholder="请输入学号" pattern="[0-9]{12}"
										required>
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
								<label for="textGroupName" class="col-sm-2 control-label">
									组名 </label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="textGroupName"
										id="textGroupName" placeholder="请输入小组名称（不多于25个汉字）" required>
								</div>
							</div>

							<div class="form-group">
								<label for="textGroupName" class="col-sm-2 control-label">
									角色 </label>
								<div class="col-sm-3">
									<select class="form-control" name="selectRole">
										<option value="组长">组长</option>
										<option value="组员">组员</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label for="textCheckCode" class="col-sm-2 control-label">
									校验码 </label>
								<div class="col-sm-3">
									<input type="text" name="textCheckCode" class="form-control"
										id="textCheckCode" placeholder="4位校验码" pattern="[0-9]{4}"
										required>
								</div>
								<div class="col-sm-2">
									<img src="CheckCode"
										onclick="this.src='CheckCode?'+Math.random()">
								</div>
								<div class="col-sm-3">${checkCodeErrorMsg}</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-default">提交分組申請</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		updateClassNameOfLink('sideIndex');
	</script>
</body>
</html>
