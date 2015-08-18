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
		<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
		<hr>
		<span>
			<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/lessons" />" style="font-size: 17; color: #E6E680">
				Lessons
			</a>
		</span>
		</span>
			<span>
				<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
					College: ${group.specialization.institution.name}
				</a>
			</span><br>
			<span>
				<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
					Faculty: ${group.specialization.name}
				</a>
			</span><br>
			<span>
				<a id="groupHeader" style="padding-left: 0%" href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}">
					Group: ${groupName}
				</a>
			</span><br>
			<span id="itemHeader" style="padding-left: 0%">Lesson</span></h1>
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

		<form action="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/lessons/${lesson.id}/edit" />">
			<span style="padding: 10px; float:left">
 				<input type="submit" value="Edit lesson">
 			</span>
 		</form>
 		
	 	<table id="lw97">
	 		<tr>
	 			<th>Group</th>
	 			<th>Student</th>
	 			
				<c:if test="${lesson.type ne 'Lecture' and lesson.type ne 'Consultation'}">	 		
	 				<th>Mark</th>
	 			</c:if>
	 		</tr>
	 		<c:forEach items="${lesson.groups}" var="group" >
	 			<c:forEach items="${group.students}" var="student" >
		 			<tr>
		 				<td>${group.name}</td>
		 				
		 				
		 				<td>
		 					<c:set var="abs" value="1" />
		 					<c:forEach items="${lesson.students}" var="absent">	
		 						
		 							<c:if test="${absent.id == student.id}">
		 								<c:set var="abs" value="0" />
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