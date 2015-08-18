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

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-1.11.3.js"></script>
	
<script type="text/javascript">
	$(document).ready(function() {
		$("#check").change(function() {
			if(this.checked) {
				document.getElementById('alert').innerHTML = '/ / / / / / THIS LESSON WILL BE DELETED! / / / / / /';
				//this.value()
			}
			else
				document.getElementById('alert').innerHTML = '';
			
		})
	})
</script>

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
					Group: ${group.name}
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

 		<h2 id="alert" style="text-align: center; color: red; padding-top: 5px"></h2>
 		
 		<form method="post" action="">
 		
	 	<table id="lw97">
	 	
	 		<tr>
	 			<th>Group</th>
	 			<th>Student</th>
	 			<th>Absence</th>
				<c:if test="${lesson.type ne 'Lecture'}">	 		
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
	 								<span style="float: right; padding-right: 9px">
	 									Change to absent: 
	 								</span>
	 							</c:when>
	 							<c:otherwise>
	 								<span style="color: red">${student.fullName} (Absent)</span>
	 								<span style="color: red; float: right; padding-right: 4px">
	 									Change to present:
	 								</span>
	 							</c:otherwise>
	 						</c:choose>	
		 				</td>
		 				
		 				<td style="width: 6%">
		 				<c:set var="match" value="0" />
			 				<c:forEach items="${marks}" var="mark">
						 		<c:if test="${mark.student.id == student.id}">
						 			<c:set var="match" value="1" />
						 		
<%-- 	 								<c:when test="${fn:trim(mark.mark) gt '0'}"> --%>
	 							</c:if>
	 							
	 							
		 					</c:forEach>
	 							
		 				<c:if test="${match == 0}">
	 						<input type="checkbox" name="absence" value="${student.id}">
	 					</c:if>
		 					
		 				</td>
		 				<c:if test="${lesson.type ne 'Lecture'}">
			 				<td>
				 			
				 			
				 			<c:set var="abs" value="1" />
		 					<c:forEach items="${lesson.students}" var="absent">	
		 						
		 							<c:if test="${absent.id == student.id}">
		 								<c:set var="abs" value="0" />
		 							</c:if>
		 					</c:forEach>
		 							
	 							<c:if test="${abs == 0}">
	 									<select name="marks">
					 					<c:forEach items="${marks}" var="mark">
						 					<c:if test="${mark.student.id == student.id}">
						 						<option value="${student.id} ${mark.mark}" >${mark.mark}</option>
						 					</c:if>
					 					</c:forEach>
					 					
					 					<option value="${student.id}"></option>
					 					<option value="${student.id} 2">2</option>
					 					<option value="${student.id} 3">3</option>
					 					<option value="${student.id} 4">4</option>
					 					<option value="${student.id} 5">5</option>
					 				</select>
	 							</c:if>
	 								
			 				</td>
		 				</c:if>
		 				
		 			</tr>
		 		</c:forEach>	
	 		</c:forEach>
	 	</table><br>
	 	<span style="float: right; padding-right: 30px">
	 		<span id="tst"></span>
	 		Delete: <input type="checkbox" id="check" name="check" style="margin-right: 20px">
	 		<input type="submit" value="Accept changes">
	 	</span>
	 	</form>
	 <div class="push"></div>
	 	
	 </div>
	 	
	 <div class="footer">
		 Copyright © by Ivaniuk E.G. 2015<br>
		 Original idea of design was taken from w3.schools.com
	 </div>

<script>
	function checkedLength() {
// 		if(document.getElementsByName('absence').length != 0)
// 			alert(document.getElementByName('absence').value + 'a');//document.getElementById('tst').innerHTML = '<input type="submit" value="Accept changes">';
// 		if(document.getElementsByName('absence').value == null){	
// 			//document.getElementById('tst').s
// 			alert('null');
// 		}
	}
</script>
</body>
</html>