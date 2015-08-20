<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visitator</title>
<link rel="stylesheet" href="<c:url value="/resources/visitator.css"/>" type="text/css">
<style>
	form {
		padding-top: 5px;
		padding-bottom: 0px;
		position: relative;
	}
</style>
</head>
<body style="height: 94%">
<div class="wrapper">	
	
	
	<header>
		<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
					<span>
				<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
					College: ${faculty.institution.name}
				</a>
			</span><br>
			<span>
				<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
					Faculty: ${faculty.name}
				</a>
			</span><br>
			 <span id="itemHeader">Groups</span></h1>
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
	
	<form style="float:right; padding-right: 30px" action="groups/new">
		<input type="submit" value="Add a new group">
	</form>
	
	<table id="lw97">
		<tr>
			<th style="width:35%">Name</th>
			<th style="width:25%">Course</th>
			<th style="width:25%">Students</th>
		</tr>
		
		
		<c:set var="groupName" value=""/>
		<c:forEach items="${educationGroupList}" var="group">
	
			<tr>
				<td>
					<a id="link" href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${group.id}">
						${group.name}
					</a>
				</td>
	
				<td>
					${group.course}
				</td>
				
				<td>
					<c:set var="student" value="${group.students[0]}" />
					<c:if test="${not empty student.firstName}">
<!-- 					fn:length(group.students) returning 1 even if it is empty -->
						${fn:length(group.students)}
					</c:if>
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

	<script>
		function getSelected() {
			var data = document.getElementById("selector").value();
			return data;
		}
	</script>

</body>
</html>