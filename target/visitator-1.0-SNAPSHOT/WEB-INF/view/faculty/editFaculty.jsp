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
				document.getElementById('alert').innerHTML = '/ / / / / / THIS FACULTY WILL BE DELETED! / / / / / /';
				
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
		<span>
			<a href="<c:url value="/colleges/${collegeId}/faculties" />" style="font-size: 17; color: #E6E680">
				Faculties
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
	<span style="color: #E6E680; font-size:20;">Faculty Editor</span>
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
<br>
	<sf:form method="post" commandName="faculty">
	<table id="newItem">
		<tr>
			<th>Faculty Name: </th>
			<td><sf:input type="text" path="name" /></td>
			<td><sf:errors path="name" cssClass="error"/></td>
		</tr>
		<tr>
			<th>Years to become Bachelor: </th>
			<td><sf:input type="text" path="yearsToBecomeBachelor" /></td>
			<td><sf:errors path="yearsToBecomeBachelor" cssClass="error"/></td>
		</tr>
		<tr>
			<th>Years to become Master: </th>
			<td><sf:input type="text" path="yearsToBecomeMaster" /></td>
			<td><sf:errors path="yearsToBecomeMaster" cssClass="error"/></td>
		</tr>
	</table>
		    
		    <br>
		    <span style="position: relative; left: 20px">
			    Delete this faculty: <input type="checkbox" id="check" name="check" style="margin-right: 20px;">
			    <input type="submit" value="Accept changes" >
			    
	    	</span>
	    	
	</sf:form>
	 
	 <hr width="97%">
	 <form style="float: left; padding-left: 20px; margin-top: 20px">
	 	<input type="submit" value="Add subjects" formaction="subjects/new">
		<input type="submit" value="Add teachers" formaction="teachers/new" style="margin-left: 15px">
		<input type="submit" value="Add groups" formaction="groups/new" style="margin-left: 15px">
	 </form>
	 
	 <br><br>
	 <h2 id="alert" style="text-align: center; color: red; padding-top: 5px"></h2>
	 
	 <footer >Copyright © by me 2015<br>
			 Original idea of design was taken from w3.schools.com
	 </footer>

</body>
</html>