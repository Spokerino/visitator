<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visitator</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/visitator.css"/>">

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-1.11.3.js"></script>
	
<script type="text/javascript">
	$(document).ready(function() {
		$("#check").change(function() {
			if(this.checked) {
				document.getElementById('alert').innerHTML = '/ / / / / / THIS TEACHER WILL BE DELETED! / / / / / /';
				
			}
			else
				document.getElementById('alert').innerHTML = '';
			
		})
	})
</script>

</head>
<body>
<header style="height: 100">
	<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
	<hr>
		<span >
			<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/teachers" />" style="font-size: 17; color: #E6E680">
				Teachers
			</a>
		</span >
	</span>
	<span>
		<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
			College: ${collegeName}
		</a>
	</span><br>
	<span>
		<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
			Faculty: ${facultyName}
		</a>
	</span><br>
	<span id="itemHeader" style="padding-left: 0%">Teacher Editor</span></h1>
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


	<sf:form method="post" modelAttribute="teacher">
	<table id="newItem">
		<tr>
			<th>First Name: </th>
			<td><sf:input type="text" path="firstName" /></td>
			<td><sf:errors path="firstName" cssClass="error"/></td>
		</tr>
		<tr>
			<th>Last Name: </th>
			<td><sf:input type="text" path="lastName" /></td>
			<td><sf:errors path="lastName" cssClass="error"/></td>
		</tr>
		<tr>
			<th>Gender: </th> 
			<td>
				<sf:select path="gender">
					<sf:option value="1">Male</sf:option>
					<sf:option value="0">Female</sf:option>
				</sf:select>
			</td>
		</tr>
		<tr>
			<th>Birthday: </th>
			<td><sf:input type="date" path="birthday" /></td>
			<td><sf:errors path="birthday" cssClass="error"/></td>
		</tr>
		<tr>
			<th>Current Faculty: </th>
			
			<td width="30%">
<%-- 				<c:set var="facId" value="0" /> --%>
				<c:forEach items="${faculties}" var="faculty">
					<c:if test="${faculty.id == facultyId}">
						<input type="text" value="${faculty.name}" readonly="readonly">
					</c:if>
				</c:forEach>
			</td>
			<th style="width: 25%"><span style="color: blue">Move teacher to another Faculty</span></th>
			<td>	
				<select name="faculties">
					<option value="-1" selected="selected">Select a faculty</option>
					<c:forEach items="${faculties}" var="faculty">
						<option value="${faculty.id}" >${faculty.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>Subjects to add: </th>
			<td>
				<select name="subjectToAdd" multiple="multiple">
					<c:forEach items="${subjectsToAdd}" var="subject">
						<option value="${subject.id}">${subject.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<th>Subjects to remove: </th>
			<td>
				<select name="subjectToRemove" multiple="multiple">
					<c:forEach items="${teacher.subjects}" var="subject">
						<option value="${subject.id}">${subject.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>E-mail: </th>
			<td><sf:input type="text" path="email" /></td>
			<td><sf:errors path="email" cssClass="error"/></td>
		</tr>
		
   </table>
		    
		    <br>
		    <span style="position: relative; left: 20px">
			    Delete this teacher: <input type="checkbox" id="check" name="check" style="margin-right: 20px;">
			    <input type="submit" value="Accept changes" >
	    	</span>
	    	
	</sf:form>
	 
	 <br><br>
	 <h2 id="alert" style="text-align: center; color: red; padding-top: 5px"></h2>
	 
	 <footer >Copyright © by me 2015<br>
			 Original idea of design was taken from w3.schools.com
	 </footer>

</body>
</html>