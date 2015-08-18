<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<head>
	<meta charset="utf-8" />
	<title>Visitator</title>
	<link rel="stylesheet" href="<c:url value="/resources/visitator.css" />" type="text/css">
	<style>
		#facultiesTable {
			float:left;
			
			padding-left: 20px;
			width: 75%;
		}
	
	</style>

</head>
<body style="height: 84%">
<div class="wrapper">
	<c:set scope="session" var="collegeName" value="${college.name}"/>
	
	<header id="wellcome" style="height: 85">	
		
			<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
			<span style="color: #E6E680; font-size:20">College: ${collegeName}<br></span></h1>
			
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
    
    
	<nav style="background-color: white" >
		<ul id="menu">
		  <li><a href="/visitator/colleges">Colleges</a></li>
		  <li><a href="/visitator/colleges/${college.id}/faculties">Faculties</a></li>
		  <li><a href="/visitator/colleges/${college.id}/students">Students</a></li>
		  <li><a href="/visitator/colleges/${college.id}/subjects" >Subjects</a></li>
		  <li><a href="/visitator/colleges/${college.id}/lessons">Lessons</a></li>
		  <li><a href="/visitator/colleges/${college.id}/teachers">Teachers</a></li>
		  <li><a href="/visitator/colleges/${college.id}/edit">Edit college</a></li>
		  
		</ul> 
	</nav>
	<br>
	
	<div id="facultiesTable">		
		<table id="lw97">
		<caption style="font-size:18"><b>Faculties</b></caption>
		<tr>

			<th style="width:15%">Name</th>
			<th style="width:15%">Teachers</th>
			<th style="width:45%">Groups</th>
		</tr>
		<c:forEach items="${college.specializations}" var="faculty">
		<tr>

			<td><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}">${faculty.name}</a></td>
			<td>
				<c:forEach items="${faculty.teachers}" var="teacher">
					<c:if test="${not empty teacher.firstName}">
						${teacher.fullName}<br>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0" />
				<c:forEach items="${faculty.groups}" var="group">
					<c:choose>
						<c:when test="${count == (fn:length(faculty.groups) - 1)}">
							<a href="/visitator/colleges/${collegeId}/faculties/${group.specialization.id}/groups/${group.id}"
							 style="text-decoration: none">
								${group.name} 
							</a>
						</c:when>
						<c:otherwise>
							<a href="/visitator/colleges/${collegeId}/faculties/${group.specialization.id}/groups/${group.id}"
							 style="text-decoration: none">
								${group.name} 
							</a>,
						</c:otherwise>
					</c:choose>
				<c:set var="count" value="${count + 1}" />
				</c:forEach>
			</td>
			
	
		</tr>
		</c:forEach>
	</table>
	</div>
	
	<br>
	
	<div class="push"></div>
	
	</div>
	<br>	
	<div class="footer">Copyright © by Ivaniuk E.G. 2015<br>
		Original idea of design was taken from w3.schools.com
	</div>
</body>

</html>