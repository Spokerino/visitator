<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visitator</title>
<style>
 	td { 
 		border-bottom: 1px solid black; 
 		border-top: 1px solid black; 
 		} 
</style>
<link rel="stylesheet" href="<c:url value="/resources/visitator.css"/>" type="text/css">
</head>
<body>
	<header id="wellcome">	
		
			<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
			<span style="color: #E6E680">Lessons</span></h1>
			
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
 		<form method="post" action="/visitator/lessons">
		Show lessons at particular date<br>
				<input type="date" name="date">
		 		<input type="submit" value="Find">				
	 	</form>
	 	
	 	<form action="/visitator/lessons/new" method="get">
	 	<input type="submit"  value="Create new lesson" style="position: absolute; top:135; left: 1100;">
	 	</form>
 	<div id="tables">
	 	<table id="lw97">
	 		<tr>
	 			<th>Date</th>
	 			<th>Lesson start</th>
	 			<th>Lesson end</th>
	 			<th>Subject</th>
	 			<th>Teacher</th>
	 		</tr>
	 		<c:forEach items="${lessonList}" var="lesson" >
	 			<tr>
	 				<td>${lesson.date}</td>
	 				<td>${lesson.startFormat}</td>
	 				<td>${lesson.endFormat}</td>
	 				<td>${lesson.subject.name}</td>
	 				<td>${lesson.teacher.fullName}</td>
	 			</tr>
	 		</c:forEach>
	 	</table>
		 	<c:if test="${fn:length(lessonList) gt 20}">
		 		<hr />
		 		<s:url value="/lessons?count=${nextCount}" var="more_url" />
		 		<a href="${more_url}">More lessons</a>
		 	</c:if>
	 </div>

	 <footer >Copyright � by me 2015<br>
			 Original idea of design was taken from w3.schools.com
	 </footer>

</body>
</html>