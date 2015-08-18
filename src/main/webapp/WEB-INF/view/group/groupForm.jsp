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
			<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/groups" />" style="font-size: 17; color: #E6E680">
				Groups
			</a>
		</span >
	</span>
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
			<span style="color: #AECFA2; font-size:20">Add a group</span></h1>

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


	<sf:form method="post" modelAttribute="collegeGroup">
	<table id="newItem">
		<tr>
			<th>Group Name: </th>
			<td><sf:input type="text" path="name" /></td>
			<td><sf:errors path="name" cssClass="error"/></td>
		</tr>
		<tr>
			<th>Group Course: </th>
			<td>
			<sf:select path="course">
				<sf:options  items="${courses}"/>
			</sf:select>
<!-- 				<select name="course"> -->
<%-- 					<c:forEach items="${courses}" var="course"> --%>
<%-- 						<option value="${course}">${course}</option> --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> -->
			</td>
		</tr>
	</table>
		    
		    <br>
		    <input type="submit" value="Add group" style="position: relative; left: 20px">
	    	
	</sf:form>
	 
	 <footer >Copyright © by me 2015<br>
			 Original idea of design was taken from w3.schools.com
	 </footer>

</body>
</html>