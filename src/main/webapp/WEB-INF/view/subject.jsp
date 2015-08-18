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
				document.getElementById('alert').innerHTML = '/ / / / / / THIS SUBJECT WILL BE DELETED! / / / / / /';
				
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
			<a href="<c:url value="/subjects" />" style="font-size: 17; color: #E6E680">
				Subjects
			</a>
		</span >
	</span> Subject Editor

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
			<th width="14%">Subject Name: </th>
			<td><input type="text" name="name" value="${subject.name}"></td>
			<td><sf:errors path="name" cssClass="error"/></td>
		</tr>
   </table>
		    
		    <br>
		    <span style="position: relative; left: 20px">
			    Delete this subject: <input type="checkbox" id="check" name="check" style="margin-right: 20px">
			    <input type="submit" value="Accept changes" >
	    	</span>
	    	
	    	<h2 id="alert" style="text-align: center; color: red; padding-top: 5px"></h2>
	</sf:form>
	 
	 <footer >Copyright © by me 2015<br>
			 Original idea of design was taken from w3.schools.com
	 </footer>

</body>
</html>