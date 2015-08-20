<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visitator</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/visitator.css"/>">

</head>
<body>
<header style="height: 100">
	<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
	<hr>
		<span >
			<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/subjects" />" style="font-size: 17; color: #E6E680">
				Subjects
			</a>
		</span >
	</span>
	<span>
				<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
					Faculty: ${facultyName}
				</a>
	</span><br>
	 Add a Subject

		</h1>
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


	<form method="post" action="">
	<table style="text-align: left; width: 80%; padding: 10px">
		<tr>
			<th width="24%">Subjects to add: </th>
			<td>
				<select name="subjectToAdd" multiple="multiple">
					<c:forEach items="${subjectsToAdd}" var="subject">
						<option value="${subject.id}">${subject.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<th width="17%">Subjects to remove: </th>
			<td>
				<select name="subjectToRemove" multiple="multiple">
					<c:forEach items="${subjectsToRemove}" var="subject">
						<option value="${subject.id}">${subject.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
   </table>
		    
		    <br>
		    <input type="submit" value="Accept changes" style="position: relative; left: 20px">
	    	
	</form>
	 
	 <footer >Copyright © by me 2015<br>
			 Original idea of design was taken from w3.schools.com
	 </footer>

</body>
</html>