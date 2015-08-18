<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visitator</title>
<link rel="stylesheet" href="<c:url value="/resources/visitator.css"/>" type="text/css">
</head>
<body>

<div class="wrapper">

	<header id="wellcome">	
		<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
		<span style="color: #E6E680">Lesson</span></h1>
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
 		
 		<table id="vertHeaders">
 			<tr>
 				<th>Date</th><td>${lesson.date}</td>
 			</tr>
 			<tr>
 				<th>Start and End</th><td>${lesson.startFormat} - ${lesson.endFormat}</td>
 			</tr>
 			<tr>
 				<th>Type</th><td>${lesson.type}</td>
 			</tr>
 			<tr>
 				<th>Subject</th><td>${lesson.subject.name}</td>
 			</tr>
 			<tr>
 				<th>Teacher</th><td>${lesson.teacher.fullName}</td>
 			</tr>
 		</table>

<!--  		<table id="lw97"> -->
<!--  			<tr> -->
<!--  				<th>Date</th> -->
<!--  				<th>Start - End</th> -->
<!--  				<th>Type</th> -->
<!--  				<th>Subject</th> -->
<!--  				<th>Teacher</th> -->
<!--  			</tr> -->
<!--  			<tr> -->
<%--  				<td>${lesson.date}</td> --%>
<%--  				<td>${lesson.startFormat} - ${lesson.endFormat}</td> --%>
<%--  				<td>${lesson.type}</td> --%>
<%--  				<td>${lesson.subject.name}</td> --%>
<%--  				<td>${lesson.teacher.fullName}</td> --%>
<!--  			</tr> -->
 			
<!--  		</table> -->
 		
 		<br>
 		
	 	<table id="lw97">
	 		<tr>
	 			<th>Group</th>
	 			<th>Student</th>
	 			
				<c:if test="${lesson.type ne 'Lecture'}">	 		
	 				<th>Mark</th>
	 			</c:if>
	 		</tr>
	 		<c:forEach items="${lesson.groups}" var="group" >
	 			<c:forEach items="${group.students}" var="student" >
		 			<tr>
		 				<td>${group.name}</td>
		 				
		 				
		 				<td>
		 					<c:set var="abs" value="0" />
		 					<c:forEach items="${lesson.absentStudents}" var="absent">	
		 						
		 							<c:if test="${absent.id == student.id}">
		 								<c:set var="abs" value="1" />
		 							</c:if>
		 					</c:forEach>
		 					
		 						<c:choose>		
		 							<c:when test="${abs == 0}">
		 								${student.fullName}
		 							</c:when>
		 							<c:otherwise>
		 								<span style="color: red">${student.fullName} (Absent)</span>
		 							</c:otherwise>
		 						</c:choose>	
		 					
		 				</td>
		 				
		 				<c:if test="${lesson.type ne 'Lecture'}">
			 				<td>
				 				<c:forEach items="${marks}" var="mark">
				 					<c:if test="${mark.student.id == student.id}">
				 						${mark.mark}
				 					</c:if>
				 				</c:forEach>
			 				</td>
		 				</c:if>
		 				
		 			</tr>
		 		</c:forEach>	
	 		</c:forEach>
	 	</table>
	 
	 <div class="push"></div>
	 	
	 </div>
	 	
	 <div class="footer">
		 Copyright © by Ivaniuk E.G. 2015<br>
		 Original idea of design was taken from w3.schools.com
	 </div>

</body>
</html>