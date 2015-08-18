<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Visitator registration</title>
		<link rel="stylesheet" type="text/css" href="">
		<style>
			span.error {
			color: red;
			}
			input.error {
			background-color: #ffcccc;
			}
		</style>
	</head>
	<body>
		<h1>Registration</h1>
		
		<sf:form method="POST" commandName="visitatr">
			First Name<sf:input path="firstName" cssErrorClass="error" />
<%-- 			<sf:errors path="firstName" cssClass="error"/><br/> --%>
			Last Name: <sf:input path="lastName" />
<%-- 				<sf:errors path="lastName" cssClass="error"/><br/> --%>
<%-- 			Sex: <sf:input type="radio" path="isMale" name="sex" value="male"  cssClass="error" checked />Male<br/> --%>
<%-- 				<sf:input type="radio" path="isMale" name="sex" value="female" cssClass="error" /><br/>Female --%>
			Date of birth: <sf:input path="birthday" type="date" cssClass="error" />
<%-- 				<sf:errors path="birthday" cssClass="error" /><br/>	 --%>
<%-- 			Status: <sf:input type="radio" path="status" name="status" value="student" checked cssClass="error" />Student<br/> --%>
<%-- 				<sf:input type="radio" path="status" name="status" value="teacher" cssClass="error" /><br/>Teacher --%>
			Email: <sf:input path="email" type="email"/>
<%-- 				<sf:errors path="email" cssClass="error"/><br/> --%>
			Username: <sf:input path="username" />
<%-- 				<sf:errors path="username" cssClass="error"/><br/> --%>
			Password: <sf:password path="password" />
<%-- 				<sf:errors path="password" cssClass="error"/><br/> --%>
			<input type="submit" value="Register" />
		</sf:form>
		
	
		
	</body>
</html>