<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>与Scope有关的EL隐含对象</title>
</head>
<body>
<% pageContext.setAttribute("student", "zhangsan"); %>
<% session.setAttribute("student", "lisi"); %>
<p>pageContext中获取属性值： ${pageScope.student}</p>
<p>Session中获取属性值： ${sessionScope.student}</p>
</body>
</html>