<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成功！</title>
<%@ include file="common.jsp"%>
</head>
<body>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<%@ include file="left.jsp"%>
			</div>
			<div class="col-md-9">
				<p>${resultMsg}. </p>
			</div>
		</div>
	</div>
</body>
</html>