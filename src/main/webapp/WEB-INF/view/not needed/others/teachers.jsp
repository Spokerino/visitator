<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visitator</title>
<style>
/* 	th { */
/* 	text-align: left; */
/* 	} */
/* 	table, th, td { */
/* 	border: 1px solid black; */
/* 	} */
</style>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/visitator.css"/>" >
</head>
<body>
	<header>
		<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span> Teachers</h1>
	</header>
	
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<span id="reg">
		    Currently logged as : <span>${pageContext.request.userPrincipal.name}</span> 
	        <c:url value="/logout" var="logoutUrl" />
	    </span>
	    <form id="reg" action="${logoutUrl} ">
				<input type="submit" name="submit" value="Logout" >
		</form>
	</c:if>
	
	<br>
	<table id="lw97">
		<tr>
			<th>Name</th>
			<th>Gender</th>
<!-- 			<th>Subject</th> -->
			<th>Birthday</th>
			<th>e-mail</th>
		</tr>
		<c:forEach items="${personList}" var="teacher">
		<tr>
			<td>${teacher.fullName}</td>
			<td>${teacher.gender}</td>
<%-- 			<td>${teacher.subject}</td> --%>
			<td>${teacher.birthday}</td>
			<td>${teacher.email}</td>
		</tr>
		</c:forEach>
	</table>
	
	<footer >Copyright © by me 2015<br>
			 Original idea of design was taken from w3.schools.com
	 </footer>
</body>
</html>