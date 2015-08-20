<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visitator</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/visitator.css"/>" >
</head>
<body style="height: 94%">
<div class="wrapper">
	<header style="height: 8%">
		<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
		<span style="font-size: 20">Subjects</span></h1>
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
	
	<form style="float:right; padding-right: 30px" action="subjects/new">
		<input type="submit" value="Add a new subject">
	</form>
	
	<table id="lw97">
		<tr>
			<th>Name</th>
		</tr>
		
		<c:forEach items="${subjectList}" var="subject">
			<tr>
				<td>
					<a href="subjects/${subject.id}/edit" style="text-decoration: none">
						${subject.name}
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<div class="push"></div>
	</div><br>

	<div class="footer" >Copyright © by me 2015<br>
			 Original idea of design was taken from w3.schools.com
	 </div>
</body>
</html>