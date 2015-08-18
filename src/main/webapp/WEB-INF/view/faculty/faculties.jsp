<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visitator</title>
<link rel="stylesheet" href="<c:url value="/resources/visitator.css"/>" type="text/css">
</head>
<body style="height: 94%">
<div class="wrapper">
	<header>
		<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
		<span>
				<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
					College: ${collegeName}
				</a>
			</span><br>
		 Faculties</h1>
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
	
	<form style="float:right; padding-right: 30px" action="faculties/new">
		<input type="submit" value="Add a new faculty">
	</form>
	
	<table id="lw97">
		<tr>

			<th style="width:25%">Name</th>
			<th style="width:25%">Teachers</th>
			<th style="width:50%">Groups</th>
		</tr>
		<c:forEach items="${educationSpecializationList}" var="faculty">
		<tr>

			<td><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}">${faculty.name}</a></td>
			<td>
				<c:forEach items="${faculty.teachers}" var="teacher">
					<c:if test="${not empty teacher.firstName}">
						${teacher.fullName}<br>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0" />
				<c:forEach items="${faculty.groups}" var="group">
					<c:choose>
						<c:when test="${count == (fn:length(faculty.groups) - 1)}">
							<a href="/visitator/colleges/${collegeId}/faculties/${group.specialization.id}/groups/${group.id}"
							 style="text-decoration: none">
								${group.name}
							</a>
						</c:when>
						<c:otherwise>
							<a href="/visitator/colleges/${collegeId}/faculties/${group.specialization.id}/groups/${group.id}"
							 style="text-decoration: none">
								${group.name},
							</a>
						</c:otherwise>
					</c:choose>
				<c:set var="count" value="${count + 1}" />
				</c:forEach>
			</td>
			

		</tr>
		</c:forEach>
	</table>
	<div class="push"></div>
	
	</div>
	<br>	
	<div class="footer">Copyright © by Ivaniuk E.G. 2015<br>
		Original idea of design was taken from w3.schools.com
	</div>

</body>
</html>