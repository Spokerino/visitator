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
			<a href="<c:url value="/subjects" />" style="font-size: 17; color: #E6E680">
				Subjects
			</a>
		</span >
	</span> Add a Subject

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


	<sf:form method="post" action="" commandName="subject">
	<table style="text-align: left; width: 80%; padding: 10px">
		<tr>
			<th>Subject Name: </th>
			<td><sf:input type="text" path="name" /></td>
			<td><sf:errors path="name" cssClass="error"/></td>
		</tr>
   </table>
		    
		    <br>
		    <input type="submit" value="Add subject" style="position: relative; left: 20px">
	    	
	</sf:form>
	 
	 <footer >Copyright © by me 2015<br>
			 Original idea of design was taken from w3.schools.com
	 </footer>

</body>
</html>