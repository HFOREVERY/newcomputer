<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<span class="glyphicon glyphicon-user" aria-hidden="true"></span> 身份验证
	</div>
	<div class="panel-body">
		<p style="color: red">${request.errorMsg}</p>
		<form class="form-horizontal" method="post" action="TeaVerify">
			<div class="form-group">
				<label for="textStuID" class="col-sm-2 control-label"> 密码 </label>
				<div class="col-sm-6">
					<input type="password" name="pwdTeacher" class="form-control"
						id="pwdTeacher" placeholder="请输入查询密码" required>
					<input type="hidden" name="hiddenRequestURL" id="hiddenRequestURL" value="teaPwd.jsp" >
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">确定</button>
				</div>
			</div>
		</form>
	</div>
</div>