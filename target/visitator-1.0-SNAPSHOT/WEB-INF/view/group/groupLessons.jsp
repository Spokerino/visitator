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
				<a id="groupHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}">
					Group: ${group.name}
				</a>
			</span><br>
			<span id="itemHeader">Lessons</span></h1>
			
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
 	
 	<div style="padding-left: 10px">
 		<form method="post" action="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/lessons">
			Show lessons at particular date<br>
			<input type="date" name="date">
		 	<input type="submit" value="Find">				
	 	</form>
	 	
	 	<form action="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/lessons/new" method="get">
	 	<input type="submit"  value="Create new lesson" style="">
	 	</form>
 	</div>
 	
 	
 	
	 	<table id="lw97">
	 		<tr>
	 			<th></th>
	 			<th>Date</th>
	 			<th>Lesson start</th>
	 			<th>Lesson end</th>
	 			<th>Type</th>
	 			<th>Subject</th>
	 			<th>Teacher</th>
<!-- 	 			<th>Groups</th> -->
	 			<th>Students</th>
	 		</tr>
	 		<c:forEach items="${lessonList}" var="lesson" >
	 			<tr>
	 				<td style="width:2%">
	 					<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/lessons/${lesson.id}" />">
							Select
	 					</a>
	 				</td>
	 				<td style="width:6%">${lesson.date}</td>
	 				<td style="width:6%">${lesson.startFormat}</td>
	 				<td style="width:6%">${lesson.endFormat}</td>
	 				<td style="width:6%">${lesson.type}</td>
	 				<td style="width:10%">${lesson.subject.name}</td>
	 				<td style="width:8%">${lesson.teacher.fullName}</td>
<!-- 	 				<td style="width:10%"> -->
<%-- 	 					<c:set var="count" value="0" /> --%>
<%-- 	 					<c:forEach items="${lesson.groups}" var="group"> --%>
<%-- 	 						<c:choose> --%>
<%-- 		 						<c:when test="${count == (fn:length(lesson.groups) - 1)}"> --%>
<%-- 		 							${group.name} --%>
<%-- 		 						</c:when> --%>
<%-- 		 						<c:otherwise> --%>
<%-- 		 							${group.name}, --%>
<%-- 		 						</c:otherwise> --%>
<%-- 	 						</c:choose> --%>
<%-- 	 						<c:set var="count" value="${count + 1}"/> --%>
<%-- 	 					</c:forEach> --%>
<!-- 	 				</td> -->
	 				
					<td style="width:10%">
						<c:set var="sum" value="0"/>
						
						<c:forEach items="${lesson.groups}" var="group">
							<c:set var="sum" value="${sum + fn:length(group.students)}"/>
						</c:forEach>
						
						${fn:length(lesson.students)}/${sum}
					</td>

	 			</tr>
	 		</c:forEach>
	 	</table>
<%-- 		 	<c:if test="${fn:length(lessonList) gt 20}"> --%>
<!-- 		 		<hr /> -->
<%-- 		 		<s:url value="/lessons?count=${nextCount}" var="more_url" /> --%>
<%-- 		 		<a href="${more_url}">More lessons</a> --%>
<%-- 		 	</c:if> --%>
	 <div class="push"></div>
	 </div>

	 <div class="footer">
	 	Copyright © by Ivaniuk E.G. 2015<br>
		Original idea of design was taken from w3.schools.com
	 </div>

</body>
</html>