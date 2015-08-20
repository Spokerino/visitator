<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visitator</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/visitator.css"/>">

<script 
	type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-1.11.3.js"></script>
	
<script type="text/javascript">
$(document).ready(function() {
	
	$("#types").change(function() {
		if($("select#types").val() == 1)
			{
				$('#groups').attr('multiple','multiple');
			}
		else
			{
				$('#groups').attr('multiple', false);
			}
	})
})
</script>		
</head>
<body>
<%-- 	<sform:errors path="less.*" /> --%>

<header style="height: 100">
	<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
	<hr>
		<span >
			<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/lessons" />" style="font-size: 17; color: #E6E680">
				Lessons
			</a>
		</span ><br>
	</span> Create new lesson
<!-- 	<span style="color: black">__</span>  -->
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
			<th>Date:</th>
			<th>Lesson begins at:</th>
			<th>Lesson ends at:</th>
			<th>Type:</th>
			<th>Group:</th>
			<th>Subject:</th>
			<th>Teacher:</th>
		</tr>
		<tr>
			<td><input id="date" type="date" name="date" /></td>
		    <td><input id="lStart" type="time" oninput="setLessonEnd()" name="start" /></td>
		    <td><input id="lEnd" type="time" name="end" /></td>
		    	    
		    <td>
	    		<select name="types" id="types">
	    			<option value="2" >Practicum</option>
					<option value="1" >Lecture</option>
					<option value="3" >Labarotory Practicum</option>
					<option value="4" >Consultation</option>
		    	</select>
	    	</td>
		    
		    <td>
	    		<select name="groups" id="groups">
			    	<c:forEach items="${faculty.groups}" var="group">		    
						<option value="${group.id}" >${group.name}</option>
			    	</c:forEach>
		    	</select>
	    	</td>
		    
		    <td>
			    <select name="subjects" >
			    	<c:forEach items="${faculty.subjects}" var="subject">		    
						<option value="${subject.id}" >${subject.name}</option>
			    	</c:forEach>
		    	</select>
	    	</td>
	    	
	    	<td>
			    <select name="teachers">
			    	<c:forEach items="${faculty.teachers}" var="teacher">
		    			<option value="${teacher.id}">${teacher.fullName}</option>
		    		</c:forEach>
		    	</select> 
	    	</td>
		</tr>
		
   </table>
		    
		    <br>
		    <input type="submit" value="Add new lesson" style="position: relative; left: 20px">
	    	
	</form>
	 
	 <footer >Copyright © by me 2015<br>
			 Original idea of design was taken from w3.schools.com
	 </footer>

<script>
function setLessonEnd() {
	var d = new Date();
	var x = document.getElementById("lStart").value;
    var y = document.getElementById("lEnd");
    
    d = document.getElementById("date").value;
    var j = new Date(d + " " + x );
    var h, m;
    j.setMinutes(j.getMinutes() + ${duration});
	
    if(j.getHours() < 10)
    	h = "" + 0 + j.getHours();
    else
        h = j.getHours();
        
    if(j.getMinutes() < 10)
    	m = "" + 0 + j.getMinutes();
    else
   		m = j.getMinutes();
    
   	y.value = h + ":" + m;	
}
</script>
</body>
</html>