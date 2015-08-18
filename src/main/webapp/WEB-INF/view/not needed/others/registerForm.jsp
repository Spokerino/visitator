<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<html>
	<head>
		
		<title>Visitator registration</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/visitator.css"/>">
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
		<header>
		<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span> Registration</h1>
		</header><br>
		<sf:form method="POST" commandName="person" style="padding-left:10px; padding-top: 5px;">
<%-- 			<sf:label path="firstName" cssErrorClass="error">First Name:</sf:label> --%>
			First Name: <br/><sf:input path="firstName" cssErrorClass="error" />
			<sf:errors path="firstName" cssClass="error"/><br/>
			Last Name: <br/><sf:input path="lastName" cssErrorClass="error"/>
				<sf:errors path="lastName" cssClass="error"/><br/><br/>
			Sex: <sf:radiobutton path="male" value="true" />Male
				<sf:radiobutton path="male" value="false" />Female<br/>
			Status: <sf:radiobutton path="status" name="status" value="0" />Student
				<sf:radiobutton path="status" name="status" value="1"  />Teacher<br/><br/>	
			Date of birth: <br/><sf:input path="birthday" type="date"  /><br/></br>
				
			
			Email: <br><sf:input path="email" type="email" /></br>
			Username: <br><sf:input path="username" cssErrorClass="error"/>
				<sf:errors path="username" cssClass="error"/><br/>
			Password: <br><sf:password path="password" cssErrorClass="error"/>
				<sf:errors path="password" cssClass="error"/><br/><br/>
			<input type="submit" value="Register" />
		</sf:form>
		
	 <footer >Copyright © by me 2015<br>
			 Original idea of design was taken from w3.schools.com
	 </footer>
	
	
		
	</body>
</html>